package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Permission Model")
public class PermissionDTO {
    private Long id;
    private String name;
    private String description;
}
