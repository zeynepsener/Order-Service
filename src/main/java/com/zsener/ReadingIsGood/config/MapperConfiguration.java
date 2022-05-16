package com.zsener.ReadingIsGood.config;

import com.zsener.ReadingIsGood.request.mapper.CreateBookRequestConverter;
import com.zsener.ReadingIsGood.request.mapper.CreateCustomerRequestConverter;
import com.zsener.ReadingIsGood.request.mapper.CreateOrderRequestConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new CreateBookRequestConverter());
        mapper.addConverter(new CreateCustomerRequestConverter());
        mapper.addConverter(new CreateOrderRequestConverter());
        return mapper;
    }
}
