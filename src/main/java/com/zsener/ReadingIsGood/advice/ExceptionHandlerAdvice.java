package com.zsener.ReadingIsGood.advice;

import com.zsener.ReadingIsGood.exceptions.EntityNotFoundException;
import com.zsener.ReadingIsGood.exceptions.InvalidInputException;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.response.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse invalidInputExceptionHandler (InvalidInputException e) {
        log.error("Validation error occurred..");
        BaseResponse response = new BaseResponse();
        response.setStatus(ErrorType.VALIDATION_ERROR.name());
        response.setMessage(e.getResponse().getMessage());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseResponse entityNotFoundExceptionHandler (EntityNotFoundException e) {
        log.error("EntityNotFoundException occurred..");
        BaseResponse response = new BaseResponse();
        response.setStatus(ErrorType.ENTITY_FETCHING_ERROR.name());
        response.setMessage(e.getResponse().getMessage());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    BaseResponse generalExceptionHandler (Exception e) {
        log.error("System error occurred..");
        BaseResponse response = new BaseResponse();
        response.setStatus(ErrorType.SYSTEM_ERROR.name());
        response.setMessage(e.getMessage());
        return response;
    }
}
