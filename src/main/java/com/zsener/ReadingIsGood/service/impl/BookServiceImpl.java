package com.zsener.ReadingIsGood.service.impl;

import com.zsener.ReadingIsGood.exceptions.EntityNotFoundException;
import com.zsener.ReadingIsGood.kafka.model.BookOrderEvent;
import com.zsener.ReadingIsGood.kafka.model.BookStockEventType;
import com.zsener.ReadingIsGood.model.Book;
import com.zsener.ReadingIsGood.model.Products;
import com.zsener.ReadingIsGood.repository.BookRepository;
import com.zsener.ReadingIsGood.request.CreateBookRequest;
import com.zsener.ReadingIsGood.request.validator.ValidatorFactory;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final ModelMapper mapper;
    private final BookRepository bookRepository;
    private final ValidatorFactory validatorFactory;

    public BookServiceImpl(ModelMapper mapper, BookRepository bookRepository,
                           ValidatorFactory validatorFactory) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public BaseResponse createBook(CreateBookRequest request) {
        BaseResponse response = new BaseResponse();
        validatorFactory.getFor(CreateBookRequest.class).validate(request);
        try {
            Book book = mapper.map(request, Book.class);
            bookRepository.save(book);
        } catch (Exception e) {
            log.error("An exception occurred while trying to persist book", e);
            response.setFail("An exception occurred while trying to persist book");
        }
        response.setSuccess("Book persisted successfully.");
        return response;
    }

    public void updateBookStock(BookOrderEvent event) {
        try {
            event.getOrder().getProducts()
                    .forEach(item -> findAndUpdateBookStock(item,
                            event.getType(),
                            event.getItemCount()));

        } catch (Exception e) {
            log.error("An exception occurred while trying to update stock amount of book", e);
        }
    }

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    private void findAndUpdateBookStock(Products product, String eventType, int amount) {
        Book book = bookRepository.findById(product.getId()).orElse(null);
        if (book == null) {
            log.debug("Book can not be found.");
            throw new EntityNotFoundException("Book can not be found.");
        }
        if (BookStockEventType.DECREASE.name().equals(eventType)) {
            book.setStockAmount(book.getStockAmount() - amount);
        }
        bookRepository.save(book);
    }
}
