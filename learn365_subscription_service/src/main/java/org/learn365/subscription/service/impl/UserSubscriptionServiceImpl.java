package org.learn365.subscription.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.exception.UserSubscriptionException;
import org.learn365.modal.subscription.SubscriptionStatus;
import org.learn365.modal.subscription.entity.SubscriptionPlan;
import org.learn365.modal.subscription.entity.UserSubscription;
import org.learn365.modal.subscription.event.SubscriptionCompleteEvent;
import org.learn365.modal.subscription.request.UserSubscriptionPortfolioRequest;
import org.learn365.modal.subscription.response.SubscribedUser;
import org.learn365.modal.subscription.response.UserSubscriptionInformation;
import org.learn365.modal.subscription.response.UserSubscriptionPortfolioResponse;
import org.learn365.subscription.config.CreateUserSubscription;
import org.learn365.subscription.repository.SubscriptionPlanRepository;
import org.learn365.subscription.repository.UserSubscriptionRepository;
import org.learn365.subscription.service.UserSubscriptionService;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private UserSubscriptionRepository userSubscriptionRepository;
    private ApplicationEventPublisher applicationEventPublisher;
    private SubscriptionPlanRepository subscriptionplan;


    UserSubscriptionServiceImpl(UserSubscriptionRepository userSubscriptionRepository, SubscriptionPlanRepository subscriptionplan, ApplicationEventPublisher applicationEventPublisher) {
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.subscriptionplan = subscriptionplan;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    @Override
    public UserSubscriptionPortfolioResponse userSubscription(
            UserSubscriptionPortfolioRequest userSubscriptionPortfolioRequest) {

        if (ObjectUtils.isEmpty(userSubscriptionPortfolioRequest)
                && ( userSubscriptionPortfolioRequest.getValidity() < 0)) {
            throw new IllegalArgumentException("Validity should always be greater than 0");
        }
        UserSubscription usrsubscription = new UserSubscription();
        BeanUtils.copyProperties(userSubscriptionPortfolioRequest, usrsubscription);
        usrsubscription.setSubscriptionStartdate(LocalDate.now());
        usrsubscription.setSubscriptionEnddate(LocalDate.now().plusDays(userSubscriptionPortfolioRequest.getValidity()));
        usrsubscription.setPlanName(userSubscriptionPortfolioRequest.getPlanname());
        Optional<UserSubscription> usersubscription = Optional.empty();
        try {
            usersubscription = Optional.of(userSubscriptionRepository.save(usrsubscription));
        } catch (Exception exception) {
            throw new UserSubscriptionException(exception.getMessage());
        }
        UserSubscription savedSubscription = usersubscription.orElseThrow(() -> new UserSubscriptionException("Subscription doesn't happened properly."));
        UserSubscriptionPortfolioResponse subscriptionPortfolioResponse = new UserSubscriptionPortfolioResponse();
        BeanUtils.copyProperties(savedSubscription, subscriptionPortfolioResponse);
        //If subscription successful create Event to Add course to user portfolio.
        Optional<SubscriptionPlan> opSubscription = subscriptionplan.findById(savedSubscription.getPlanId());
        List<String> subjectids = new ArrayList<>();
        opSubscription.get().getCourseSubscriptionDetail().stream().forEach(subject -> subjectids.add(subject.getOfferedSubjectid()));
        applicationEventPublisher.publishEvent(new SubscriptionCompleteEvent(this, savedSubscription.getUserId()
                , savedSubscription.getStandardId(), savedSubscription.getPlanId(), savedSubscription.getPlanName(), savedSubscription.getSubscriptionStartdate()
                , savedSubscription.getSubscriptionEnddate(), subjectids, savedSubscription.getStandardName()));
        return subscriptionPortfolioResponse;
    }

    @Override
    public SubscribedUser fetchUserSubscription(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Request is not valid. Please provide userId");
        }
        List<UserSubscription> userCourseSubscription = null;
        try {
            userCourseSubscription = userSubscriptionRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new UserSubscriptionException(e.getMessage());
        }
        if (CollectionUtils.isEmpty(userCourseSubscription)) {
            throw new UserSubscriptionException("User doesn't have any active subscription");
        }
        SubscribedUser subscribedUser = new SubscribedUser();
        subscribedUser.setUserId(userCourseSubscription.get(0).getUserId());
        subscribedUser.setUserName(userCourseSubscription.get(0).getUserName());

        List<UserSubscriptionInformation> userSubscriptioninfo = userCourseSubscription.stream().map(surcoursesub -> {
            UserSubscriptionInformation usersub = new UserSubscriptionInformation(surcoursesub.getPlanName(), surcoursesub.getStandardId(), surcoursesub.getStandardName());
            usersub.setPlanId(surcoursesub.getPlanId());
            if (surcoursesub.getSubscriptionEnddate().isBefore(LocalDate.now())) {
                usersub.setStatus(SubscriptionStatus.EXPIRED);
            }else{
                usersub.setStatus(SubscriptionStatus.SUBSCRIBED);
            }
            return usersub;
        }).collect(Collectors.toList());

        subscribedUser.setSubscriptioninfo(userSubscriptioninfo);
        return subscribedUser;
    }

}
