package com.andre.training.core.application;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.ports.ClientRepository;
import com.andre.training.core.shared.UseCase;

import java.time.LocalDateTime;

import static com.andre.training.core.application.FirstClientLoginUseCase.*;

public class FirstClientLoginUseCase extends UseCase<ClientInput, ClientOutput> {

    private final ClientRepository clientRepository;

    public FirstClientLoginUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientOutput execute(ClientInput input) {
        Client client = input.convertToClient();
        clientRepository.save(client);
        return null;
    }

    public record ClientInput(String fullName, String email, LocalDateTime birthDate) implements UseCase.Input {
        Client convertToClient() {
            return null;
        }

    }

    public record ClientOutput() implements UseCase.Output {}

}
