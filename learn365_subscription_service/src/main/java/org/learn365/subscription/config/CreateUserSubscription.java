package org.learn365.subscription.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CreateUserSubscription {

    @Output("applyUserSubscriptionChannel")
    MessageChannel applySubscriptionToUser();
}
