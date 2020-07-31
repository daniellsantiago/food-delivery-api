package com.daniellsantiago.fooddeliveryapi.infrastructure.service.email;

import com.daniellsantiago.fooddeliveryapi.core.email.EmailProperties;
import com.daniellsantiago.fooddeliveryapi.domain.service.SendEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//@Service - Using fake email
public class SmtpSendEmailService implements SendEmailService {

    @Autowired
    private  JavaMailSender mailSender;
    @Autowired
    private  EmailProperties emailProperties;
    @Autowired
    private  Configuration freemarkerConfig;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = createMimeMessage(message);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Could not send the email", e);
        }
    }

    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        String body = generateTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getReceivers().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        return mimeMessage;
    }

    protected String generateTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("It was not possible to create the email template", e);
        }
    }
}
