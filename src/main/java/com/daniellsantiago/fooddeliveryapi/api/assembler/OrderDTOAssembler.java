package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDTOAssembler {

    private final ModelMapper modelMapper;

    public OrderBasicDTO toDTO(Order order) {
        return modelMapper.map(order, OrderBasicDTO.class);
    }

    public List<OrderBasicDTO> toCollectionDTO(List<Order> orders) {
        return orders.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
