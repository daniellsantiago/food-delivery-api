package com.daniellsantiago.fooddeliveryapi.api.assembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodDTO.class);
    }

    public List<PaymentMethodDTO> toCollectionDTO(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
