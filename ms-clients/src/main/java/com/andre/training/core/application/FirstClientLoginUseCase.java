package com.andre.training.core.application;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.ports.ClientRepository;
import com.andre.training.core.shared.UseCase;
import com.andre.training.infra.stereotype.UseCaseComponent;

import java.time.LocalDateTime;

import static com.andre.training.core.application.FirstClientLoginUseCase.*;

@UseCaseComponent
public class FirstClientLoginUseCase extends UseCase<ClientIO, ClientIO> {

    private final ClientRepository clientRepository;

    public FirstClientLoginUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientIO execute(ClientIO input) {
        Client savedClient = clientRepository.save(input.convertToClient());
        return ClientIO.convertClientToOutput(savedClient);
    }

    public record ClientIO(String fullName, String email, LocalDateTime birthDate) implements UseCase.Input, UseCase.Output {

        Client convertToClient() {
            var domainClient = new Client();
            domainClient.setFullName(fullName);
            domainClient.setEmail(email);
            domainClient.setBirthDate(birthDate);
            return domainClient;
        }

        static ClientIO convertClientToOutput(Client client) {
            return new ClientIO(client.getFullName(), client.getEmail().getAddress(), client.getBirthDate());
        }

    }
}
