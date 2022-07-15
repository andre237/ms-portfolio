package com.andre.training.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;

@Configuration
public class EmailSenderBeansConfig {

    @Bean
    public JavaMailSender mailSender() {
        return new JavaMailSenderImpl(); // customize later
    }

}
