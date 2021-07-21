package org.learn365.modal.subscription.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="learn365_specialofferings")
public class SpecialOfferings  extends BaseEntity{

    private String specialCourseId;
    private String offeringName;
    private String offeringdescription;
    private Boolean isForSubscription;
    private String offeringCaption;
    private String specialOfferingType;
    private Double subscriptionCost;
    private Integer validity;
    private String actionName;
    private String image;

    public String getSpecialCourseId() {
        return specialCourseId;
    }

    public void setSpecialCourseId(String specialCourseId) {
        this.specialCourseId = specialCourseId;
    }

    public String getOfferingName() {
        return offeringName;
    }

    public void setOfferingName(String offeringName) {
        this.offeringName = offeringName;
    }

    public String getOfferingdescription() {
        return offeringdescription;
    }

    public void setOfferingdescription(String offeringdescription) {
        this.offeringdescription = offeringdescription;
    }

    public Boolean getForSubscription() {
        return isForSubscription;
    }

    public void setForSubscription(Boolean forSubscription) {
        isForSubscription = forSubscription;
    }

    public String getOfferingCaption() {
        return offeringCaption;
    }

    public void setOfferingCaption(String offeringCaption) {
        this.offeringCaption = offeringCaption;
    }

    public String getSpecialOfferingType() {
        return specialOfferingType;
    }

    public void setSpecialOfferingType(String specialOfferingType) {
        this.specialOfferingType = specialOfferingType;
    }

    public Double getSubscriptionCost() {
        return subscriptionCost;
    }

    public void setSubscriptionCost(Double subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }
}
