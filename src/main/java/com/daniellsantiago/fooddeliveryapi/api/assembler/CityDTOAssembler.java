package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.LinksManager;
import com.daniellsantiago.fooddeliveryapi.api.controller.CityController;
import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityDTOAssembler extends RepresentationModelAssemblerSupport<City, CityDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LinksManager linksManager;

    public CityDTOAssembler() {
        super(CityController.class, CityDTO.class);
    }

    @Override
    public CityDTO toModel(City city) {
        CityDTO cityDTO = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityDTO);

        cityDTO.add(linksManager.linkToCities("cities"));

        return cityDTO;
    }

    @Override
    public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                        .add(linksManager.linkToCities());
    }
}
