package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("User With Password Input Model")
public class UserWithPasswordInput extends UserInput{

    @ApiModelProperty(example = "12345678", required = true)
    @NotBlank
    private String password;
}
