package com.zsener.ReadingIsGood.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Document("customer")
public class Customer extends BaseModel {

    @Field("name")
    private String name;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
}
