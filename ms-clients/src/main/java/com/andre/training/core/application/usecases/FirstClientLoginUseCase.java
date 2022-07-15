package com.andre.training.core.application.usecases;

import com.andre.training.core.application.UseCase;
import com.andre.training.core.domain.events.ClientRegisteredEvent;
import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.events.EventPublisher;
import com.andre.training.core.domain.ports.ClientRepository;
import com.andre.training.core.domain.stereotype.Injectable;

import java.time.LocalDateTime;

import static com.andre.training.core.application.usecases.FirstClientLoginUseCase.*;

@Injectable
public class FirstClientLoginUseCase extends UseCase<ClientIO, ClientIO> {

    private final EventPublisher eventPublisher;
    private final ClientRepository clientRepository;

    public FirstClientLoginUseCase(EventPublisher eventPublisher,
                                   ClientRepository clientRepository) {
        this.eventPublisher = eventPublisher;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientIO execute(ClientIO input) {
        Client savedClient = clientRepository.save(input.convertToClient());
        eventPublisher.publish(new ClientRegisteredEvent(savedClient));
        return ClientIO.convertClientToOutput(savedClient);
    }

    public record ClientIO(String cpf, String fullName, String email, LocalDateTime birthDate) implements UseCase.Input, UseCase.Output {

        Client convertToClient() {
            return new Client(cpf, fullName, email, birthDate);
        }

        static ClientIO convertClientToOutput(Client client) {
            return new ClientIO(client.getCpf(), client.getFullName(), client.getEmailAddress(), client.getBirthDate());
        }

    }
}
