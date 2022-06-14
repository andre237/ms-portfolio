package com.andre.training.infra.messaging;

public interface NotificationQueueConsumer {

    void consumeNotification(NotificationQueueData notificationData);

}
