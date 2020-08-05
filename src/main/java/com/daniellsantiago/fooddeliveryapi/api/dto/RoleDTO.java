package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Role Model")
public class RoleDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Gerente")
    private String name;
}
