package org.learn365.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableResourceServer
public class Learn365ApplicationConfig {

	@Bean
	public GlobalExceptionHandler exceptionHandler() {
		return new GlobalExceptionHandler();
	}

}
