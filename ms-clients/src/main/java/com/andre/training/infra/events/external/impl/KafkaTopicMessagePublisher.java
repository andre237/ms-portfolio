package com.andre.training.infra.events.external.impl;

import com.andre.training.core.domain.events.ExternalEvent;
import com.andre.training.infra.events.external.ExternalMessageEventPublisher;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.google.common.base.CaseFormat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j @Component
@Profile("kafka")
public class KafkaTopicMessagePublisher implements ExternalMessageEventPublisher {

    private final Map<String, String> topicsCache;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaTopicMessagePublisher(@Value("${system.topics}") List<String> availableTopics,
                                      KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicsCache = availableTopics.stream()
                .collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));

        System.out.println(this.topicsCache);
    }

    @Override
    public void publish(ExternalEvent event) {
        kafkaTemplate.send(this.buildTopicName(event), event.payload());
    }

    private String buildTopicName(ExternalEvent event) {
        String eventName = event.getClass().getSimpleName();
        String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, eventName);

        return Optional.ofNullable(topicsCache.get(snakeCaseName))
                .orElseThrow(() -> new UnsupportedOperationException(String.format("Topic not found for event %s", eventName)));
    }

}
