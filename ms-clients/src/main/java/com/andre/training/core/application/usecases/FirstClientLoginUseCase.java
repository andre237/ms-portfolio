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
