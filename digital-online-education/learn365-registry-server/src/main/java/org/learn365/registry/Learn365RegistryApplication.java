package org.learn365.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Learn365RegistryApplication {
    public static void main(String args[]) {
        SpringApplication.run(Learn365RegistryApplication.class,args);
    }
}
