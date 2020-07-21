package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
