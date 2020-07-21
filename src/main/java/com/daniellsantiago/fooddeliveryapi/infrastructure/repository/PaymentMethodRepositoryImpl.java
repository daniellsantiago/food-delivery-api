package com.daniellsantiago.fooddeliveryapi.infrastructure.repository;

import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PaymentMethodRepository;
import com.daniellsantiago.fooddeliveryapi.infrastructure.repository.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public PaymentMethod save(PaymentMethod entity) {
        return paymentJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        paymentJpaRepository.deleteById(id);
        paymentJpaRepository.flush();
    }

    @Override
    public Optional<PaymentMethod> findById(Long id) {
        return paymentJpaRepository.findById(id);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return paymentJpaRepository.findAll();
    }
}
