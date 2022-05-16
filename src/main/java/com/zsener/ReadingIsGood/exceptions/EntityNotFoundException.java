package com.zsener.ReadingIsGood.exceptions;

import com.zsener.ReadingIsGood.response.enums.StatusType;

public class EntityNotFoundException extends ServiceException{

    public EntityNotFoundException(String description) {
        super(StatusType.FAIL.name(), description);
    }
}
