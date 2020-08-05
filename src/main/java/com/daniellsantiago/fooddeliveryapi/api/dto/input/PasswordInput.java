package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ApiModel("Password Input Model")
public class PasswordInput {

    @ApiModelProperty(example = "123456", required = true)
    @NotBlank
    private String oldPassword;

    @ApiModelProperty(example = "12345678", required = true)
    @NotBlank
    private String newPassword;
}
