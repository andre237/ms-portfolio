package com.andre.training.infra.events;

import com.andre.training.core.domain.shared.Event;
import com.andre.training.core.domain.shared.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher delegatedPublisher;

    public SpringApplicationEventPublisher(ApplicationEventPublisher delegatedPublisher) {
        this.delegatedPublisher = delegatedPublisher;
    }

    @Override
    public void publish(Event event) {
        delegatedPublisher.publishEvent(event);
    }
}
