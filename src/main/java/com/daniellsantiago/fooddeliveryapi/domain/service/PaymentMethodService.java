package com.daniellsantiago.fooddeliveryapi.domain.service;

import com.daniellsantiago.fooddeliveryapi.domain.exception.EntityInUseException;
import com.daniellsantiago.fooddeliveryapi.domain.exception.ResourceNotFoundException;
import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import com.daniellsantiago.fooddeliveryapi.domain.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void delete(Long id) {
        try {
            paymentMethodRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Payment Method with id " + id + " not found");
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("Payment Method with id " + id + " is associated with another Entity");
        }
    }

    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Method with id " + id + " not found"));
    }

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }
}
