package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Restaurant Summarized Model")
public class RestaurantBasicDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Panela Brasileira")
    private String name;
    @ApiModelProperty(example = "10")
    private BigDecimal shippingCost;
    private CuisineDTO cuisine;
    @ApiModelProperty(example = "true")
    private Boolean active;
    @ApiModelProperty(example = "true")
    private Boolean open;
}
