package com.andre.training.infra.events;

import com.andre.training.core.domain.shared.Event;
import com.andre.training.core.domain.shared.EventHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CentralEventHub {

    private final List<EventHandler<?>> registeredHandlers;

    public CentralEventHub(List<EventHandler<?>> registeredHandlers) {
        this.registeredHandlers = registeredHandlers;
    }

    @EventListener
    public void handleEvent(Event event) {
        registeredHandlers.forEach(eventHandler -> eventHandler.handle(event));
    }

}
