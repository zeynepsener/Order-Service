package com.zsener.ReadingIsGood.request.validator;

import com.zsener.ReadingIsGood.exceptions.InvalidInputException;
import com.zsener.ReadingIsGood.request.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class CreateBookRequestValidator implements IValidator<CreateBookRequest> {
    @Override
    public void validate(CreateBookRequest request) {

        if (request.getTitle().isEmpty()) {
            log.error("Title is empty.");
            throw new InvalidInputException("Title can not be empty.");
        }

        if (request.getPrice().compareTo(BigDecimal.ZERO) != 1) {
            log.error("Price is incorrect.");
            throw new InvalidInputException("Price must be bigger than 0.0");
        }

        if (request.getStockAmount() < 1) {
            log.error("Stock amount is less than 1.");
            throw new InvalidInputException("Stock amount must be bigger than 1.");
        }
    }
}
