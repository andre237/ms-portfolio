package com.andre.training.infra.messaging.impl;

import com.andre.training.core.shared.NotificationInputMapper;
import com.andre.training.core.shared.NotificationType;
import com.andre.training.core.shared.SendNotificationUseCaseRegistry;
import com.andre.training.infra.messaging.schema.ClientNotificationData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j @Component
@Profile("kafka, !rabbit")
public class KafkaTopicNotificationConsumer {

    private final NotificationInputMapper notificationInputMapper;
    private final SendNotificationUseCaseRegistry notificationUseCaseRegistry;

    public KafkaTopicNotificationConsumer(SendNotificationUseCaseRegistry notificationUseCaseRegistry) {
        this.notificationInputMapper = new NotificationInputMapper();
        this.notificationUseCaseRegistry = notificationUseCaseRegistry;
    }

    @KafkaListener(topics = "${app.notification.topic}", groupId = "${app.notification.group}")
    public void consumeMessage(@Payload ClientNotificationData notificationData) {
        var notificationType = NotificationType.valueOf(notificationInputMapper.findNotificationType(notificationData));
        notificationUseCaseRegistry.findUseCaseFor(notificationType)
                .ifPresent(useCase -> useCase.execute(notificationInputMapper.map(notificationData)));
    }

}
