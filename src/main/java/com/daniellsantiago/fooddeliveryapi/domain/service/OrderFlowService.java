package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniellsantiago.fooddeliveryapi.domain.service.SendEmailService.Message;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderFlowService {

    private final IssueOrderService issueOrderService;
    private final SendEmailService sendEmail;

    @Transactional
    public void confirm(String code) {
        Order order = issueOrderService.findByCode(code);
        order.confirm();

        var message = Message.builder()
                            .subject(order.getRestaurant().getName() + " - Order confirmed")
                            .messageContent("Order with code <strong>" + order.getCode() + "</strong> has been confirmed.")
                            .receivers(Set.of("danielfake780@gmail.com"))
                            .build();
        sendEmail.send(message);
    }

    @Transactional
    public void cancel(String code) {
        Order order = issueOrderService.findByCode(code);
        order.cancel();
    }

    @Transactional
    public void deliver(String code) {
        Order order = issueOrderService.findByCode(code);
        order.deliver();
    }
}
