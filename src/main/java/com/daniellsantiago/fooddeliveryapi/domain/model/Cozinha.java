package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
}
