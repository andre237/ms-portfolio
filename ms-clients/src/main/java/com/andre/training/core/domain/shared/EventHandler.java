package com.andre.training.core.domain.shared;

public abstract class EventHandler<T extends Event> {

    @SuppressWarnings("unchecked")
    public void handle(Event event) {
        if (canProcess(event)) processInternal((T) event);
    }

    protected abstract boolean canProcess(Event event);
    protected abstract void processInternal(T event);

}
