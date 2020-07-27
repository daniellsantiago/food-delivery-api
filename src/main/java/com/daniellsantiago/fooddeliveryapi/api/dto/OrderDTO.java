package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class OrderDTO {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal shippingRate;
    private BigDecimal totalPrice;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime confirmedAt;
    private OffsetDateTime deliveredAt;
    private OffsetDateTime cancelledAt;
    private RestaurantBasicDTO restaurant;
    private UserDTO customer;
    private PaymentMethodDTO paymentMethod;
    private AddressDTO deliveryAddress;
    private List<OrderItemDTO> items;
}
