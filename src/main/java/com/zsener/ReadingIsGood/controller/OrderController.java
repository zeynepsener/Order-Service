package com.zsener.ReadingIsGood.controller;

import com.zsener.ReadingIsGood.request.CreateOrderRequest;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;
import com.zsener.ReadingIsGood.response.QueryOrderResponse;
import com.zsener.ReadingIsGood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/order", produces = "application/json", consumes = "application/json")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse> createOrder(@RequestBody CreateOrderRequest request) {
        BaseResponse response = orderService.createOrder(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<QueryOrderResponse> queryOrder(@PathVariable String id) {
        QueryOrderResponse response = orderService.queryById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/order/{startDate}/{endDate}/{page}")
    public ResponseEntity<QueryOrderListResponse> queryOrdersByInterval(@PathVariable Date startDate,
                                                                    @PathVariable Date endDate,
                                                                    @PathVariable int page) {
        QueryOrderListResponse response = orderService.listByInterval(startDate, endDate, page);
        return ResponseEntity.ok().body(response);
    }
}
