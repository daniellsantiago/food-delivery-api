package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ApiModel("Cuisine Input Model")
public class CuisineInput {

    @ApiModelProperty(example = "Brazilian", required = true)
    @NotBlank
    private String name;
}
