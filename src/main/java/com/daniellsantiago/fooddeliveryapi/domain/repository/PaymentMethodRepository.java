package com.daniellsantiago.fooddeliveryapi.domain.repository;


import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository {
    PaymentMethod save(PaymentMethod entity);
    void deleteById(Long id);
    Optional<PaymentMethod> findById(Long id);
    List<PaymentMethod> findAll();
    OffsetDateTime getDateLastUpdate();
    OffsetDateTime getDateLastUpdateById(long id);
}
