package com.example.demo.service.impl;

import com.example.demo.config.Setting;
import com.example.demo.dto.UserEvent;
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
    public void audit(UserEvent userEvent) {
        try {
            kafkaTemplate.send(setting.getUserEventAuditTopic(), objectMapper.writeValueAsString(userEvent));
        } catch (Throwable ex) {
            log.error(String.format("Failed to send audit event for user id %s, even %s", userEvent.getUserId(), userEvent.getEvenName()), ex);
        }
    }
}
