package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuisineDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Brasileira")
    private String name;
}
