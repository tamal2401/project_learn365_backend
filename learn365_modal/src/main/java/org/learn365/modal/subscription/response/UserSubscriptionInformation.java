package org.learn365.modal.subscription.response;

import org.learn365.modal.subscription.SubscriptionStatus;

import java.time.LocalDate;

public class UserSubscriptionInformation {
    private Long planId;
    private String planName;
    private SubscriptionStatus status;
    private Long gradeId;
    private String gradeName;


    public UserSubscriptionInformation(String planName,Long gradeId, String gradeName) {
        this.planName = planName;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }

    public UserSubscriptionInformation() {
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
