package com.andre.training.core.shared;

import com.andre.training.core.application.UseCase;

import java.util.List;
import java.util.Map;

import static com.andre.training.core.shared.SendNotificationUseCase.*;

public abstract class SendNotificationUseCase extends UseCase<NotificationInput, VoidOutput> {

    public abstract VoidOutput execute(NotificationInput input);
    public abstract NotificationType getTypeImplementation();

    public record NotificationInput(String event, List<String> recipients, Map<String, Object> args) implements UseCase.Input {

    }

}
