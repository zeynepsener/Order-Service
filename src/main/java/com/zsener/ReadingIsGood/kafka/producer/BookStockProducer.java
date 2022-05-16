package com.zsener.ReadingIsGood.kafka.producer;

import com.zsener.ReadingIsGood.kafka.model.BookOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookStockProducer {

    private final KafkaTemplate<String, BookOrderEvent> orderKafkaTemplate;

    @Value(value = "${kafka.message.topic.order}")
    private String orderTopicName;

    public BookStockProducer(KafkaTemplate<String, BookOrderEvent> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderEvent(BookOrderEvent event) {
        log.info("Sending order event: {}, to topic: {}", event, orderTopicName);
        orderKafkaTemplate.send(orderTopicName, event);
    }
}
