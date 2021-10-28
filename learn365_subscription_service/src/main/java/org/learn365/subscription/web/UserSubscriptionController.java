package org.learn365.subscription.web;

import io.swagger.annotations.ApiOperation;
import org.learn365.modal.subscription.request.UserSubscriptionPortfolioRequest;
import org.learn365.modal.subscription.response.SubscribedUser;
import org.learn365.modal.subscription.response.UserSubscriptionPortfolioResponse;
import org.learn365.subscription.service.UserSubscriptionService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/userSubscription/")
public class UserSubscriptionController {

	private UserSubscriptionService userSubscriptionService;

	public UserSubscriptionController(UserSubscriptionService userSubscriptionService) {
		this.userSubscriptionService = userSubscriptionService;
	}

	@ApiOperation(value = "This api to create subscription order for the student or customer")
	@PostMapping(value = {"process","service/process"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserSubscriptionPortfolioResponse userSubscription(@Valid
			@RequestBody UserSubscriptionPortfolioRequest userSubscriptionPortfolioRequest) {

		return userSubscriptionService.userSubscription(userSubscriptionPortfolioRequest);
	}

	@ApiOperation(value="Api is used to fetch all the subscription for the particular user")
	@GetMapping(value = {"{userId}","service/{userId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscribedUser userSubscription(@PathVariable(value = "userId", required = true) String userId) {
		return userSubscriptionService.fetchUserSubscription(Long.parseLong(userId));
	}

}
