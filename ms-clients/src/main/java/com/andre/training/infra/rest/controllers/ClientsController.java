package com.andre.training.infra.rest.controllers;

import com.andre.training.core.application.FirstClientLoginUseCase;
import com.andre.training.gateway.AbstractRestController;
import com.andre.training.gateway.response.RestResult;
import com.andre.training.infra.rest.presenters.FirstLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientsController extends AbstractRestController {

    private final FirstClientLoginUseCase firstClientLoginUseCase;

    @Autowired
    public ClientsController(FirstClientLoginUseCase firstClientLoginUseCase) {
        this.firstClientLoginUseCase = firstClientLoginUseCase;
    }

    @PostMapping("sign-up")
    public RestResult<Object> createFirstLogin(@RequestBody FirstLoginRequest firstLoginRequest) {
        var output = firstClientLoginUseCase.execute(firstLoginRequest.map());
        return getRestResult(output);
    }

}

