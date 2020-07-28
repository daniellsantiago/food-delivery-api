package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderBasicDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.OrderInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderInput;
import com.daniellsantiago.fooddeliveryapi.core.data.PageableTranslator;
import com.daniellsantiago.fooddeliveryapi.domain.filter.OrderFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import com.daniellsantiago.fooddeliveryapi.domain.service.IssueOrderService;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.spec.OrderSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Page<OrderBasicDTO>> findAll(OrderFilter filter,
                                               @PageableDefault(size = 10)Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(OrderSpecs.usingFilter(filter), pageable);

        List<OrderBasicDTO> orderBasicDTOS = orderBasicDTOAssembler.toCollectionDTO(orderPage.getContent());

        Page<OrderBasicDTO> orderBasicDTOPage = new PageImpl<>(
                orderBasicDTOS, pageable, orderPage.getTotalElements()
        );

        return ResponseEntity.ok(orderBasicDTOPage);
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

    //To customize the sort parameters
//    private Pageable translatePageable(Pageable apiPageable) {
//        var mapping = Map.of(
//                "code", "code",
//                "subTotal", "subTotal",
//                "shippingRate", "shippingRate",
//                "totalPrice", "totalPrice",
//                "createdAt", "createdAt",
//                "restaurant.name", "restaurant.name",
//                "restaurant.id", "restaurant.id",
//                "customer.id", "customer.id",
//                "customer.name", "customer.name"
//        );
//
//        return PageableTranslator.translate(apiPageable, mapping);
//    }
}
