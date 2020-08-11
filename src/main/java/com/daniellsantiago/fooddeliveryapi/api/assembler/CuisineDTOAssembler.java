package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.LinksManager;
import com.daniellsantiago.fooddeliveryapi.api.controller.CuisineController;
import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CuisineDTOAssembler extends RepresentationModelAssemblerSupport<Cuisine, CuisineDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LinksManager linksManager;

    public CuisineDTOAssembler() {
        super(CuisineController.class, CuisineDTO.class);
    }

    @Override
    public CuisineDTO toModel(Cuisine cuisine) {
        CuisineDTO cuisineDTO = createModelWithId(cuisine.getId(), cuisine);
        modelMapper.map(cuisine, cuisineDTO);

        cuisineDTO.add(linksManager.linkToCuisines("cuisines"));

        return cuisineDTO;
    }

}
