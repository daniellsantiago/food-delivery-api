package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Payment Method Input Model")
public class PaymentMethodInput {

    @ApiModelProperty(example = "Cartão de crédito", required = true)
    @NotBlank
    private String name;
}
