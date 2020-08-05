package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@ApiModel("Order Item Input Model")
public class OrderItemInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private long productId;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @ApiModelProperty(example = "Menos picante, por favor")
    private String observation;
}
