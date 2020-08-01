package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFlowService {

    private final IssueOrderService issueOrderService;
    private final SendEmailService sendEmail;
    private final OrderRepository orderRepository;

    @Transactional
    public void confirm(String code) {
        Order order = issueOrderService.findByCode(code);
        order.confirm();
        orderRepository.save(order);
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
