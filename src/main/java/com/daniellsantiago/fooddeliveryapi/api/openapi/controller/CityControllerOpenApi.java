package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.CityDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.CityInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("Find all Cities")
    List<CityDTO> findAll();

    @ApiOperation("Find one City by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid City ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "City not found", response = ExceptionDetails.class)
    })
    CityDTO findById(@ApiParam(value = "City ID", example = "1", required = true)
                                     Long id);

    @ApiOperation("Save a new City to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "City created"),
    })
    CityDTO save(@ApiParam(name = "body", value = "Represents a new City", required = true)
                                 CityInput cityInput);

    @ApiOperation("Update City information given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "City updated"),
            @ApiResponse(code = 404, message = "City not found", response = ExceptionDetails.class)
    })
    CityDTO update(@ApiParam(name = "body", value = "Represents a new City", required = true)
                                   CityInput cityInput,
                                   @ApiParam(value = "City ID", example = "1", required = true)
                                   Long id);

    @ApiOperation("Delete a City given an id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid City ID", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "City deleted"),
            @ApiResponse(code = 404, message = "City not found", response = ExceptionDetails.class)
    })
    void delete(@ApiParam(value = "City ID", example = "1", required = true)
                                Long id);
}
