package com.andre.training.infra.events;

import com.andre.training.core.domain.events.Event;
import com.andre.training.core.domain.events.EventHandler;
import com.andre.training.core.domain.events.EventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringApplicationEventHub implements EventPublisher {

    private final List<EventHandler<?>> registeredHandlers;

    public SpringApplicationEventHub(List<EventHandler<?>> registeredHandlers) {
        this.registeredHandlers = registeredHandlers;
    }

    @Override
    public void publish(Event event) {
        registeredHandlers.forEach(eventHandler -> eventHandler.handle(event));
    }
}
