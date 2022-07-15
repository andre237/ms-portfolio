package com.andre.training.infra.data.entities;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.infra.data.repositories.EntityDataPresenter;
import com.andre.training.util.ObjectCopy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document(collection = "clients")
@NoArgsConstructor @AllArgsConstructor
public class ClientEntityDocument implements EntityDataPresenter<Client> {

    @Id
    private String cpf;
    private String fullName;
    private String email;
    private LocalDateTime birthDate;

    public static ClientEntityDocument from(Client client) {
        return new ClientEntityDocument(client.getCpf(), client.getFullName(), client.getEmailAddress(), client.getBirthDate());
    }

    @Override
    public Client convertToDomain() {
        return new Client(cpf, fullName, email, birthDate);
    }
}

