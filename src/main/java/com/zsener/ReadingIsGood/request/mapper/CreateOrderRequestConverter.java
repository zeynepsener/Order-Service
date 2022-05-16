package com.zsener.ReadingIsGood.request.mapper;

import com.zsener.ReadingIsGood.model.Order;
import com.zsener.ReadingIsGood.model.Products;
import com.zsener.ReadingIsGood.request.CreateOrderRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderRequestConverter implements Converter<CreateOrderRequest, Order> {

    @Override
    public Order convert(MappingContext<CreateOrderRequest, Order> ctx) {
        List<Products> products = ctx.getSource().getProducts();

        Order order = new Order();
        order.setCustomerId(ctx.getSource().getCustomerId());
        order.setProducts(products);
        int totalItemCount = products.stream().
                mapToInt(Products::getProductCount)
                .sum();
        order.setTotalItemCount(totalItemCount);
        order.setTotalAmount(getTotalAmount(products));
        return order;
    }

    private BigDecimal getTotalAmount(List<Products> products) {
        BigDecimal totalAmount = new BigDecimal(0);
        for (Products product : products) {
            BigDecimal quantity = new BigDecimal(product.getProductCount());
            BigDecimal productAmount = product.getUnitPrice().multiply(quantity);
            totalAmount = totalAmount.add(productAmount);
        }
        return totalAmount;
    }
}
