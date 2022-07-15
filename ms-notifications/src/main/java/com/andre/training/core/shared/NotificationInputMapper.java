package com.andre.training.core.shared;

import com.andre.training.core.domain.BaseEventSchema;

import java.util.ArrayList;

public class NotificationInputMapper {

    public String findNotificationType(BaseEventSchema genericEvent) {
        return "EMAIL";
    }

    public SendNotificationUseCase.NotificationInput map(BaseEventSchema genericEvent) {
        return new SendNotificationUseCase.NotificationInput("event", new ArrayList<>(), null);
    }

}
