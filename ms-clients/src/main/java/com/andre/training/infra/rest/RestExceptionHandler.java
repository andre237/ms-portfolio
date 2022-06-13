package com.andre.training.infra.rest;

import com.andre.training.core.shared.BusinessException;
import com.andre.training.gateway.response.RestResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j @Component
@ControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public RestResult<String> handleBusinessException(BusinessException exception) {
        log.error("Business error", exception);

        var result = new RestResult<String>();
        result.setData("error");
        result.setError(exception.getMessage());
        return result;
    }

}
