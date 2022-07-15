package com.andre.training.infra.messaging.schema;

import com.andre.training.core.domain.BaseEventSchema;
import com.andre.training.core.domain.MessagingPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MessagingPayload(identifier = "__ClientRegistered__")
public class ClientNotificationData extends BaseEventSchema {

    private String cpf;
    private String fullName;
    private String email;

}
