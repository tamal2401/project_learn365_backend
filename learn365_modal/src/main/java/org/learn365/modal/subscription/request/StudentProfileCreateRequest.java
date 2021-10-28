package org.learn365.modal.subscription.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentProfileCreateRequest {
    private Long userId;
    private Long gradeId;
    private String gradeName;
    private Long subscribedPlanId;
    private String subscribedPlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> subjectId=new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getSubscribedPlanId() {
        return subscribedPlanId;
    }

    public void setSubscribedPlanId(Long subscribedPlanId) {
        this.subscribedPlanId = subscribedPlanId;
    }

    public String getSubscribedPlanName() {
        return subscribedPlanName;
    }

    public void setSubscribedPlanName(String subscribedPlanName) {
        this.subscribedPlanName = subscribedPlanName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(List<String> subjectId) {
        this.subjectId = subjectId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}
