package com.andre.training.infra.data.repositories.mongo;

import com.andre.training.infra.data.entities.ClientEntityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoRepository extends MongoRepository<ClientEntityDocument, String> {

}
