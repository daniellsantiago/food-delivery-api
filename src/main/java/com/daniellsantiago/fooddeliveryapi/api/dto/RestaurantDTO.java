package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private BigDecimal shippingCost;
    private CuisineDTO cuisine;
    private AddressDTO address;
    private Boolean active;
}
