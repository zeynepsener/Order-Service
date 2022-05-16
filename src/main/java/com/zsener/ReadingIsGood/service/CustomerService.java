package com.zsener.ReadingIsGood.service;

import com.zsener.ReadingIsGood.request.CreateCustomerRequest;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;

public interface CustomerService {
    BaseResponse createCustomer(CreateCustomerRequest request);

    QueryOrderListResponse queryForCustomerOrders(String customerId, int page);
}
