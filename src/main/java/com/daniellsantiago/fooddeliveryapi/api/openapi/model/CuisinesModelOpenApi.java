package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CuisinesModel")
@Setter
@Getter
public class CuisinesModelOpenApi{

    private CuisinesEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CuisinesEmbeddedModel")
    @Data
    public class CuisinesEmbeddedModelOpenApi {

        private List<CuisineDTO> cuisines;

    }
}
