package com.andre.training.core.domain.ports;

import com.andre.training.core.domain.entity.Client;
import com.andre.training.core.domain.entity.Email;
import com.andre.training.core.shared.EntityRepository;

public interface ClientRepository extends EntityRepository<Client, Email> { }
