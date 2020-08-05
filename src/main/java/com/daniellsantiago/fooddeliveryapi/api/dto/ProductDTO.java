package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Product Model")
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
}
