package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.StateDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.StateInput;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("Find all States")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No State registered", response = ExceptionDetails.class)
    })
    ResponseEntity<List<StateDTO>> findAll();

    @ApiOperation("Find one State by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid State ID", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "State not found", response = ExceptionDetails.class)
    })
    ResponseEntity<StateDTO> findById(@ApiParam(value = "State ID", example = "1", required = true)
                                      Long id);

    @ApiOperation("Save a new State to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "State created"),
    })
    ResponseEntity<StateDTO> save(@ApiParam(name = "body", value = "Represents a new State", required = true)
                                  StateInput stateInput);

    @ApiOperation("Update State information given an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "State updated"),
            @ApiResponse(code = 404, message = "State not found", response = ExceptionDetails.class)
    })
    ResponseEntity<StateDTO> update(@ApiParam(value = "State ID", example = "1", required = true)
                                    Long id,
                                    @ApiParam(name = "body", value = "Represents a new State", required = true)
                                    StateInput stateInput);

    @ApiOperation("Delete a State given an id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid State ID", response = ExceptionDetails.class),
            @ApiResponse(code = 204, message = "State deleted"),
            @ApiResponse(code = 404, message = "State not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> delete(@ApiParam(value = "State ID", example = "1", required = true)
                                Long id);
}
