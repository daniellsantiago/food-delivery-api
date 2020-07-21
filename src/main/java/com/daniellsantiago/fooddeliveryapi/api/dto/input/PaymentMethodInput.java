package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PaymentMethodInput {

    @NotBlank
    private String name;
}
