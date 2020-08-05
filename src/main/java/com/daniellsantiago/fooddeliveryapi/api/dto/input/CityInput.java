package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("City Input Model")
public class CityInput {

    @ApiModelProperty(example = "Uberlândia", required = true)
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdInput state;
}
