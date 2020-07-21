package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PaymentMethodDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.PaymentMethodDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PaymentMethodInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import com.daniellsantiago.fooddeliveryapi.domain.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final PaymentMethodService paymentService;

    private final PaymentMethodDTOAssembler DTOAssembler;

    private final PaymentMethodDisassembler disassembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAll() {
        List<PaymentMethod> paymentList = paymentService.findAll();

        if(paymentList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(DTOAssembler.toCollectionDTO(paymentList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(DTOAssembler.toDTO(paymentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> save(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethod = disassembler.toDomainObject(paymentMethodInput);

        paymentMethod = paymentService.save(paymentMethod);

        return new ResponseEntity<>(DTOAssembler.toDTO(paymentMethod), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> update(@RequestBody @Valid PaymentMethodInput paymentMethodInput,
                                                   @PathVariable Long id){
        PaymentMethod paymentToBeUpdated = paymentService.findById(id);

        disassembler.copyToDomainObject(paymentMethodInput, paymentToBeUpdated);

        PaymentMethodDTO updatedPayment = DTOAssembler.toDTO(paymentService.save(paymentToBeUpdated));

        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
