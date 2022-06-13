package com.andre.training.core.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Client {

    private String fullName;
    private Email email;
    private LocalDateTime birthDate;
    private Address address;

    public Client() { }

    public Client(String fullName, Email email, LocalDateTime birthDate) {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Client(String fullName, String email, LocalDateTime birthDate) {
        this.fullName = fullName;
        this.email = new Email(email);
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }
}
