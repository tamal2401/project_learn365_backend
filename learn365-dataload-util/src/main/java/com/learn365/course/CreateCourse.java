package com.learn365.course;

import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.response.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CreateCourse {

    private RestTemplate restTemplate;
    private static final String COURSE_CREATION_API="http://localhost:9012/v1/grade/service/addportfolio";
    CreateCourse(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public void createGrade(StandardRequest standardRequest){
        ResponseEntity<String> response=null;
        try {
            HttpEntity<StandardRequest> request = new HttpEntity<>(standardRequest);
             response = restTemplate.exchange(COURSE_CREATION_API, HttpMethod.POST, request, String.class);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
