package com.zsener.ReadingIsGood.controller;

import com.zsener.ReadingIsGood.request.AuthRequest;
import com.zsener.ReadingIsGood.request.CreateCustomerRequest;
import com.zsener.ReadingIsGood.response.AuthResponse;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.QueryOrderListResponse;
import com.zsener.ReadingIsGood.security.JwtUtils;
import com.zsener.ReadingIsGood.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer", produces = "application/json", consumes = "application/json")
public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager,
                              JwtUtils jwtUtils) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<AuthResponse> authCustomer(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        BaseResponse response = customerService.createCustomer(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/orders/{customerId}/{page}")
    public ResponseEntity<QueryOrderListResponse> queryOrders(@PathVariable String customerId,
                                                              @PathVariable int page) {
        QueryOrderListResponse response = customerService.queryForCustomerOrders(customerId, page);
        return ResponseEntity.ok().body(response);
    }
}
