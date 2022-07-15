package com.andre.training.infra.events.external;

import com.andre.training.core.domain.events.ExternalEvent;

public interface ExternalMessageEventPublisher {

    void publish(ExternalEvent event);

}
