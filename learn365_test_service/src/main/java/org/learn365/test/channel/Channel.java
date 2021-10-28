package org.learn365.test.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface Channel {

    String videoTestStatusChannel = "videoTestStatusListener";

    @Output(videoTestStatusChannel)
    MessageChannel testStatusPublisher();
}
