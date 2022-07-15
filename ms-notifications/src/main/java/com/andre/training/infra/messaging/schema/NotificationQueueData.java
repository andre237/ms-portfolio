package com.andre.training.infra.messaging.schema;

import com.andre.training.core.domain.BaseEventSchema;
import com.andre.training.core.domain.MessagingPayload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@MessagingPayload(identifier = "__Notification__")
public class NotificationQueueData extends BaseEventSchema {

    private String type;
    private String event;
    private List<String> recipients;
    private Map<String, Object> contextArgs;

}
