package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderBasicDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.OrderInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import com.daniellsantiago.fooddeliveryapi.domain.service.IssueOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final IssueOrderService issueOrderService;
    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderBasicDTOAssembler orderBasicDTOAssembler;
    private final OrderInputDisassembler orderInputDisassembler;

    @GetMapping
    public ResponseEntity<List<OrderBasicDTO>> findAll() {
        List<Order> orders = orderRepository.findAll();

        List<OrderBasicDTO> ordersDTO = orderBasicDTOAssembler.toCollectionDTO(orders);
        if(orders.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ordersDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrderDTO> findById(@PathVariable String code) {
        Order order = issueOrderService.findByCode(code);

        return ResponseEntity.ok(orderDTOAssembler.toDTO(order));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> add(@RequestBody @Valid OrderInput orderInput) {
        Order order = orderInputDisassembler.toDomainObject(orderInput);

        order.setCustomer(new User());
        order.getCustomer().setId(1L);

        order = issueOrderService.issue(order);
        return new ResponseEntity<>(orderDTOAssembler.toDTO(order), HttpStatus.CREATED);
    }
}
