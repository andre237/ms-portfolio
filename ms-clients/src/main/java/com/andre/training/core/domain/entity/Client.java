package com.andre.training.core.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class Client {

    private String cpf;
    private String fullName;
    private Email email;
    private LocalDateTime birthDate;
    private Address address;

    public Client() { }

    public Client(String cpf, String fullName, String email, LocalDateTime birthDate) {
        this.cpf = cpf;
        this.fullName = fullName;
        this.email = new Email(email);
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getEmailAddress() {
        return Optional.ofNullable(this.email).map(Email::getAddress).orElse(null);
    }

}
