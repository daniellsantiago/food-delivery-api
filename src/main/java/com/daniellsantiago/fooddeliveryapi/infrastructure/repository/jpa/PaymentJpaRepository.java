package com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa;

import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface PaymentJpaRepository extends JpaRepository<PaymentMethod, Long> {
    @Query("select max(updatedAt) from PaymentMethod")
    OffsetDateTime getDateLastUpdate();

    @Query("select updatedAt from PaymentMethod where id = :id")
    OffsetDateTime getDateLastUpdateById(Long id);
}
