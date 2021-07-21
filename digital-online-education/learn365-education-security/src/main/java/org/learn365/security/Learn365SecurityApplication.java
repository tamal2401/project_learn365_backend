package org.learn365.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EntityScan("org.learn365.modal.user.entity")
public class Learn365SecurityApplication {

	public static void main(String args[]) {
		SpringApplication.run(Learn365SecurityApplication.class, args);
	}
}
