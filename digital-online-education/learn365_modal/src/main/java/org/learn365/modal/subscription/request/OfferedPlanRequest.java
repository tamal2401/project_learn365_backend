package org.learn365.modal.subscription.request;



import java.util.ArrayList;
import java.util.List;

public class OfferedPlanRequest extends BaseEntity {

    private String subscriptionPlanName;
    private String currency;
    private Double cost;
    private String validity;
    private Boolean active;
    private Double discountPrice;
    private List<OfferedSubjectRequest> courseSubscriptionDetail=new ArrayList();
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

    public List<OfferedSubjectRequest> getCourseSubscriptionDetail() {
        return courseSubscriptionDetail;
    }

    public void setCourseSubscriptionDetail(List<OfferedSubjectRequest> courseSubscriptionDetail) {
        this.courseSubscriptionDetail = courseSubscriptionDetail;
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
}
