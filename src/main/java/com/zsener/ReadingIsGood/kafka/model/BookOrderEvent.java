package com.zsener.ReadingIsGood.kafka.model;

import com.zsener.ReadingIsGood.model.Order;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookOrderEvent {

    private String eventId;
    private String type;
    private int itemCount;
    private Order order;

    public static BookOrderEvent of(BookStockEventType type, Order order) {
        BookOrderEvent event = new BookOrderEvent();
        event.setType(type.name());
        event.setOrder(order);
        event.setItemCount(order.getTotalItemCount());
        return event;
    }
}
