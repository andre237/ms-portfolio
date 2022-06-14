package com.andre.training.core.domain.ports;

import com.andre.training.core.domain.EntityRepository;
import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.entity.Email;

public interface ClientRepository extends EntityRepository<Client, Email> { }
