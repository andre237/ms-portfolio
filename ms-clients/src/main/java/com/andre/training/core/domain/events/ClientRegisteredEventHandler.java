package com.andre.training.core.domain.events;

import com.andre.training.core.domain.stereotype.Injectable;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Injectable
public class ClientRegisteredEventHandler extends EventHandler<ClientRegisteredEvent> {

    @Override
    protected void processInternal(ClientRegisteredEvent event) {
        log.info("{} registered at {}", event.registeredClient(), event.timestamp());
    }

    @Override
    protected boolean canProcess(Event event) {
        return event instanceof ClientRegisteredEvent;
    }


}
