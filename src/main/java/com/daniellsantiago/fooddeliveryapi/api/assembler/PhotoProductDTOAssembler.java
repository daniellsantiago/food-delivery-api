package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.PhotoProductDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.PhotoProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoProductDTOAssembler {

    private final ModelMapper modelMapper;

    public PhotoProductDTO toDTO(PhotoProduct photo) {
        return modelMapper.map(photo, PhotoProductDTO.class);
    }
}
