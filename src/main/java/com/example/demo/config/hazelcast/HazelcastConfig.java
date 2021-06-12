package com.example.demo.config.hazelcast;

import com.hazelcast.client.config.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    public void setHazelcastAddress(String hazelcastAddress) {
        this.hazelcastAddress = hazelcastAddress;
    }

    @Value(value = "${hazelcast.address-name}")
    private String hazelcastAddress;

    @Bean
    public ClientConfig hazelcastClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        if (!this.hazelcastAddress.isEmpty()) {
            clientConfig.getNetworkConfig().addAddress("hazelcast");
        }
        return clientConfig;
    }
}
