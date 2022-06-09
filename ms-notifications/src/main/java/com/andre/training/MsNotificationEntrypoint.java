package com.andre.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsNotificationEntrypoint implements CommandLineRunner {

    @Value("${app.random-prop}")
    private String validationProp;

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationEntrypoint.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(" ====> " + validationProp + " <==== ");
    }
}
