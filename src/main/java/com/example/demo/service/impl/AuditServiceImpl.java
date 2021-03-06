package com.example.demo.service.impl;

import com.example.demo.config.core.Setting;
import com.example.demo.dto.AuditEvent;
import com.example.demo.service.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Autowired
    Setting setting;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void audit(AuditEvent auditEvent) {
        try {
            kafkaTemplate.send(setting.getUserEventAuditTopic(), objectMapper.writeValueAsString(auditEvent));
        } catch (Throwable ex) {
            log.error(String.format("Failed to send audit event for user id %s, even %s", auditEvent.getUserId(), auditEvent.getEvenName()), ex);
        }
    }
}
