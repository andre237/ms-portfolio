package com.andre.training.infra.rest.presenters;

import com.andre.training.core.application.FirstClientLoginUseCase;
import com.andre.training.gateway.request.RequestMapper;

public class FirstLoginRequest extends RequestMapper<FirstClientLoginUseCase.ClientInput> {

    @Override
    public FirstClientLoginUseCase.ClientInput map() {
        return null;
    }
}
