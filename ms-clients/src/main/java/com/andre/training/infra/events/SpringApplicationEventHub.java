package com.andre.training.infra.events;

import com.andre.training.core.domain.events.Event;
import com.andre.training.core.domain.events.EventHandler;
import com.andre.training.core.domain.events.EventPublisher;
import com.andre.training.core.domain.events.ExternalEvent;
import com.andre.training.infra.events.external.ExternalMessageEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringApplicationEventHub implements EventPublisher {

    private final List<EventHandler<?>> registeredHandlers;
    private final ExternalMessageEventPublisher externalEventPublisher;

    public SpringApplicationEventHub(List<EventHandler<?>> registeredHandlers,
                                     ExternalMessageEventPublisher externalEventPublisher) {
        this.registeredHandlers = registeredHandlers;
        this.externalEventPublisher = externalEventPublisher;
    }

    @Override
    public void publish(Event event) {
        registeredHandlers.forEach(eventHandler -> eventHandler.handle(event));
        if (event instanceof ExternalEvent && event.shouldPublishToExternal()) {
            externalEventPublisher.publish((ExternalEvent) event);
        }
    }
}
