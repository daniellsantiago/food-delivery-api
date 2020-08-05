package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Restaurant Summarized Model")
public class RestaurantBasicDTO {
    private Long id;
    private String name;
    private BigDecimal shippingCost;
    private CuisineDTO cuisine;
    private Boolean active;
    private Boolean open;
}
