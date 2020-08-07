package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Permission Model")
public class PermissionDTO {

    @ApiModelProperty(example = "2")
    private Long id;
    @ApiModelProperty(example = "EDIT_CUISINES")
    private String name;
    @ApiModelProperty(example = "Allows you to edit cuisines")
    private String description;
}
