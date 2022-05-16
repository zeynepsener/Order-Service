package com.zsener.ReadingIsGood.service.impl;

import com.zsener.ReadingIsGood.kafka.model.BookStockEventType;
import com.zsener.ReadingIsGood.kafka.model.BookOrderEvent;
import com.zsener.ReadingIsGood.kafka.producer.BookStockProducer;
import com.zsener.ReadingIsGood.model.Order;
import com.zsener.ReadingIsGood.repository.OrderRepository;
import com.zsener.ReadingIsGood.request.CreateOrderRequest;
import com.zsener.ReadingIsGood.request.GetCustomerStatsRequest;
import com.zsener.ReadingIsGood.request.validator.ValidatorFactory;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.OrderStatisticsResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;
import com.zsener.ReadingIsGood.response.QueryOrderResponse;
import com.zsener.ReadingIsGood.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper mapper;
    private final OrderRepository orderRepository;
    private final BookStockProducer producer;
    private final ValidatorFactory validatorFactory;

    public OrderServiceImpl(ModelMapper mapper, OrderRepository orderRepository, BookStockProducer producer,
                            ValidatorFactory validatorFactory) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.producer = producer;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public BaseResponse createOrder(CreateOrderRequest request) {
        BaseResponse response = new BaseResponse();
        validatorFactory.getFor(CreateOrderRequest.class).validate(request);
        Order inserted = null;
        try {
            Order order = mapper.map(request, Order.class);
            inserted = orderRepository.insert(order);
        } catch (Exception e) {
            response.setFail("Order can not be created.");
        }
        if (inserted != null) {
            sendBookOrderEvent(inserted);
        }
        response.setSuccess("Order is created successfully.");
        return response;
    }

    @Override
    public QueryOrderResponse queryById(String id) {
        QueryOrderResponse response = new QueryOrderResponse();
        try {
            Order order = orderRepository.findById(id).orElse(null);
            if (order == null) {
                response.setSuccess("Order not found.");
            }
            response.setOrder(order);
        } catch (Exception e) {
            response.setFail("Order can not be queried.");
        }
        response.setSuccess("Order is queried successfully.");
        return response;
    }

    @Override
    public QueryOrderListResponse listByInterval(Date start, Date end, int page) {
        QueryOrderListResponse response = new QueryOrderListResponse();
        Page<Order> orderList = orderRepository.findByCreateDateBetween(start, end,
                PageRequest.of(page, 10));
        response.setOrderList(orderList.getContent());
        return response;
    }

    @Override
    public OrderStatisticsResponse listMonthlyStatsOfCustomer(GetCustomerStatsRequest request) {
        OrderStatisticsResponse response = new OrderStatisticsResponse();
        try {
            List<Order> orderList = orderRepository.findByCreateDateBetweenAndCustomerId(request.getStartDate(),
                    request.getEndDate(), request.getCustomerId());

            int totalProductCount = orderList.stream().
                    mapToInt(Order::getTotalItemCount)
                    .sum();

            BigDecimal totalAmount = orderList.stream()
                    .map(Order::getTotalAmount)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            response.setCustomerId(request.getCustomerId());
            response.setTotalPurchasedAmount(totalAmount);
            response.setTotalBookCount(totalProductCount);
            response.setTotalOrderCount(orderList.size());
            response.setMonthOfStats(String.valueOf(request.getStartDate().getMonth()));
        } catch (Exception e) {
            response.setFail("Customer orders can not be listed.");
        }
        response.setSuccess("Customer orders listed successfully.");
        return response;
    }

    private void sendBookOrderEvent(Order order) {
        producer.sendOrderEvent(BookOrderEvent.of(BookStockEventType.DECREASE, order));
    }
}
