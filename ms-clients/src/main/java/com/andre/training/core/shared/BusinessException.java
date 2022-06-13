package com.andre.training.core.shared;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
