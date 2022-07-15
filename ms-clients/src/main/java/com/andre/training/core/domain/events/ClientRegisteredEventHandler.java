package com.andre.training.core.domain.events;

import com.andre.training.core.domain.ports.NotificationResource;
import com.andre.training.core.domain.stereotype.Injectable;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Injectable
public class ClientRegisteredEventHandler extends EventHandler<ClientRegisteredEvent> {

//    private final NotificationResource notificationResource;
//
//    public ClientRegisteredEventHandler(NotificationResource notificationResource) {
//        this.notificationResource = notificationResource;
//    }

    @Override
    protected void processInternal(ClientRegisteredEvent event) {
        log.info("{} registered at {}", event.registeredClient(), event.timestamp());
        //notificationResource.saveClientContactMethod();
    }

    @Override
    protected boolean canProcess(Event event) {
        return event instanceof ClientRegisteredEvent;
    }


}
