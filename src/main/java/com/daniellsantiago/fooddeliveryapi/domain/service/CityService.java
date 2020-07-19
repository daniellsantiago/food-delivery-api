package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUseException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.City;
import com.daniellsantiago.fooddeliveryapi.domain.model.State;
import com.daniellsantiago.fooddeliveryapi.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;
    private final StateService stateService;

    @Transactional
    public City save(City city) {
        Long stateId = city.getState().getId();

        State state = stateService.findById(stateId);

        city.setState(state);

        return cityRepository.save(city);
    }

    @Transactional
    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("City with id " + cityId+ " not found");
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("City with id " + cityId + " is associated with another Entity");
        }
    }

    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
