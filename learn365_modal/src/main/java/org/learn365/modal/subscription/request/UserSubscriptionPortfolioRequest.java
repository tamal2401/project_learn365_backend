package org.learn365.modal.subscription.request;

import org.learn365.modal.subscription.SubscriptionStatus;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Locale;

public class UserSubscriptionPortfolioRequest extends BaseEntity {

	@NotNull
	private Long userId;
	@NotNull
	private String userName;
	@NotNull
	private SubscriptionStatus status;
	@NotNull
	private Long planId;
	@NotNull
	private String planname;
	@NotNull
	private Long standardId;
	@NotNull
	private String standardName;
	@NotNull
	private Long paymentId;

	private LocalDate subscriptionStartdate;
	private LocalDate subscriptionEnddate;
	@NotNull
	private Integer validity;

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

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}
}
