package com.zsener.ReadingIsGood.request.validator;

import com.zsener.ReadingIsGood.exceptions.InvalidInputException;
import com.zsener.ReadingIsGood.request.CreateCustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateCustomerRequestValidator implements IValidator<CreateCustomerRequest> {
    @Override
    public void validate(CreateCustomerRequest request) {

        if (request.getName().isEmpty()) {
            log.error("");
            throw new InvalidInputException("");
        }

        if (request.getEmail().isEmpty()) {
            log.error("");
            throw new InvalidInputException("");
        }

        if (request.getPassword().isEmpty() ) {
            log.error("");
            throw new InvalidInputException("");
        }
    }
}
