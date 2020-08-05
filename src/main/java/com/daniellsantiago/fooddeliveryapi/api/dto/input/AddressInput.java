package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel("Address Input Model")
public class AddressInput {

    @ApiModelProperty(example = "38400-000", required = true)
    @NotBlank
    private String zip;

    @ApiModelProperty(example = "1500", required = true)
    @NotBlank
    private String number;

    @ApiModelProperty(example = "Apto 901")
    private String additionalInformation;

    @ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
    @NotBlank
    private String street;

    @Valid
    @NotNull
    private CityIdInput city;
}
