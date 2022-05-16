package com.zsener.ReadingIsGood.response;

import com.zsener.ReadingIsGood.response.enums.StatusType;
import lombok.Data;

@Data
public class BaseResponse {
    private String status;
    private String message;

    public void setSuccess(String message) {
        this.status = StatusType.SUCCESS.name();
        this.message = message;
    }

    public void setFail(String message) {
        this.status = StatusType.FAIL.name();
        this.message = message;
    }
}
