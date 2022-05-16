package com.zsener.ReadingIsGood.response;

import lombok.Data;

@Data
public class AuthResponse extends BaseResponse{
    private String token;
    private String type = "Bearer";

    public AuthResponse(String token) {
        this.token = token;
    }
}
