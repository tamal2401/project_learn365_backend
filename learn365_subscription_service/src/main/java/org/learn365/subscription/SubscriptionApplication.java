package org.learn365.subscription;

import org.learn365.subscription.config.CreateUserSubscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableBinding(CreateUserSubscription.class)
public class SubscriptionApplication {

	public static void main(String args[]) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}

}
