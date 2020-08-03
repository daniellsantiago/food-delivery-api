package com.daniellsantiago.fooddeliveryapi.core.modelmapper;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderItemInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }
}
