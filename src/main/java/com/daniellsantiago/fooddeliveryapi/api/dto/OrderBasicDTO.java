package com.daniellsantiago.fooddeliveryapi.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderBasicDTO extends RepresentationModel<OrderBasicDTO> {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;
    @ApiModelProperty(example = "298.90")
    private BigDecimal subTotal;
    @ApiModelProperty(example = "10.00")
    private BigDecimal shippingRate;
    @ApiModelProperty(example = "308.90")
    private BigDecimal totalPrice;
    @ApiModelProperty(example = "CREATED")
    private String status;
    @ApiModelProperty(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createdAt;
    private RestaurantReallyBasicDTO restaurant;
    private UserDTO customer;
}
