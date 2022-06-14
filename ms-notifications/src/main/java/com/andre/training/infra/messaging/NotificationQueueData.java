package com.andre.training.infra.messaging;

import com.andre.training.schema.BaseSchema;
import com.andre.training.schema.MessagingPayload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@MessagingPayload(identifier = "__Notification__")
public class NotificationQueueData extends BaseSchema {

    private String type;
    private String event;
    private List<String> recipients;
    private Map<String, Object> contextArgs;

}
