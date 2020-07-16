package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddressInput {

    @NotBlank
    private String zip;

    @NotBlank
    private String number;

    private String additionalInformation;

    @NotBlank
    private String street;

    @Valid
    @NotNull
    private CityIdInput city;
}
