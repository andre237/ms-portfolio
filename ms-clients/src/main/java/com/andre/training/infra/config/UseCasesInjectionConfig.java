package com.andre.training.infra.config;

import com.andre.training.core.domain.shared.EventPublisher;
import com.andre.training.core.application.usecases.FirstClientLoginUseCase;
import com.andre.training.core.domain.ports.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesInjectionConfig {

    @Bean
    public FirstClientLoginUseCase firstClientLoginUseCase(EventPublisher eventPublisher,
                                                           ClientRepository repository) {
        return new FirstClientLoginUseCase(eventPublisher, repository);
    }

}
