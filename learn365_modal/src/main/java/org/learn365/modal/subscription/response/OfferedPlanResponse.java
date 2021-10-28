package org.learn365.modal.subscription.response;

import org.learn365.modal.subscription.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class OfferedPlanResponse extends BaseEntity {

    private Long id;
    private String subscriptionPlanName;
    private String currency;
    private Double cost;
    private String validity;
    private Boolean active;
    private Double discountPrice;
    private Boolean discounted;
    private List<OfferedSubjectResponse> offeredSubject=new ArrayList();
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<OfferedSubjectResponse> getOfferedSubject() {
        return offeredSubject;
    }
    public void setOfferedSubject(List<OfferedSubjectResponse> offeredSubject) {
        this.offeredSubject = offeredSubject;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Boolean getDiscounted() {
        return discounted;
    }

    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }
}
