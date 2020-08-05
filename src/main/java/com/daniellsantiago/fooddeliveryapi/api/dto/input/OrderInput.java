package com.daniellsantiago.fooddeliveryapi.api.dto.input;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@ApiModel("Order Input Model")
public class OrderInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private AddressInput deliveryAddress;

    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemInput> items;
}
