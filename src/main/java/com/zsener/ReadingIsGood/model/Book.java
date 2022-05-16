package com.zsener.ReadingIsGood.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Document("book")
public class Book extends BaseModel {

    @Field("title")
    private String title;
    @Field("stock_amount")
    private int stockAmount;
    @Field("price")
    private BigDecimal price;
}
