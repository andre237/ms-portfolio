package com.andre.training.infra.data.entities;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.infra.data.repositories.EntityDataPresenter;

public class ClientEntityDocument implements EntityDataPresenter<Client> {

    public static ClientEntityDocument from(Client client) {

    }

    @Override
    public Client convertToDomain() {
        return null;
    }
}
