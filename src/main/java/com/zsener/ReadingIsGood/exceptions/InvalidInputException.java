package com.zsener.ReadingIsGood.exceptions;

import com.zsener.ReadingIsGood.response.enums.StatusType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends ServiceException{

    public InvalidInputException(String description) {
        super(StatusType.FAIL.name(), description);
    }
}
