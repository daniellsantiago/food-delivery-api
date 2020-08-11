package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CityModelOpenApi {

    private CitiesEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CitiesEmbeddedModel")
    @Data
    public class CitiesEmbeddedModelOpenApi {

        private List<CityDTO> cities;

    }
}
