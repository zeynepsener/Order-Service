package com.zsener.ReadingIsGood.exceptions;

import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.enums.StatusType;

public abstract class ServiceException extends RuntimeException {
    private String status;
    private String description;

    public ServiceException(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public BaseResponse getResponse() {
        BaseResponse response = new BaseResponse();
        response.setStatus(this.status);
        response.setMessage(this.description);
        return response;
    }
}
