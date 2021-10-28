package org.learn365.modal.subscription.event;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionCompleteEvent extends ApplicationEvent {

    private Long userId;
    private Long gradeId;
    private String gradeName;
    private Long subscribedPlanId;
    private String subscribedPlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> subjectId=new ArrayList<>();
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SubscriptionCompleteEvent(Object source,Long userId,Long gradeId,Long subscribedPlanId
            ,String subscribedPlanName,LocalDate startDate,LocalDate endDate,List<String> subjectId,String gradeName) {
        super(source);
        this.userId=userId;
        this.gradeId=gradeId;
        this.subscribedPlanId=subscribedPlanId;
        this.subscribedPlanName=subscribedPlanName;
        this.startDate=startDate;
        this.endDate=endDate;
        this.subjectId=subjectId;
        this.gradeName=gradeName;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public Long getSubscribedPlanId() {
        return subscribedPlanId;
    }

    public String getSubscribedPlanName() {
        return subscribedPlanName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getSubjectId() {
        return subjectId;
    }

    public String getGradeName() {
        return gradeName;
    }
}
