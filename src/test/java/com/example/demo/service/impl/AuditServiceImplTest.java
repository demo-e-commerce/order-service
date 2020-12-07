package com.example.demo.service.impl;

import com.example.demo.config.Setting;
import com.example.demo.dto.AuditEvent;
import com.example.demo.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuditServiceImplTest {
    @TestConfiguration
    static class OrderServiceImplTestConfig {
        @Bean
        AuditService auditService() {
            return new AuditServiceImpl();
        }
    }

    @Autowired
    AuditService auditService;

    @MockBean
    KafkaTemplate kafkaTemplate;

    @MockBean
    Setting setting;

    @MockBean
    ObjectMapper objectMapper;

    @Test
    public void testAudit_Success() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(any())).thenReturn("");
        when(setting.getUserEventAuditTopic()).thenReturn("");

        AuditEvent auditEvent = AuditEvent.builder().userId("").eventDate(Instant.now()).build();
        auditService.audit(auditEvent);

        Mockito.verify(kafkaTemplate).send(anyString(), anyString());
    }

    @Test
    public void testAudit_Error() throws JsonProcessingException {
        when(objectMapper.writeValueAsString(any())).thenReturn("");
        when(setting.getUserEventAuditTopic()).thenReturn("");
        String EXCEPTION_MESSAGE = "log_audit_failed";
        doThrow(new RuntimeException(EXCEPTION_MESSAGE)).when(kafkaTemplate).send(anyString(), anyString());


        AuditEvent auditEvent = AuditEvent.builder().userId("").eventDate(Instant.now()).build();
        auditService.audit(auditEvent);

        Mockito.verify(kafkaTemplate).send(anyString(), anyString());
        // TODO verify log error
    }
}
