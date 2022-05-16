package com.zsener.ReadingIsGood.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderStatisticsResponse extends BaseResponse {
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedAmount;
    private String monthOfStats;
    private String customerId;
}
