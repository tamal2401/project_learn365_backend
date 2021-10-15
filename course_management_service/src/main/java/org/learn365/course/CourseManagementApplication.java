package org.learn365.course;

import org.learn365.course.listener.CreateUserSubscribedCourse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EntityScan("org.learn365.modal.course.entity")
@EnableResourceServer
@EnableEurekaClient
@EnableBinding(CreateUserSubscribedCourse.class)
public class CourseManagementApplication {
	public static void main(String args[]) {
		SpringApplication.run(CourseManagementApplication.class, args);
	}
}
