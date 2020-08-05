package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("State Input Model")
public class StateInput {

    @ApiModelProperty(example = "Pará", required = true)
    @NotBlank
    private String name;
}
