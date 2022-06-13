package com.andre.training.core.domain.shared;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface Event extends Serializable {

    default LocalDateTime timestamp() {
        return LocalDateTime.now();
    }

}
