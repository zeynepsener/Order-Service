package com.zsener.ReadingIsGood.kafka.consumer;

import com.zsener.ReadingIsGood.kafka.model.BookOrderEvent;
import com.zsener.ReadingIsGood.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookStockConsumer {

    private final BookService bookService;

    public BookStockConsumer(BookService bookService) {
        this.bookService = bookService;
    }

    @KafkaListener(topics = "${kafka.message.topic.order}")
    public void receive(@Payload BookOrderEvent event) {
        if (event != null) {
            bookService.updateBookStock(event);
        }
    }
}
