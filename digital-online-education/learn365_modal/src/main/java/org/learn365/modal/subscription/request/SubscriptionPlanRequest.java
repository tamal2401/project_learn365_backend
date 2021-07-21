package org.learn365.modal.subscription.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class SubscriptionPlanRequest extends BaseEntity{

    @NotBlank
    private String subscriptionPlanName;
    @NotBlank
    private String currency;
    private Double cost;
    @NotBlank
    private String validity;
    private Boolean active;
    private List<SubscriptionDetailsRequest> subscriptionDetails;
    private String createdBy;

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public void setSubscriptionPlanName(String subscriptionPlanName) {
        this.subscriptionPlanName = subscriptionPlanName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<SubscriptionDetailsRequest> getSubscriptionDetails() {
        return subscriptionDetails;
    }

    public void setSubscriptionDetails(List<SubscriptionDetailsRequest> subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
