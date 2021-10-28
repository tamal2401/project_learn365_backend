package com.learn365.subscription;

import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CreateSubscription {

    private RestTemplate restTemplate;
    private static final String Subscription_CREATION_API="http://localhost:9015/v1/subscriptionportfolio/service/create";
    CreateSubscription(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public void createGrade(OfferedGradeRequest offerGradeRequest){
        ResponseEntity<String> response=null;
        try {
            HttpEntity<OfferedGradeRequest> request = new HttpEntity<>(offerGradeRequest);
             response = restTemplate.exchange(Subscription_CREATION_API, HttpMethod.POST, request, String.class);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
