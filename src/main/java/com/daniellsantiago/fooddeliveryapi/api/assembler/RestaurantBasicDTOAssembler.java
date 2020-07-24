package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.RestaurantBasicDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantBasicDTOAssembler {

    private final ModelMapper modelMapper;

    public RestaurantBasicDTO toDTO(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantBasicDTO.class);
    }

    public List<RestaurantBasicDTO> toCollectionDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
