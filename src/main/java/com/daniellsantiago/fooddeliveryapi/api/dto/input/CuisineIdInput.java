package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Cuisine ID Input Model")
public class CuisineIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
