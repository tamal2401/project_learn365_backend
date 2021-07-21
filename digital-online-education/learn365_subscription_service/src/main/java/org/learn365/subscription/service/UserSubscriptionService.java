package org.learn365.subscription.service;

import org.learn365.modal.subscription.request.UserSubscriptionPortfolioRequest;
import org.learn365.modal.subscription.response.UserSubscriptionPortfolioResponse;

import java.util.List;

public interface UserSubscriptionService {

	public UserSubscriptionPortfolioResponse userSubscription(UserSubscriptionPortfolioRequest userSubscriptionPortfolioRequest);

	public List<UserSubscriptionPortfolioResponse> fetchUserSubscription(Long userId);
}
