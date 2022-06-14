package com.andre.training.core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationStartupException extends RuntimeException {

    private String cause;

}
