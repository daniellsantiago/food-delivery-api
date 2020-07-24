package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.CuisineInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuisineInputDisassembler {

    private final ModelMapper modelMapper;

    public Cuisine toDomainObject(CuisineInput cuisineInput) {
        return modelMapper.map(cuisineInput, Cuisine.class);
    }

    public void copyToDomainObject(CuisineInput cuisineInput, Cuisine cuisine) {
        modelMapper.map(cuisineInput, cuisine);
    }
}
