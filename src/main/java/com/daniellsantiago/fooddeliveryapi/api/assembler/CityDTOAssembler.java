package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityDTOAssembler {

    private final ModelMapper modelMapper;

    public CityDTO toDTO(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

    public List<CityDTO> toCollectionDTO(List<City> cities) {
        return cities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
