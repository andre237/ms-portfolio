package com.andre.training.core.domain.entity;

import com.andre.training.core.domain.exception.InvalidEmailException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientTest {

    private Client client;

    @Before
    public void setup() {
        client = new Client();
        client.setFullName("Test client");
    }

    @Test
    public void shouldThrowErrorOnInvalidEmail() {
        Assertions.assertThrows(InvalidEmailException.class, () -> client.setEmail("wrongemail.com"));
    }

}
