package com.andre.training.infra.email;

import com.andre.training.core.email.domain.port.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j @Component
public class SpringMailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    public SpringMailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String subject, String body, List<String> recipients) {
        log.info("Sending some email with Java -> {}", javaMailSender.toString());
    }
}
