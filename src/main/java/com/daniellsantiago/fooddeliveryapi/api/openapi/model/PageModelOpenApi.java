package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {

    @ApiModelProperty(example = "10", value = "Number of elements per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "Number of elements found")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Number of pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Current page number (starts at 0)")
    private Long number;
}
