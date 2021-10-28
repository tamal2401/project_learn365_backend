package com.learn365.model.subscription;

import java.util.List;

public class OfferPlan {

    private Long id;
    private Boolean active;
    private String appName;
    private Double cost;
    private String currency;
    private Double discountPrice;
    private Integer order;
    private String subscriptionPlanName;
    private Integer validity;
    private Long offerGradeID;
    private List<OfferSubject> offerSubjects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public void setSubscriptionPlanName(String subscriptionPlanName) {
        this.subscriptionPlanName = subscriptionPlanName;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Long getOfferGradeID() {
        return offerGradeID;
    }

    public void setOfferGradeID(Long offerGradeID) {
        this.offerGradeID = offerGradeID;
    }

    public List<OfferSubject> getOfferSubjects() {
        return offerSubjects;
    }

    public void setOfferSubjects(List<OfferSubject> offerSubjects) {
        this.offerSubjects = offerSubjects;
    }
}
