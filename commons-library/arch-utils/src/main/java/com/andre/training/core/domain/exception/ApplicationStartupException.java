package com.andre.training.core.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationStartupException extends RuntimeException {

    public ApplicationStartupException(String message) {
        super(message);
    }
}
