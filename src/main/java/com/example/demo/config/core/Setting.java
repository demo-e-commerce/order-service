package com.example.demo.config.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class Setting {
    @Value(value = "${kafka.bootstrapAddress:localhost:9092}")
    private String bootstrapAddress;

    @Value(value = "${kafka.auditTopicName:user-event-audit}")
    private String userEventAuditTopic;

    @Value(value = "${app.process-order-endpoint}")
    private String processOrderEndpoint;
}
