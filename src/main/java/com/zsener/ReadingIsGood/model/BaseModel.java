package com.zsener.ReadingIsGood.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public abstract class BaseModel {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Field("create_date")
    @CreatedDate
    private Date createDate;

    @Field("update_date")
    @LastModifiedDate
    private Date updateDate;
}
