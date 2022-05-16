package com.zsener.ReadingIsGood.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBookRequest {
    private String title;
    private int stockAmount;
    private BigDecimal price;
}
