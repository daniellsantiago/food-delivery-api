package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
public class OrderItemInput {

    @NotNull
    private long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private String observation;
}
