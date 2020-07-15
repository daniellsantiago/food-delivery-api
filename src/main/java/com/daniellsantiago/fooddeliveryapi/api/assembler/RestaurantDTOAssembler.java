package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantDTO toDTO(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }
    public List<RestaurantDTO> toCollectionDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
