package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderBasicDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.OrderDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.OrderInputDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.OrderInput;
import com.daniellsantiago.fooddeliveryapi.api.openapi.controller.OrderControllerOpenApi;
import com.daniellsantiago.fooddeliveryapi.domain.filter.OrderFilter;
import com.daniellsantiago.fooddeliveryapi.domain.model.Order;
import com.daniellsantiago.fooddeliveryapi.domain.model.User;
import com.daniellsantiago.fooddeliveryapi.domain.repository.OrderRepository;
import com.daniellsantiago.fooddeliveryapi.domain.service.IssueOrderService;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.spec.OrderSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController implements OrderControllerOpenApi {

    private final OrderRepository orderRepository;
    private final IssueOrderService issueOrderService;
    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderBasicDTOAssembler orderBasicDTOAssembler;
    private final OrderInputDisassembler orderInputDisassembler;

    @GetMapping
    public Page<OrderBasicDTO> findAll(OrderFilter filter,
                                               @PageableDefault(size = 10)Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(OrderSpecs.usingFilter(filter), pageable);
        return orderBasicDTOAssembler.toCollectionDTO(orderPage);
    }

    @GetMapping("/{code}")
    public OrderDTO findByCode(@PathVariable String code) {
        Order order = issueOrderService.findByCode(code);

        return orderDTOAssembler.toDTO(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO add(@RequestBody @Valid OrderInput orderInput) {
        Order order = orderInputDisassembler.toDomainObject(orderInput);

        // TODO catch authenticated user
        order.setCustomer(new User());
        order.getCustomer().setId(1L);

        order = issueOrderService.issue(order);
        return orderDTOAssembler.toDTO(order);
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
