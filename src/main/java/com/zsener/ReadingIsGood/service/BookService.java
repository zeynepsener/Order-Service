package com.zsener.ReadingIsGood.service;

import com.zsener.ReadingIsGood.kafka.model.BookOrderEvent;
import com.zsener.ReadingIsGood.model.Book;
import com.zsener.ReadingIsGood.request.CreateBookRequest;
import com.zsener.ReadingIsGood.response.BaseResponse;

public interface BookService {
    BaseResponse createBook(CreateBookRequest request);
    void updateBookStock(BookOrderEvent event);
    Book findById(String id);
}
