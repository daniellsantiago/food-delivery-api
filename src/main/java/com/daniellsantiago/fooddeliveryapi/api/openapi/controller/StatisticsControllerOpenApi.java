package com.daniellsantiago.fooddeliveryapi.api.openapi.controller;

import com.daniellsantiago.fooddeliveryapi.domain.filter.DailySaleFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.dto.DailySale;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Statistics")
public interface StatisticsControllerOpenApi {

    @ApiOperation("Consult daily sales")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restaurantId", value = "Restaurant ID",
                    example = "1", dataType = "int"),
            @ApiImplicitParam(name = "initialCreationDate", value = "Date/time that started the order creation time",
                    example = "2019-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "endCreationDate", value = "Date/time that ended the order creation time",
                    example = "2019-12-02T23:59:59Z", dataType = "date-time")
    })
    List<DailySale>  findDailySales(DailySaleFilter filter,
                                    @ApiParam(value = "Time to be considered in the query in relation to UTC",
                                              defaultValue = "+00:00")
                                    String timeOffset);
}
