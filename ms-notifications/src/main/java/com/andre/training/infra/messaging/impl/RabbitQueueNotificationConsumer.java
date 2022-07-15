package com.andre.training.infra.messaging.impl;

import com.andre.training.infra.messaging.schema.NotificationQueueData;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"rabbit", "!kafka"})
public class RabbitQueueNotificationConsumer  {

//    @RabbitListener(queues = {"${app.notification.queue.name}"})
    public void consumeNotification(NotificationQueueData notificationData) {

    }
}
