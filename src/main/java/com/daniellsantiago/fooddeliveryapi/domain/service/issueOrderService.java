package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.BussinessRuleException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.*;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class issueOrderService {
    private final OrderRepository orderRepository;

    private final RestaurantService restaurantService;

    private final CityService cityService;

    private final UserService userService;

    private final ProductService productService;

    private final PaymentMethodService paymentMethodService;

    @Transactional
    public Order issue(Order order) {
        validateOrder(order);
        validateItems(order);

        order.setShippingRate(order.getRestaurant().getShippingCost());
        order.calculateTotalPrice();

        return orderRepository.save(order);
    }

    public Order findByCode(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Order with code " + code + " not found"));
    }

    private void validateOrder(Order order) {
        City city = cityService.findById(order.getDeliveryAddress().getCity().getId());
        User customer = userService.findById(order.getCustomer().getId());
        Restaurant restaurant = restaurantService.findById(order.getRestaurant().getId());
        PaymentMethod paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());

        order.getDeliveryAddress().setCity(city);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);

        if(restaurant.doesNotAcceptPaymentMethod(paymentMethod)) {
            throw new BussinessRuleException(String.format("Payment Method '%s' it's not accept by this Restaurant.",
                    paymentMethod.getName()));
        }
    }

    private void validateItems(Order order) {
        order.getItems().forEach(item -> {
            Product product = productService.findById(order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }
}
