package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Ordem Item Model")
public class OrderItemDTO {
    @ApiModelProperty(example = "1")
    private Long productId;
    @ApiModelProperty(example = "Porco com molho agridoce")
    private String productName;
    @ApiModelProperty(example = "2")
    private Integer quantity;
    @ApiModelProperty(example = "78.90")
    private BigDecimal unitPrice;
    @ApiModelProperty(example = "157.80")
    private BigDecimal totalPrice;
    @ApiModelProperty(example = "Menos picante, por favor")
    private String observation;
}
