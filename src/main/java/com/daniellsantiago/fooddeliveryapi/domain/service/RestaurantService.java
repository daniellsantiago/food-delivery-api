package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.*;
import com.daniellsantiago.fooddeliveryapi.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;
    private final CityService cityService;
    private final PaymentMethodService paymentMethodService;
    private final UserService userService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineService.findById(cuisineId);
        City city = cityService.findById(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);

        return restaurantRepository.save(restaurant);
    }
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }


    public List<Restaurant> findAllActives() {
        return restaurantRepository.findAllActives();
    }

    public List<Restaurant> findAllInactives() {
        return restaurantRepository.findAllInactives();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with id " + id + " not found"));
    }

    @Transactional
    public void activate(Long id) {
        Restaurant restaurant = findById(id);

        restaurant.activate();
    }

    @Transactional
    public void inactivate(Long id) {
        Restaurant restaurant = findById(id);

        restaurant.inactivate();
    }

    @Transactional
    public void associatePaymentMethod(Long restaurantId, Long paymentId) {
        Restaurant restaurant = findById(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentId);

        restaurant.addPaymentMethod(paymentMethod);
    }

    @Transactional
    public void disassociatePaymentMethod(Long restaurantId, Long paymentId) {
        Restaurant restaurant = findById(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentId);

        restaurant.removePaymentMethod(paymentMethod);
    }

    @Transactional
    public void open(Long id) {
        Restaurant restaurant = findById(id);

        restaurant.open();
    }

    @Transactional
    public void close(Long id) {
        Restaurant restaurant = findById(id);

        restaurant.close();
    }

    @Transactional
    public void associateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        User user = userService.findById(userId);

        restaurant.addResponsible(user);
    }

    @Transactional
    public void disassociateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        User user = userService.findById(userId);

        restaurant.removeResponsible(user);
    }

}
