package com.zsener.ReadingIsGood.request;

import com.zsener.ReadingIsGood.model.Products;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String customerId;
    private List<Products> products;
}
