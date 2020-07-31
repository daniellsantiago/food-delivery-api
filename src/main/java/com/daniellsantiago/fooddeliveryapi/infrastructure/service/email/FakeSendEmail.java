package com.daniellsantiago.fooddeliveryapi.infrastructure.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FakeSendEmail extends SmtpSendEmailService{
    @Override
    public void send(Message message) {
        String body = generateTemplate(message);

        log.info("[FAKE E-MAIL] To: {}\n{}", message.getReceivers(), body);
    }
}
