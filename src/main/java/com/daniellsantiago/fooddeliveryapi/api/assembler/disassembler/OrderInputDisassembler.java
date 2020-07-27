package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderInputDisassembler {

    private final ModelMapper modelMapper;

    public Order toDomainObject(OrderInput orderInput) {
        return modelMapper.map(orderInput, Order.class);
    }

    public void copyToDomainObject(OrderInput orderInput, Order order) {
        modelMapper.map(orderInput, order);
    }

}
