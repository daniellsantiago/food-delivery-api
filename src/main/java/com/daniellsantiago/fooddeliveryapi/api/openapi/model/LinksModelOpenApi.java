package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@ApiModel("Links")
public class LinksModelOpenApi {
    private String rel;
    private String href;

}
