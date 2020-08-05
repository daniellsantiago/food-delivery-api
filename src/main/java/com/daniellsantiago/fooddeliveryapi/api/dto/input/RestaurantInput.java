package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Restaurant Input Model")
public class RestaurantInput {

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "10.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal shippingCost;

    @Valid
    @NotNull
    private CuisineIdInput cuisine;

    @Valid
    @NotNull
    private AddressInput address;
}
