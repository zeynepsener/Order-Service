package com.zsener.ReadingIsGood.response;

import com.zsener.ReadingIsGood.model.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderResponse extends BaseResponse {
    private Order order;
}
