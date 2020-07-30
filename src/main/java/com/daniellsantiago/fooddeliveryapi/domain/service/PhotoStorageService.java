package com.daniellsantiago.fooddeliveryapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    InputStream recovery(String filename);

    void storage(NewPhoto newPhoto);

    void remove(String filename);

    default void substitute(String oldFilename, NewPhoto newPhoto) {
        this.storage(newPhoto);

        if (oldFilename != null) {
            this.remove(oldFilename);
        }
    }

    default String generateFilename(String originalName) {
        return UUID.randomUUID().toString() + "_" + originalName;
    }

    @Builder
    @Getter
    class NewPhoto {

        private String filename;
        private InputStream inputStream;

    }
}
