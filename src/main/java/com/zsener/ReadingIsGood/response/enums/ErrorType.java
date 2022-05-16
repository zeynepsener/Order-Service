package com.zsener.ReadingIsGood.response.enums;

public enum ErrorType {
    VALIDATION_ERROR(StatusType.FAIL),
    SYSTEM_ERROR(StatusType.FAIL),
    ENTITY_FETCHING_ERROR(StatusType.FAIL);

    private StatusType statusType;

    ErrorType(StatusType statusType) {
        this.statusType = statusType;
    }
}
