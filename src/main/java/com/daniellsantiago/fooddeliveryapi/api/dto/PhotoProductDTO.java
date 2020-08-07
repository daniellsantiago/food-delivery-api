package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Photo Product Model")
public class PhotoProductDTO {

    @ApiModelProperty(example = "e51fa97f-b99c-437e-841e-dc4b1f9c9707_bradesco.png")
    private String filename;
    @ApiModelProperty(example = "print")
    private String description;
    @ApiModelProperty(example = "image/png")
    private String contentType;
    @ApiModelProperty(example = "4701")
    private Long size;

}
