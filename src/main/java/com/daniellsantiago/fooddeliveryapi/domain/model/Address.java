package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {

    @Column(name = "address_zip")
    private String zip;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_additional_information")
    private String additionalInformation;

    @Column(name = "address_street")
    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;
}
