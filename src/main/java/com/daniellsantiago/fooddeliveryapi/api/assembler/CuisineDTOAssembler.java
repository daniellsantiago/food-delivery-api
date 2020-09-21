package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CuisineDTOAssembler {

    private final ModelMapper modelMapper;

    public CuisineDTO toDTO(Cuisine cuisine) {
        return modelMapper.map(cuisine, CuisineDTO.class);
    }

    public Page<CuisineDTO> toCollectionDTO(Page<Cuisine> cuisines) {
        List<CuisineDTO> cuisineDTOS = cuisines.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(cuisineDTOS);
    }
}
