package com.andre.training.core.shared;

import com.andre.training.infra.messaging.NotificationQueueData;

public class NotificationInputMapper {

    public SendNotificationUseCase.NotificationInput map(NotificationQueueData notificationQueueData) {
        return new SendNotificationUseCase.NotificationInput(
                notificationQueueData.getEvent(),
                notificationQueueData.getRecipients(),
                notificationQueueData.getContextArgs()
        );
    }

}
