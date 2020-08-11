package com.daniellsantiago.fooddeliveryapi.api.openapi.model;

import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("OrderBasicModel")
@Setter
@Getter
public class OrderBasicModelOpenApi {

    private OrderBasicEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("OrderBasicEmbeddedModel")
    @Data
    public class OrderBasicEmbeddedModelOpenApi {

        private List<OrderBasicDTO> orders;

    }
}
