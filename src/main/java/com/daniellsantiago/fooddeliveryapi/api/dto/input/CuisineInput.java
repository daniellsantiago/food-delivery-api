package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CuisineInput {

    @NotBlank
    private String name;
}
