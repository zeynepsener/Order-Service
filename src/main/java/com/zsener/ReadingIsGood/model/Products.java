package com.zsener.ReadingIsGood.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Products {

    private String id;
    private int productCount;
    private BigDecimal unitPrice;
}
