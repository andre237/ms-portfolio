package com.andre.training.core.domain.events;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Event extends Serializable {

    default LocalDateTime timestamp() {
        return LocalDateTime.now();
    }

    default boolean shouldPublishToExternal() {
        return false;
    }

}
