package com.daniellsantiago.fooddeliveryapi.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Setter
@Getter
public class OrderFilter {

    @ApiModelProperty(value = "Customer ID to filter response")
    private Long customerId;
    @ApiModelProperty(value = "Restaurant ID to filter response")
    private Long restaurantId;

    @ApiModelProperty(value = "Initial creation date / time to filter response")
    @DateTimeFormat(iso = ISO.DATE_TIME )
    private OffsetDateTime initialCreationDate;

    @ApiModelProperty(value = "End creation date / time to filter response")
    @DateTimeFormat(iso = ISO.DATE_TIME )
    private OffsetDateTime endCreationDate;

    @Override
    public String toString() {
        return "OrderFilter{" +
                "customerId=" + customerId +
                ", restaurantId=" + restaurantId +
                ", initialCreationDate=" + initialCreationDate +
                ", endCreationDate=" + endCreationDate +
                '}';
    }
}
