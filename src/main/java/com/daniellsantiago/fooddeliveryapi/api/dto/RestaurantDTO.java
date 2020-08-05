package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Restaurant Model")
public class RestaurantDTO {
    private Long id;
    private String name;
    private BigDecimal shippingCost;
    private CuisineDTO cuisine;
    private AddressDTO address;
    private Boolean active;
    private Boolean open;
}
