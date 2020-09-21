package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("OrderBasicModel")
@Setter
@Getter
public class OrderBasicModelOpenApi {

    private List<OrderBasicDTO> orders;
    private PageModelOpenApi page;
}
