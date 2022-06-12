package com.andre.training.infra.config;

import com.andre.training.core.application.FirstClientLoginUseCase;
import com.andre.training.core.domain.ports.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesInjectionConfig {

    @Bean
    public FirstClientLoginUseCase firstClientLoginUseCase(ClientRepository repository) {
        return new FirstClientLoginUseCase(repository);
    }

}
