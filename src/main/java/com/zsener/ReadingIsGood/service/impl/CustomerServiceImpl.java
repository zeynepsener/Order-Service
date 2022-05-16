package com.zsener.ReadingIsGood.service.impl;

import com.zsener.ReadingIsGood.model.Customer;
import com.zsener.ReadingIsGood.model.Order;
import com.zsener.ReadingIsGood.repository.CustomerRepository;
import com.zsener.ReadingIsGood.repository.OrderRepository;
import com.zsener.ReadingIsGood.request.CreateCustomerRequest;
import com.zsener.ReadingIsGood.request.validator.ValidatorFactory;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;
import com.zsener.ReadingIsGood.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ValidatorFactory validatorFactory;

    public CustomerServiceImpl(ModelMapper mapper, CustomerRepository customerRepository,
                               OrderRepository orderRepository, ValidatorFactory validatorFactory) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public BaseResponse createCustomer(CreateCustomerRequest request) {
        BaseResponse response = new BaseResponse();
        validatorFactory.getFor(CreateCustomerRequest.class).validate(request);
        try {
            Customer customer = mapper.map(request, Customer.class);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);
            customerRepository.save(customer);
        } catch (Exception e) {
            response.setFail("Customer can not be created.");
        }
        response.setSuccess("Customer is created successfully.");
        return response;
    }

    @Override
    public QueryOrderListResponse queryForCustomerOrders(String customerId, int page) {
        QueryOrderListResponse response = new QueryOrderListResponse();
        try {
            Page<Order> orderList = orderRepository.findByCustomerId(customerId, PageRequest.of(page, 10));
            response.setOrderList(orderList.getContent());
        } catch (Exception e) {
            response.setFail("Customer can not be queried.");
        }
        response.setSuccess("Customer orders are queried successfully.");
        return response;
    }
}
