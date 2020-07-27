package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFlowService {

    private final IssueOrderService issueOrderService;

    @Transactional
    public void confirm(String code) {
        Order order = issueOrderService.findByCode(code);
        order.confirm();
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
