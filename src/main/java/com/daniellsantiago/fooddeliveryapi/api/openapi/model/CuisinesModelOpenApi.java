package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("CuisinesModel")
@Setter
@Getter
public class CuisinesModelOpenApi{

    private List<CuisineDTO> cuisines;
    private PageModelOpenApi page;
}
