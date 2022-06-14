package com.andre.training.core.domain.events;

public interface EventPublisher {

    void publish(Event event);

}
