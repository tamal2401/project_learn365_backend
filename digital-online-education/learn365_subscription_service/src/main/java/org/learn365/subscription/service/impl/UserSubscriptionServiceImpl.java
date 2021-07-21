package org.learn365.subscription.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.exception.UserSubscriptionException;
import org.learn365.modal.subscription.entity.UserSubscription;
import org.learn365.modal.subscription.request.UserSubscriptionPortfolioRequest;
import org.learn365.modal.subscription.response.UserSubscriptionPortfolioResponse;
import org.learn365.subscription.repository.UserSubscriptionRepository;
import org.learn365.subscription.service.UserSubscriptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private UserSubscriptionRepository userSubscriptionRepository;


    UserSubscriptionServiceImpl(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    public UserSubscriptionPortfolioResponse userSubscription(
            UserSubscriptionPortfolioRequest userSubscriptionPortfolioRequest) {

        if (ObjectUtils.isEmpty(userSubscriptionPortfolioRequest)
                && (userSubscriptionPortfolioRequest.getUserId() == null
                || userSubscriptionPortfolioRequest.getPlanId() == null
                || userSubscriptionPortfolioRequest.getStandardId() == null || userSubscriptionPortfolioRequest.getStatus() == null
                || userSubscriptionPortfolioRequest.getSubscriptionStartdate() == null
                || userSubscriptionPortfolioRequest.getValidity() == null || userSubscriptionPortfolioRequest.getValidity() < 0)) {
            throw new IllegalArgumentException("Request is not valid. Please try with valid data");
        }

        UserSubscription usrsubscription = new UserSubscription();
        BeanUtils.copyProperties(userSubscriptionPortfolioRequest, usrsubscription);
        LocalDate SubscriptionStartdate = LocalDate.now();
        LocalDate SubscriptionEndDatedate = SubscriptionStartdate.plusDays(userSubscriptionPortfolioRequest.getValidity());
        usrsubscription.setSubscriptionStartdate(SubscriptionStartdate);
        usrsubscription.setSubscriptionEnddate(SubscriptionEndDatedate);
        Optional<UserSubscription> usersubscription = Optional.empty();
        try {
            usersubscription = Optional.of(userSubscriptionRepository.save(usrsubscription));
        } catch (Exception exception) {
            throw new UserSubscriptionException(exception.getMessage());
        }
        UserSubscription savedSubscription = usersubscription.orElseThrow(() -> new UserSubscriptionException("Subscription doesn't happened properly."));
        UserSubscriptionPortfolioResponse subscriptionPortfolioResponse = new UserSubscriptionPortfolioResponse();
        BeanUtils.copyProperties(savedSubscription, subscriptionPortfolioResponse);
        return subscriptionPortfolioResponse;
    }

    @Override
    public List<UserSubscriptionPortfolioResponse> fetchUserSubscription(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Request is not valid. Please provide userId");
        }

        UserSubscriptionPortfolioResponse userSubscriptionResponse = new UserSubscriptionPortfolioResponse();
        List<UserSubscription> userCourseSubscription = null;
        try {
            userCourseSubscription = userSubscriptionRepository.findByUserIdandValidEndDate(userId, LocalDate.now());
        } catch (Exception e) {
            throw new UserSubscriptionException(e.getMessage());
        }
        if (null == userCourseSubscription) {
            throw new UserSubscriptionException("User doesn't have any active subscription");
        }
        return userCourseSubscription.stream().filter(usubscription -> usubscription != null).map(userSubscription -> {
            UserSubscriptionPortfolioResponse userSubscriptionportfolioResponse = new UserSubscriptionPortfolioResponse();
            BeanUtils.copyProperties(userSubscription, userSubscriptionportfolioResponse);
            return userSubscriptionportfolioResponse;
        }).collect(Collectors.toList());

    }

}
