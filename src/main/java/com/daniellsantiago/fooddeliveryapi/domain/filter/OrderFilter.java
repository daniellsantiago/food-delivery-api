package com.daniellsantiago.fooddeliveryapi.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Setter
@Getter
public class OrderFilter {

    private Long customerId;
    private Long restaurantId;

    @DateTimeFormat(iso = ISO.DATE_TIME )
    private OffsetDateTime initialCreationDate;

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
