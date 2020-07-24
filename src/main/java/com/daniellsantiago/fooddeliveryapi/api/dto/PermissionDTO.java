package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionDTO {
    private Long id;
    private String name;
    private String description;
}
