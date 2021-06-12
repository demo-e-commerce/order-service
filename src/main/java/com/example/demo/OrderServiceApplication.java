package com.example.demo;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@SpringBootApplication
@EnableCaching
public class OrderServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Profile("dev")
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}
}

