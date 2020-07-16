package com.daniellsantiago.fooddeliveryapi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {

    private String zip;

    private String number;

    private String additionalInformation;

    private String street;

    private CityBasicDTO city;
}
