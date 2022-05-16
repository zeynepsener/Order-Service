package com.zsener.ReadingIsGood.response;

import com.zsener.ReadingIsGood.model.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrderListResponse extends BaseResponse {
    private List<Order> orderList;
}
