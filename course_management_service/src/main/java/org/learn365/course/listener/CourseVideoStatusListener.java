package org.learn365.course.listener;

import org.learn365.course.repository.SubscribedvideoRepository;
import org.learn365.modal.course.CourseVideoTestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.invoke.MethodHandles;

@EnableBinding(Channel.class)
public class CourseVideoStatusListener {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    SubscribedvideoRepository repository;

    @StreamListener(value = Channel.videoTestStatusChannel)
    public void listen(@Payload CourseVideoTestStatus testStatus) {
        try {
            repository.updateTestStatus(testStatus.getTestResult(), testStatus.getUserId(), testStatus.getVideoId());
        } catch (Throwable ex) {
            log.error("Exception occurred while logging test status: {}, ex: {}", testStatus, ex.getMessage());
        }
    }
}
