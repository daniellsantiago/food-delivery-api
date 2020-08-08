package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.api.dto.PermissionDTO;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Roles")
public interface RolePermissionControllerOpenApi {

    @ApiOperation("Find all permissions associated with a Role")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Roles registered"),
            @ApiResponse(code = 404, message = "Role not found", response = ExceptionDetails.class)
    })
    ResponseEntity<List<PermissionDTO>> findAll(@ApiParam(value = "Role ID", example = "1", required = true)
                                                Long roleId);

    @ApiOperation("Associate Permission to Role")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Association succeed", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Role or Permission not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> associate(@ApiParam(value = "Role ID", example = "1", required = true)
                                   Long roleId,
                                   @ApiParam(value = "Permission ID", example = "1", required = true)
                                   Long permissionId);

    @ApiOperation("Disassociate Permission to Role")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Disassociation succeed", response = ExceptionDetails.class),
            @ApiResponse(code = 404, message = "Role or Permission not found", response = ExceptionDetails.class)
    })
    ResponseEntity<Void> disassociate(@ApiParam(value = "Role ID", example = "1", required = true)
                                           Long roleId,
                                   @ApiParam(value = "Permission ID", example = "1", required = true)
                                           Long permissionId);
}
