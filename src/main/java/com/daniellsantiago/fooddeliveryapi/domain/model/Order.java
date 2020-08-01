package com.daniellsantiago.fooddeliveryapi.domain.model;

import com.daniellsantiago.fooddeliveryapi.domain.event.OrderCancelledEvent;
import com.daniellsantiago.fooddeliveryapi.domain.event.OrderConfirmedEvent;
import com.daniellsantiago.fooddeliveryapi.domain.exception.BussinessRuleException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "`order`")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BigDecimal subTotal;
    private BigDecimal shippingRate;
    private BigDecimal totalPrice;

    @Embedded
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private OffsetDateTime confirmedAt;
    private OffsetDateTime cancelledAt;
    private OffsetDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public void calculateTotalPrice() {
        getItems().forEach(OrderItem::calculateTotalPrice);

        this.subTotal = getItems().stream()
                            .map(OrderItem::getTotalPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalPrice = this.subTotal.add(this.shippingRate);
    }

    public void confirm() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmedAt(OffsetDateTime.now());

        registerEvent(new OrderConfirmedEvent(this));
    }

    public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELLED);
        setCancelledAt(OffsetDateTime.now());

        registerEvent(new OrderCancelledEvent(this));
    }

    private void setStatus(OrderStatus newStatus) {
        if(getStatus().cantChangeTo(newStatus)){
            throw new BussinessRuleException(
                    String.format("Order status %s cannot be changed from %s to %s",
                            getCode(), getStatus().getDescription(),
                            newStatus.getDescription()));
        }
        this.status = newStatus;
    }

    @PrePersist
    private void generateCode() {
        setCode(UUID.randomUUID().toString());
    }
}
