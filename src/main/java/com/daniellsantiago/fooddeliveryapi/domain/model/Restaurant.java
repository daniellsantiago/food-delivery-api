package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;
}
