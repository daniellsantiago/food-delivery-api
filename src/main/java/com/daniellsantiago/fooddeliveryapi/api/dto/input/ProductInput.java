package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Product Input Model")
public class ProductInput {

    @ApiModelProperty(example = "Doritos", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "Snack", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(example = "5.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean active;
}
