package com.zsener.ReadingIsGood.request.mapper;

import com.zsener.ReadingIsGood.model.Customer;
import com.zsener.ReadingIsGood.request.CreateCustomerRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CreateCustomerRequestConverter implements Converter<CreateCustomerRequest, Customer> {

    @Override
    public Customer convert(MappingContext<CreateCustomerRequest, Customer> ctx) {
        Customer customer = new Customer();
        customer.setName(ctx.getSource().getName());
        customer.setEmail(ctx.getSource().getEmail());
        customer.setPassword(ctx.getSource().getPassword());
        return customer;
    }
}
