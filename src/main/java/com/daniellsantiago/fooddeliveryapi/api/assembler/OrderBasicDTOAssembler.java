package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderBasicDTOAssembler {

    private final ModelMapper modelMapper;

    public OrderBasicDTO toDTO(Order order) {
        return modelMapper.map(order, OrderBasicDTO.class);
    }

    public Page<OrderBasicDTO> toCollectionDTO(Page<Order> orders) {
        List<OrderBasicDTO> ordersDTO = orders.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(ordersDTO);
    }
}
