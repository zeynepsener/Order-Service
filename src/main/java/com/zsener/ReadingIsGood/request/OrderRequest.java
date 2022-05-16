package com.zsener.ReadingIsGood.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String id;
    private int purchasedAmount;
}
