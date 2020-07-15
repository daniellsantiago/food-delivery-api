package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.CuisineInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CuisineInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Cuisine toDomainObject(CuisineInput cuisineInput) {
        return modelMapper.map(cuisineInput, Cuisine.class);
    }

    public void copyToDomainObject(CuisineInput cuisineInput, Cuisine cuisine) {
        modelMapper.map(cuisineInput, cuisine);
    }
}
