package org.learn365.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EntityScan("org.learn365.modal.user.entity")
public class Learn365UserApplication {

	public static void main(String args[]) {
		SpringApplication.run(Learn365UserApplication.class, args);
	}

}
