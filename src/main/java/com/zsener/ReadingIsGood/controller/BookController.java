package com.zsener.ReadingIsGood.controller;

import com.zsener.ReadingIsGood.request.CreateBookRequest;
import com.zsener.ReadingIsGood.response.BaseResponse;
import com.zsener.ReadingIsGood.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/book", produces = "application/json", consumes = "application/json")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse> createBook(@RequestBody CreateBookRequest request) {
        BaseResponse response = bookService.createBook(request);
        return ResponseEntity.ok().body(response);
    }
}
