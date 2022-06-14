package com.andre.training.core.application.usecases;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.events.EventPublisher;
import com.andre.training.core.domain.ports.ClientRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FirstLoginTest {

    private FirstClientLoginUseCase useCase;

    @Mock private EventPublisher eventPublisher;
    @Mock private ClientRepository clientRepository;

    @Before
    public void setUp() {
        doNothing().when(eventPublisher).publish(any());
        doReturn(this.mockDomainData()).when(clientRepository).save(any());

        useCase = new FirstClientLoginUseCase(eventPublisher, clientRepository);
    }

    @Test
    public void shouldSaveAndPublishEventOnExecution() {
        useCase.execute(this.mockIOData());

        verify(eventPublisher, times(1)).publish(any());
        verify(clientRepository, times(1)).save(any());
    }

    private Client mockDomainData() {
        return new Client("test name", "test@email.com", LocalDateTime.MAX);
    }

    private FirstClientLoginUseCase.ClientIO mockIOData() {
        return new FirstClientLoginUseCase.ClientIO("test name", "test@email.com", LocalDateTime.now());
    }
}
