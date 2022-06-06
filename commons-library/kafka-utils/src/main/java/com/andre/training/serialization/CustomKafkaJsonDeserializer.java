package com.andre.training.serialization;

import com.andre.training.schema.BaseSchema;
import com.andre.training.schema.KafkaPayload;
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
public class CustomKafkaJsonDeserializer implements Deserializer<BaseSchema> {

    private final ObjectMapper jsonParser = new ObjectMapper();
    private final HashMap<String, Class<?>> mappedSchemas = new HashMap<>();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(KafkaPayload.class));
        scanner.findCandidateComponents("com.andre.training").stream()
                .map(this::buildBeanClassFromDefinition)
                .filter(BaseSchema.class::isAssignableFrom)
                .forEach(type -> mappedSchemas.put(type.getAnnotation(KafkaPayload.class).identifier(), type));
    }

    @Override
    public BaseSchema deserialize(String topic, byte[] payload) {
        try {
            JsonNode rootNode = jsonParser.readTree(payload);
            JsonNode payloadNode = rootNode.get("data");

            Class<?> identifier = mappedSchemas.get(rootNode.get("identifier").asText());
            if (identifier != null && payloadNode != null) {
                return (BaseSchema) jsonParser.treeToValue(payloadNode, identifier);
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
