package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Product Model")
public class ProductDTO {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Pork with sweet and sour sauce")
    private String name;
    @ApiModelProperty(example = "Delicious pork in special sauce")
    private String description;
    @ApiModelProperty(example = "78.90")
    private BigDecimal price;
    @ApiModelProperty(example = "true")
    private Boolean active;
}
