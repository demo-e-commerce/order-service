package com.example.demo.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringBeanConfig {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
