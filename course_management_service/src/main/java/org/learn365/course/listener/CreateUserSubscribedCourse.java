package org.learn365.course.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CreateUserSubscribedCourse {

    String UserSubscriptionCourseListner = "usercourseSubscription";
    @Input(UserSubscriptionCourseListner)
    SubscribableChannel userCourseSubscriptionEvenr();
}
