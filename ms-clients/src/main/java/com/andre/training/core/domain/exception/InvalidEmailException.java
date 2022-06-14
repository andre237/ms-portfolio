package com.andre.training.core.domain.exception;

import lombok.Getter;

@Getter
public class InvalidEmailException extends BusinessException {

    public InvalidEmailException() {
        super("invalid.email");
    }
}
