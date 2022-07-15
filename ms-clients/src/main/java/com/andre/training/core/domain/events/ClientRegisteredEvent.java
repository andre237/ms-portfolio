package com.andre.training.core.domain.events;

import com.andre.training.core.domain.BaseEventSchema;
import com.andre.training.core.domain.entity.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

public record ClientRegisteredEvent(Client registeredClient) implements ExternalEvent {

    @Override
    public Object payload() {
        return new ExternalClientRegisteredEvent(
                registeredClient.getCpf(),
                registeredClient.getFullName(),
                registeredClient.getEmailAddress()
        );
    }

    @Data @EqualsAndHashCode(callSuper = true)
    private static class ExternalClientRegisteredEvent extends BaseEventSchema {

        private String cpf;
        private String fullName;
        private String email;

        public ExternalClientRegisteredEvent(String cpf, String fullName, String email) {
            super("__ClientRegistered__");
            this.cpf = cpf;
            this.fullName = fullName;
            this.email = email;
        }
    }

}
