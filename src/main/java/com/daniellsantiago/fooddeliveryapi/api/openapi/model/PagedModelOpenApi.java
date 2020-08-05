package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
public class PagedModelOpenApi<T> {
    private List<T> content;

    @ApiModelProperty(example = "10", value = "Number of elements per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "Number of elements found")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Number of pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Current page number (starts at 0)")
    private Long number;

    private Sort sort;
}
