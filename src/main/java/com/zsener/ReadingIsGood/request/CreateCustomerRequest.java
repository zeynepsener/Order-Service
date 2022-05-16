package com.zsener.ReadingIsGood.request;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String name;
    private String email;
    private String password;
}
