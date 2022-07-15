package com.andre.training.infra.kafka.serde;

import com.andre.training.core.domain.BaseEventSchema;
import com.andre.training.core.domain.MessagingPayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j @Component
public class CustomKafkaJsonDeserializer implements Deserializer<BaseEventSchema> {

    private final ObjectMapper jsonParser = new ObjectMapper();
    private final HashMap<String, Class<?>> mappedSchemas = new HashMap<>();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MessagingPayload.class));
        scanner.findCandidateComponents("com.andre.training").stream()
                .map(this::buildBeanClassFromDefinition)
                .filter(BaseEventSchema.class::isAssignableFrom)
                .forEach(type -> mappedSchemas.put(type.getAnnotation(MessagingPayload.class).identifier(), type));
    }

    @Override
    public BaseEventSchema deserialize(String topic, byte[] payload) {
        try {
            JsonNode rootNode = jsonParser.readTree(payload);
            JsonNode payloadNode = rootNode.get("data");

            Class<?> identifier = mappedSchemas.get(rootNode.get("identifier").asText());
            if (identifier != null && payloadNode != null) {
                return (BaseEventSchema) jsonParser.treeToValue(payloadNode, identifier);
            }

            log.error("Could not find candidate schema to deserialization. Payload = {}", rootNode);
        } catch (Exception ex) {
            log.error("Deserialization failure {}", ex.getMessage());
        }

        return null;
    }

    @SneakyThrows
    private Class<?> buildBeanClassFromDefinition(BeanDefinition bd) {
        return Class.forName(bd.getBeanClassName());
    }

}