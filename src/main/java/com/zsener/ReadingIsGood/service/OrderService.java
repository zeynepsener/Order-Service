package com.zsener.ReadingIsGood.service;

import com.zsener.ReadingIsGood.request.CreateOrderRequest;
import com.zsener.ReadingIsGood.request.GetCustomerStatsRequest;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.OrderStatisticsResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;
import com.zsener.ReadingIsGood.response.QueryOrderResponse;

import java.util.Date;

public interface OrderService {
    BaseResponse createOrder(CreateOrderRequest request);
    QueryOrderResponse queryById(String id);
    QueryOrderListResponse listByInterval(Date start, Date end, int page);
    OrderStatisticsResponse listMonthlyStatsOfCustomer(GetCustomerStatsRequest request);
}
