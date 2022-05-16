package com.zsener.ReadingIsGood.controller;

import com.zsener.ReadingIsGood.request.GetCustomerStatsRequest;
import com.zsener.ReadingIsGood.response.OrderStatisticsResponse;
import com.zsener.ReadingIsGood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/stats", produces = "application/json", consumes = "application/json")
public class StatisticsController {

    private final OrderService orderService;

    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders/monthly")
    public ResponseEntity<OrderStatisticsResponse> queryOrderStatsByCustomer(
            @RequestBody GetCustomerStatsRequest request) {
        OrderStatisticsResponse response = orderService.listMonthlyStatsOfCustomer(request);
        return ResponseEntity.ok().body(response);
    }
}
