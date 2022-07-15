package com.andre.training.infra.kafka.serde;

import com.andre.training.core.domain.BaseEventSchema;
import com.andre.training.core.domain.MessagingPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j @Component
public class CustomKafkaJsonSerializer implements Serializer<BaseEventSchema> {

    private final ObjectMapper jsonParser = new ObjectMapper();

    @SneakyThrows
    @Override
    public byte[] serialize(String s, BaseEventSchema baseSchema) {
        String identifier = Optional.ofNullable(baseSchema.getClass().getAnnotation(MessagingPayload.class))
                .map(MessagingPayload::identifier).orElse(baseSchema.getIdentifier());

        ObjectNode rootNode = jsonParser.createObjectNode();
        rootNode.put("identifier", identifier);
        rootNode.set("data", jsonParser.valueToTree(baseSchema));

        return jsonParser.writeValueAsString(rootNode).getBytes(StandardCharsets.UTF_8);
    }
}
