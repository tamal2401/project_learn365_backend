package org.learn365.subscription.event;

import org.learn365.modal.subscription.event.SubscriptionCompleteEvent;
import org.learn365.modal.subscription.request.StudentProfileCreateRequest;
import org.learn365.subscription.config.CreateUserSubscription;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventListner {

    private CreateUserSubscription createUserSubscription;

    public SubscriptionEventListner(CreateUserSubscription createUserSubscription){
       this.createUserSubscription=createUserSubscription;
    }

    //This Event will be fired If user will successfully complete th subscription
    @EventListener
    public void subscriptionCompleteEventListner(SubscriptionCompleteEvent subscriptionEventComplete){

        StudentProfileCreateRequest studentProfileCreateRequest=new StudentProfileCreateRequest();
        studentProfileCreateRequest.setUserId(subscriptionEventComplete.getUserId());
        studentProfileCreateRequest.setSubscribedPlanId(subscriptionEventComplete.getSubscribedPlanId());
        studentProfileCreateRequest.setGradeId(subscriptionEventComplete.getGradeId());
        studentProfileCreateRequest.setSubscribedPlanName(subscriptionEventComplete.getSubscribedPlanName());
        studentProfileCreateRequest.setStartDate(subscriptionEventComplete.getStartDate());
        studentProfileCreateRequest.setGradeName(subscriptionEventComplete.getGradeName());
        studentProfileCreateRequest.setEndDate(subscriptionEventComplete.getEndDate());
        studentProfileCreateRequest.setSubjectId(subscriptionEventComplete.getSubjectId());

        createUserSubscription.applySubscriptionToUser().send(MessageBuilder.withPayload(studentProfileCreateRequest).build());

    }
}
