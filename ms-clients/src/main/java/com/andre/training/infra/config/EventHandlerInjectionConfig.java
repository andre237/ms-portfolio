package com.andre.training.infra.config;

import com.andre.training.core.domain.events.ClientRegisteredEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHandlerInjectionConfig {

    @Bean
    public ClientRegisteredEventHandler clientRegisteredEventHandler() {
        return new ClientRegisteredEventHandler();
    }

}
