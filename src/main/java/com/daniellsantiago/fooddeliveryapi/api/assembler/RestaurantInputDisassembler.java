package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.RestaurantInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        // To avoid org.hibernate.HibernateException: identifier of an instance of
        // domain.model.Cuisine was altered from 1 to 2
        restaurant.setCuisine(new Cuisine());

        modelMapper.map(restaurantInput, restaurant);
    }
}
