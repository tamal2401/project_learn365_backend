package org.learn365.subscription.service;

import org.learn365.modal.subscription.request.UserSubscriptionPortfolioRequest;
import org.learn365.modal.subscription.response.SubscribedUser;
import org.learn365.modal.subscription.response.UserSubscriptionPortfolioResponse;

import java.util.List;

public interface UserSubscriptionService {

	UserSubscriptionPortfolioResponse userSubscription(UserSubscriptionPortfolioRequest userSubscriptionPortfolioRequest);

	SubscribedUser fetchUserSubscription(Long userId);
}
