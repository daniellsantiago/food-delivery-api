package com.daniellsantiago.fooddeliveryapi.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @Embedded
    private Address address;

    private Boolean active = Boolean.TRUE;

    private Boolean open = Boolean.TRUE;

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
                    joinColumns = @JoinColumn(name = "restaurant_id"),
                    inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_user_responsible",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsibles = new HashSet<>();

    public void activate() {
        setActive(true);
    }

    public void inactivate() {
        setActive(false);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().add(paymentMethod);
    }

    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().remove(paymentMethod);
    }

    public void open() {
        setOpen(true);
    }

    public void close() {
        setOpen(false);
    }

    public boolean addResponsible(User user) {
        return getResponsibles().add(user);
    }

    public boolean removeResponsible(User user) {
        return getResponsibles().remove(user);
    }
}
