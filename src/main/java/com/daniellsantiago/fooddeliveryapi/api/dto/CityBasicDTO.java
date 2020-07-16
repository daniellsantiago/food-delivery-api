package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityBasicDTO {

    private Long id;

    private String name;

    private StateDTO state;
}
