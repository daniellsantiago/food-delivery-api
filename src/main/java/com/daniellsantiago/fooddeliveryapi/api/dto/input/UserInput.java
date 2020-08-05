package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ApiModel("User Input Model")
public class UserInput {

    @ApiModelProperty(example = "Daniel", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "danielsantiago111@hotmail.com", required = true)
    @NotBlank
    @Email
    private String email;
}
