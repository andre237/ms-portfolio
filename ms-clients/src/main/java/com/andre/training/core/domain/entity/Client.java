package com.andre.training.core.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Client {

    private String fullName;
    private Email email;
    private LocalDateTime birthDate;
    private Address address;

    public void setEmail(String email) {
        this.email = new Email(email);
    }
}
