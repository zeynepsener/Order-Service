package com.zsener.ReadingIsGood.model;

import com.zsener.ReadingIsGood.model.enums.OrderStatus;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Document("order")
public class Order extends BaseModel {

    @Field("customer_id")
    private String customerId;
    @Field("order_status")
    private OrderStatus orderStatus;
    @Field("delivery_date")
    private Date deliveryDate;
    @Field("total_amount")
    private BigDecimal totalAmount;
    @Field("total_item_count")
    private Integer totalItemCount;
    @Field("products")
    private List<Products> products;
}
