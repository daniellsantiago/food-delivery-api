package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.LinksManager;
import com.daniellsantiago.fooddeliveryapi.api.controller.OrderController;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderBasicDTOAssembler extends RepresentationModelAssemblerSupport<Order, OrderBasicDTO> {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private LinksManager linksManager;

    public OrderBasicDTOAssembler() {
        super(OrderController.class, OrderBasicDTO.class);
    }

    @Override
    public OrderBasicDTO toModel(Order order) {
        OrderBasicDTO orderBasicDTO = createModelWithId(order.getCode(), order);

        modelMapper.map(order, orderBasicDTO);

        orderBasicDTO.add(linksManager.linkToOrders("orders"));

        orderBasicDTO.getRestaurant().add(linksManager.linkToRestaurant(order.getRestaurant().getId()));

        orderBasicDTO.getCustomer().add(linksManager.linkToUser(order.getCustomer().getId()));

        return orderBasicDTO;
    }
}
