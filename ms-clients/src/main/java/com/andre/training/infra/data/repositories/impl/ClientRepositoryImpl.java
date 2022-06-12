package com.andre.training.infra.data.repositories.impl;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.entity.Email;
import com.andre.training.core.domain.ports.ClientRepository;
import com.andre.training.infra.data.entities.ClientEntityDocument;
import com.andre.training.infra.data.repositories.mongo.ClientMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private ClientMongoRepository mongoRepository;

    @Override
    public Client save(Client entity) {
        var savedDocument = mongoRepository.save(ClientEntityDocument.from(entity));
        return savedDocument.convertToDomain();
    }

    @Override
    public Client findById(Email id) {
        return null;
    }

    @Override
    public Set<Client> findAll() {
        return null;
    }
}
