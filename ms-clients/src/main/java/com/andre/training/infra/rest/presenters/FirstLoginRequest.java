package com.andre.training.infra.rest.presenters;

import com.andre.training.core.application.FirstClientLoginUseCase;
import com.andre.training.gateway.request.RequestMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data @EqualsAndHashCode(callSuper = false)
public class FirstLoginRequest extends RequestMapper<FirstClientLoginUseCase.ClientInput> {

    private String fullName;
    private String email;
    private String birthDate;

    @Override
    public FirstClientLoginUseCase.ClientInput map() {
        return new FirstClientLoginUseCase.ClientInput(fullName, email, LocalDateTime.parse(birthDate));
    }
}
