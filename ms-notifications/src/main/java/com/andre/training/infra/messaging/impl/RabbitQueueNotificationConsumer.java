package com.andre.training.infra.messaging.impl;

import com.andre.training.infra.messaging.NotificationQueueConsumer;
import com.andre.training.infra.messaging.NotificationQueueData;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"rabbit", "!kafka"})
public class RabbitQueueNotificationConsumer implements NotificationQueueConsumer {

    @Override
//    @RabbitListener(queues = {"${app.notification.queue.name}"})
    public void consumeNotification(NotificationQueueData notificationData) {

    }
}
