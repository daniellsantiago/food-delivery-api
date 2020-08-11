package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
@ApiModel("Restaurant Really Summarized")
public class RestaurantReallyBasicDTO extends RepresentationModel<RestaurantReallyBasicDTO> {
    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Thai Gourmet")
    private String name;
}
