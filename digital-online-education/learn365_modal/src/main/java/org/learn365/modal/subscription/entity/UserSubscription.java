package org.learn365.modal.subscription.entity;

import org.learn365.modal.subscription.SubscriptionStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.persistence.*;

@Entity
@Table(name = "user_course_portfolio")
public class UserSubscription extends BaseEntity {

	@Column(name = "subscriber_user", nullable = false, unique = true)
	private Long userId;
	@Column(name = "subscriber_userName", nullable = false)
	private String userName;
	@Enumerated(EnumType.STRING)
	@Column(name = "subscription_status")
	private SubscriptionStatus status;
	@Column(name = "subscription_planid",nullable = false)
	private Long planId;
	@Column(name = "subscription_standardId",nullable = false)
	private Long standardId;
	@Column(name = "subscription_payment",nullable = false)
	private Long paymentId;
	@Column(name = "subscription_startdate",nullable = false)
	private LocalDate subscriptionStartdate;
	@Column(name = "subscription_enddate",nullable = false)
	private LocalDate subscriptionEnddate;

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

	public LocalDate getSubscriptionStartdate() {
		return subscriptionStartdate;
	}

	public void setSubscriptionStartdate(LocalDate subscriptionStartdate) {
		this.subscriptionStartdate = subscriptionStartdate;
	}

	public LocalDate getSubscriptionEnddate() {
		return subscriptionEnddate;
	}

	public void setSubscriptionEnddate(LocalDate subscriptionEnddate) {
		this.subscriptionEnddate = subscriptionEnddate;
	}
}
