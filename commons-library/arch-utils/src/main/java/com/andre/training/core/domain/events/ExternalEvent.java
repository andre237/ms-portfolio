package com.andre.training.core.domain.events;

public interface ExternalEvent extends Event {

    Object payload();

    @Override
    default boolean shouldPublishToExternal() {
        return true;
    }
}
