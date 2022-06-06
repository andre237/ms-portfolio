package com.andre.training.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaPropertySource {

    private BrokerConfig broker;
    private ProducerConfig producer;
    private ConsumerConfig consumer;

    public record BrokerSecurityConfig(String protocol, String mechanism, String username, String password) {}
    public record BrokerConfig(String bootstrapServers, String clientId, BrokerSecurityConfig security) {}
    public record ProducerConfig(String keySerializer, String valueSerializer) {}
    public record ConsumerConfig(String autoOffsetReset, String keyDeserializer, String valueDeserializer) {}

}
