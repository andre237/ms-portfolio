package com.andre.training.core.domain.events;

import com.andre.training.core.domain.entity.Client;

public record ClientRegisteredEvent(Client registeredClient) implements Event {}
