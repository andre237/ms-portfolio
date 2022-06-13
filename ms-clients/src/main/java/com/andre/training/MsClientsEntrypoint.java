package com.andre.training;

import com.andre.training.infra.config.InjectableAnnotatedBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsClientsEntrypoint {

    public static void main(String[] args) {
        SpringApplication.run(MsClientsEntrypoint.class, args);
    }

    @Bean
    public InjectableAnnotatedBeanFactory injectableAnnotatedBeanFactory() {
        return new InjectableAnnotatedBeanFactory();
    }

}
