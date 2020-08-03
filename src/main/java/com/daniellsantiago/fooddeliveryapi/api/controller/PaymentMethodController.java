package com.daniellsantiago.fooddeliveryapi.api.controller;

import com.daniellsantiago.fooddeliveryapi.api.assembler.PaymentMethodDTOAssembler;
import com.daniellsantiago.fooddeliveryapi.api.assembler.disassembler.PaymentMethodDisassembler;
import com.daniellsantiago.fooddeliveryapi.api.dto.PaymentMethodDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.input.PaymentMethodInput;
import com.daniellsantiago.fooddeliveryapi.domain.model.PaymentMethod;
import com.daniellsantiago.fooddeliveryapi.domain.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment-method")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final PaymentMethodService paymentService;

    private final PaymentMethodDTOAssembler DTOAssembler;

    private final PaymentMethodDisassembler disassembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAll(ServletWebRequest request) {

        String eTag = "0";

        OffsetDateTime dateLastUpdate = paymentService.getDateLastUpdate();

        if (dateLastUpdate != null) {
            eTag = String.valueOf(dateLastUpdate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<PaymentMethod> allPaymentMethods = paymentService.findAll();

        if(allPaymentMethods.isEmpty())
            return ResponseEntity.noContent().build();

        List<PaymentMethodDTO> paymentMethodDTOS = DTOAssembler
                .toCollectionDTO(allPaymentMethods);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> findById(@PathVariable Long id, ServletWebRequest request) {
        String eTag = "0";

        OffsetDateTime dateLastUpdate = paymentService.getDateLastUpdateById(id);

        if (dateLastUpdate != null) {
            eTag = String.valueOf(dateLastUpdate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        PaymentMethod paymentMethod = paymentService.findById(id);

        PaymentMethodDTO paymentMethodDTO = DTOAssembler.toDTO(paymentMethod);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodDTO);
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
