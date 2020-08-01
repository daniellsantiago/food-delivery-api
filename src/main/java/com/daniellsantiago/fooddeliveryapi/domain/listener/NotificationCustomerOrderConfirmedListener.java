package com.daniellsantiago.fooddeliveryapi.domain.listener;

import com.daniellsantiago.fooddeliveryapi.domain.event.OrderConfirmedEvent;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class NotificationCustomerOrderConfirmedListener {
    private final SendEmailService sendEmail;

    @TransactionalEventListener
    public void whenOrderIsConfirmed(OrderConfirmedEvent event) {
        Order order = event.getOrder();

        var message = SendEmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed")
                .body("order-confirmed.html")
                .receivers(Set.of(order.getCustomer().getEmail()))
                .variable("order", order)
                .build();
        sendEmail.send(message);
    }
}
