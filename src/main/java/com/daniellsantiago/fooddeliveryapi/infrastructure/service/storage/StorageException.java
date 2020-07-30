package com.daniellsantiago.fooddeliveryapi.infrastructure.service.storage;

import com.daniellsantiago.fooddeliveryapi.domain.exception.BussinessRuleException;

public class StorageException extends BussinessRuleException {

    public StorageException(String message) {
        super(message);
    }

}
