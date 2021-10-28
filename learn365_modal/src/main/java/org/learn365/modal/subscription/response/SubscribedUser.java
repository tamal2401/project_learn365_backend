package org.learn365.modal.subscription.response;

import java.util.ArrayList;
import java.util.List;

public class SubscribedUser {

    private Long userId;
    private String userName;
    private List<UserSubscriptionInformation> subscriptioninfo=new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserSubscriptionInformation> getSubscriptioninfo() {
        return subscriptioninfo;
    }

    public void setSubscriptioninfo(List<UserSubscriptionInformation> subscriptioninfo) {
        this.subscriptioninfo = subscriptioninfo;
    }
}
