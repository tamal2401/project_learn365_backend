package org.learn365.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("org.learn365.test.entity")
public class Learn365TestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Learn365TestServiceApplication.class, args);
	}

	@Bean
	public Gson getMapper(){
		return new Gson();
	}
}
