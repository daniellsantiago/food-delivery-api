package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.RestaurantInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Address;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantInputDisassembler {

    private final ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant) {
        // To avoid org.hibernate.HibernateException: identifier of an instance of
        // domain.model.Cuisine was altered from 1 to 2
        restaurant.setCuisine(new Cuisine());
        if(restaurant.getAddress() != null) {
            restaurant.getAddress().setCity(new City());
        }
        modelMapper.map(restaurantInput, restaurant);
    }
}
