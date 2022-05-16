package com.zsener.ReadingIsGood.request.mapper;

import com.zsener.ReadingIsGood.model.Book;
import com.zsener.ReadingIsGood.request.CreateBookRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CreateBookRequestConverter implements Converter<CreateBookRequest, Book> {

    @Override
    public Book convert(MappingContext<CreateBookRequest, Book> ctx) {
        Book book = new Book();
        book.setTitle(ctx.getSource().getTitle());
        book.setStockAmount(ctx.getSource().getStockAmount());
        book.setPrice(ctx.getSource().getPrice());
        return book;
    }
}
