package com.andre.training.infra.rest.presenters;

import com.andre.training.core.application.usecases.FirstClientLoginUseCase;
import com.andre.training.gateway.request.RequestMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data @EqualsAndHashCode(callSuper = false)
public class FirstLoginRequest extends RequestMapper<FirstClientLoginUseCase.ClientIO> {

    @NotNull @NotEmpty
    private String cpf;

    @NotNull @NotEmpty
    private String fullName;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private String birthDate;

    @Override
    public FirstClientLoginUseCase.ClientIO map() {
        return new FirstClientLoginUseCase.ClientIO(cpf, fullName, email, LocalDateTime.parse(birthDate));
    }
}
