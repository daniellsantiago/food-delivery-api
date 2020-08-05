package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Photo Product Model")
public class PhotoProductDTO {

    private String filename;
    private String description;
    private String contentType;
    private Long size;

}
