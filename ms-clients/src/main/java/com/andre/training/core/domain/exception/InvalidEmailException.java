package com.andre.training.core.domain.exception;

import com.andre.training.core.domain.shared.BusinessException;
import lombok.Getter;

@Getter
public class InvalidEmailException extends BusinessException {

    public InvalidEmailException() {
        super("invalid.email");
    }
}
