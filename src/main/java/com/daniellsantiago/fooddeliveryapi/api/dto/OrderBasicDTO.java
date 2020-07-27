package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderBasicDTO {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal shippingRate;
    private BigDecimal totalPrice;
    private String status;
    private OffsetDateTime createdAt;
    private RestaurantReallyBasicDTO restaurant;
    private UserDTO customer;
}
