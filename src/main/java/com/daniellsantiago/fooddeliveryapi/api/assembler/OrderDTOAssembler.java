package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.LinksManager;
import com.daniellsantiago.fooddeliveryapi.api.controller.*;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOAssembler extends RepresentationModelAssemblerSupport<Order, OrderDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LinksManager linksManager;

    public OrderDTOAssembler() {
        super(OrderController.class, OrderDTO.class);
    }

    @Override
    public OrderDTO toModel(Order order) {
        OrderDTO orderDTO = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderDTO);

        orderDTO.add(linksManager.linkToOrders("orders"));

        if (order.canBeConfirmed()) {
            orderDTO.add(linksManager.linkToConfirmOrder(order.getCode(), "confirm"));
        }

        if (order.canBeCancelled()) {
            orderDTO.add(linksManager.linkToCancelOrder(order.getCode(), "cancel"));
        }

        if (order.canBeDelivered()) {
            orderDTO.add(linksManager.linkToDeliverOrder(order.getCode(), "deliver"));
        }

        orderDTO.getRestaurant().add(linksManager.linkToRestaurant(order.getRestaurant().getId()));

        orderDTO.getCustomer().add(linksManager.linkToUser(order.getCustomer().getId()));

        orderDTO.getPaymentMethod().add(linksManager.linkToPaymentMethod(order.getPaymentMethod().getId()));

        orderDTO.getDeliveryAddress().getCity()
                .add(linksManager.linkToCity(order.getDeliveryAddress().getCity().getId()));

        orderDTO.getItems().forEach(item -> {
            item.add(linksManager.linkToProduct(orderDTO.getRestaurant().getId(), item.getProductId(), "product"));
        });

        return orderDTO;
    }
}
