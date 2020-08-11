package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cuisines")
@Setter
@Getter
public class CuisineDTO extends RepresentationModel<CuisineDTO> {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Brasileira")
    private String name;
}
