package org.learn365.modal.subscription.response;

import org.learn365.modal.subscription.SubscriptionStatus;

import java.util.Locale;

public class UserSubscriptionPortfolioResponse extends BaseEntity {

	private Long userId;
	private String userName;
	private SubscriptionStatus status;
	private Long planId;
	private Long standardId;
	private Long paymentId;
	private Locale subscriptionStartdate;
	private Locale subscriptionEnddate;

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

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Locale getSubscriptionStartdate() {
		return subscriptionStartdate;
	}

	public void setSubscriptionStartdate(Locale subscriptionStartdate) {
		this.subscriptionStartdate = subscriptionStartdate;
	}

	public Locale getSubscriptionEnddate() {
		return subscriptionEnddate;
	}

	public void setSubscriptionEnddate(Locale subscriptionEnddate) {
		this.subscriptionEnddate = subscriptionEnddate;
	}
}