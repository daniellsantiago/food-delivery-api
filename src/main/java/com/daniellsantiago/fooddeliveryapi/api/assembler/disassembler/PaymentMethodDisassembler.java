package com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler;

import com.daniellsantiago.fooddeliveryapi.api.dto.input.PaymentMethodInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput) {
        return modelMapper.map(paymentMethodInput, PaymentMethod.class);
    }

    public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod) {
        modelMapper.map(paymentMethodInput, paymentMethod);
    }
}
