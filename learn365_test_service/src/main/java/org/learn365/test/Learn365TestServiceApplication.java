package org.learn365.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.learn365.test.channel.Channel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan("org.learn365.modal.test.entity")
@EnableMongoAuditing
@EnableResourceServer
@EnableEurekaClient
@EnableScheduling
@EnableBinding(Channel.class)
public class Learn365TestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Learn365TestServiceApplication.class, args);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
	@Qualifier("customTemplate")
    @LoadBalanced
    public RestTemplate getTemplate() {
    	RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
