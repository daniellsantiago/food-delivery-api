package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Address Model")
public class AddressDTO {

    @ApiModelProperty(example = "38400-000")
    private String zip;

    @ApiModelProperty(example = "1500")
    private String number;

    @ApiModelProperty(example = "Apto 901")
    private String additionalInformation;

    @ApiModelProperty(example = "Rua Floriano Peixoto")
    private String street;

    private CityDTO city;
}
