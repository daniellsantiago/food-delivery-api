package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RestaurantIdInput {

    @NotNull
    private Long id;
}
