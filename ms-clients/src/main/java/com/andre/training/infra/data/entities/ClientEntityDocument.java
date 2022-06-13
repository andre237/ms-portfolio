package com.andre.training.infra.data.entities;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.infra.data.repositories.EntityDataPresenter;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "clients")
public class ClientEntityDocument implements EntityDataPresenter<Client> {

    @Id
    private String id;

    private String fullName;
    private String email;
    private LocalDateTime birthDate;

    public ClientEntityDocument(String fullName, String email, LocalDateTime birthDate) {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public static ClientEntityDocument from(Client client) {
        return new ClientEntityDocument(client.getFullName(), client.getEmail().getAddress(), client.getBirthDate());
    }

    @Override
    public Client convertToDomain() {
        var domainClient = new Client();
        domainClient.setFullName(fullName);
        domainClient.setEmail(email);
        domainClient.setBirthDate(birthDate);
        return domainClient;
    }
}

