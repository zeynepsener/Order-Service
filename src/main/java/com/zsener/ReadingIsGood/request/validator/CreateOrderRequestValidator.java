package com.zsener.ReadingIsGood.request.validator;

import com.zsener.ReadingIsGood.exceptions.InvalidInputException;
import com.zsener.ReadingIsGood.request.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateOrderRequestValidator implements IValidator<CreateOrderRequest> {
    @Override
    public void validate(CreateOrderRequest request) {

        if (request.getCustomerId().isEmpty()) {
            log.error("");
            throw new InvalidInputException("");
        }

        if (request.getProducts().isEmpty()) {
            log.error("");
            throw new InvalidInputException("");
        }
    }
}
