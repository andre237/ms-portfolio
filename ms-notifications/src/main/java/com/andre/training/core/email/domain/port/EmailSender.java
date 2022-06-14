package com.andre.training.core.email.domain.port;

import java.util.List;

public interface EmailSender {

    void sendEmail(String subject, String body, List<String> recipients);

}
