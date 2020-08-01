package com.daniellsantiago.fooddeliveryapi.domain.event;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderCancelledEvent {
    private Order order;
}
