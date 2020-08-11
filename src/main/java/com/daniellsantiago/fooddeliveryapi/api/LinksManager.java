package com.daniellsantiago.fooddeliveryapi.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.daniellsantiago.fooddeliveryapi.api.controller.*;

import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.IanaLinkRelations;

import org.springframework.stereotype.Component;

@Component
public class LinksManager {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", VariableType.REQUEST_PARAM),
            new TemplateVariable("size", VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", VariableType.REQUEST_PARAM));

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("customerId", VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", VariableType.REQUEST_PARAM),
                new TemplateVariable("initialCreationDate", VariableType.REQUEST_PARAM),
                new TemplateVariable("endCreationDate", VariableType.REQUEST_PARAM));

        String ordersUrl = linkTo(OrderController.class).toUri().toString();
        return Link.of(UriTemplate.of(ordersUrl, PAGINATION_VARIABLES.concat(filterVariables)), rel);
    }
    public Link linkToConfirmOrder(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .confirm(orderCode)).withRel(rel);
    }

    public Link linkToDeliverOrder(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .deliver(orderCode)).withRel(rel);
    }

    public Link linkToCancelOrder(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .cancel(orderCode)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .findById(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .findById(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToPaymentMethod(Long paymentMethodId, String rel) {
        return linkTo(methodOn(PaymentMethodController.class)
                .findById(paymentMethodId, null)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId) {
        return linkToPaymentMethod(paymentMethodId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCities(String rel) {
        return linkTo(CityController.class).withRel(rel);
    }

    public Link linkToCities() {
        return linkToCities(IanaLinkRelations.SELF.value());
    }
    public Link linkToCity(Long cityId, String rel) {
        return linkTo(methodOn(CityController.class)
                .findById(cityId)).withRel(rel);
    }

    public Link linkToCity(Long cityId) {
        return linkToCity(cityId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProduct(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .findProductById(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToProduct(Long restaurantId, Long productId) {
        return linkToProduct(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCuisines(String rel) {
        return linkTo(CuisineController.class).withRel(rel);
    }

    public Link linkToCuisines() {
        return linkToCuisines(IanaLinkRelations.SELF.value());
    }

}
