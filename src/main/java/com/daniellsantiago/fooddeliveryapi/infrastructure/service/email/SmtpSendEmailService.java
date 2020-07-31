package com.daniellsantiago.fooddeliveryapi.infrastructure.service.email;

import com.daniellsantiago.fooddeliveryapi.core.email.EmailProperties;
import com.daniellsantiago.fooddeliveryapi.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class SmtpSendEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getSender());
            helper.setTo(message.getReceivers().toArray(new String[0]));
            helper.setSubject(message.getSubject());
            helper.setText(message.getMessageContent(), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Could not send the email.", e);
        }
    }
}
