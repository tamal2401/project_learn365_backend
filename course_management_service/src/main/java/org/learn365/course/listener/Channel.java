package org.learn365.course.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface Channel {
    String videoTestStatusChannel = "videoTestStatusListener";

    @Input(videoTestStatusChannel)
    SubscribableChannel testStatusListener();
}
