package com.andre.training.infra.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j @Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaComponentsConfig {

    private final KafkaPropertySource configurationSource;

    public Map<String, Object> kafkaProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, configurationSource.getBroker().bootstrapServers());
        if (Objects.nonNull(configurationSource.getBroker().security())) {
            props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, configurationSource.getBroker().security().protocol());
            props.put(SaslConfigs.SASL_MECHANISM, configurationSource.getBroker().security().mechanism());
            props.put(SaslConfigs.SASL_JAAS_CONFIG, this.buildJaasLoginModule(configurationSource.getBroker().security()));
        }

        return props;
    }

    public Map<String, Object> kafkaConsumerProps() {
        Map<String, Object> props = new HashMap<>(kafkaProps());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, configurationSource.getBroker().clientId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, configurationSource.getConsumer().keyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, configurationSource.getConsumer().valueDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, configurationSource.getConsumer().autoOffsetReset());
        return props;
    }

    public Map<String, Object> kafkaProducerProps() {
        Map<String, Object> props = new HashMap<>(kafkaProps());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, configurationSource.getProducer().keySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, configurationSource.getProducer().valueSerializer());
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaConsumerProps());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProducerProps()));
    }

    private String buildJaasLoginModule(KafkaPropertySource.BrokerSecurityConfig securityConfig) {
        final String authSection = String.format("username=\"%s\" password=\"%s\";", securityConfig.username(), securityConfig.password());
        return switch (securityConfig.mechanism()) {
            case "SCRAM-SHA-512", "SCRAM-SHA-256" -> "org.apache.kafka.common.security.scram.ScramLoginModule required".concat(authSection);
            case "OAUTHBEARER" -> "org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule ".concat(authSection);
            default -> "org.apache.kafka.common.security.plain.PlainLoginModule required ".concat(authSection);
        };
    }

}
