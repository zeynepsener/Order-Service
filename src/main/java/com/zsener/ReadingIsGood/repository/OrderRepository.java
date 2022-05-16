package com.zsener.ReadingIsGood.repository;

import com.zsener.ReadingIsGood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Page<Order> findByCreateDateBetween(Date start, Date end, Pageable pageable);
    Page<Order> findByCustomerId(String customerId, Pageable pageable);
    List<Order> findByCreateDateBetweenAndCustomerId(Date start, Date end, String customerId);
}
