package com.example.demo.config.kafka;

import com.example.demo.config.core.Setting;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Autowired
    Setting setting;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, setting.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userEventAuditTopic() {
        return new NewTopic(setting.getUserEventAuditTopic(), 1, (short) 1);
    }
}
