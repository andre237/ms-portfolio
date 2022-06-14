package com.andre.training.kafka.serde;

import com.andre.training.schema.BaseSchema;
import com.andre.training.schema.MessagingPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j @Component
public class CustomKafkaJsonSerializer implements Serializer<BaseSchema> {

    private final ObjectMapper jsonParser = new ObjectMapper();

    @SneakyThrows
    @Override
    public byte[] serialize(String s, BaseSchema baseSchema) {
        MessagingPayload annotation = baseSchema.getClass().getAnnotation(MessagingPayload.class);

        ObjectNode rootNode = jsonParser.createObjectNode();
        rootNode.put("identifier", annotation.identifier());
        rootNode.set("data", jsonParser.valueToTree(baseSchema));

        return jsonParser.writeValueAsString(rootNode).getBytes(StandardCharsets.UTF_8);
    }
}
