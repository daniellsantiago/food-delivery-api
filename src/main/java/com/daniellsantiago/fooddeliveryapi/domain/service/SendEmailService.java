package com.daniellsantiago.fooddeliveryapi.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Set;

public interface SendEmailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        @Singular
        private Set<String> receivers;
        @NonNull
        private String subject;
        @NonNull
        private String messageContent;

    }
}
