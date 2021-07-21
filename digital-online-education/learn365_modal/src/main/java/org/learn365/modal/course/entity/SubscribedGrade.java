package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="student_profile_grade")
@IdClass(StudentProfileGradeId.class)
public class SubscribedGrade{

    @Id
    private Long userId;
    @Id
    private Long gradeId;
    private Long subscribedPlanId;
    private Long subscribedPlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    private StudentProgress gradestatus=StudentProgress.NOTSTARTED;
    private Long timespent;
    @ManyToOne
    @JoinColumn(name = "fk_userprofileId")
    private StudentProfile studentprofile;

    @OneToMany(mappedBy = "subscribedGrade",cascade = CascadeType.ALL)
    private List<SubscribedSubject> subscribedSubject;
    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_At", updatable = true, nullable = false)
    private LocalDate updatedAt;

    public SubscribedGrade(SubscribedGradeBuilder builder){
        this.userId=builder.userId;
        this.gradeId= builder.gradeId;
        this.gradestatus=builder.gradestatus;
        this.startDate=builder.startDate;
        this.endDate=builder.endDate;
        this.subscribedPlanId=builder.subscribedPlanId;
        this.subscribedPlanName= builder.subscribedPlanName;
        this.timespent=builder.timespent;
    }

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

    public Long getSubscribedPlanName() {
        return subscribedPlanName;
    }

    public void setSubscribedPlanName(Long subscribedPlanName) {
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

    public StudentProgress getGradestatus() {
        return gradestatus;
    }

    public void setGradestatus(StudentProgress gradestatus) {
        this.gradestatus = gradestatus;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public StudentProfile getStudentprofile() {
        return studentprofile;
    }

    public void setStudentprofile(StudentProfile studentprofile) {
        this.studentprofile = studentprofile;
    }

    public List<SubscribedSubject> getSubscribedSubject() {
        return subscribedSubject;
    }

    public void setSubscribedSubject(List<SubscribedSubject> subscribedSubject) {
        this.subscribedSubject = subscribedSubject;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class SubscribedGradeBuilder{

        private Long userId;
        private Long gradeId;
        private Long subscribedPlanId;
        private Long subscribedPlanName;
        private LocalDate startDate;
        private LocalDate endDate;
        private StudentProgress gradestatus=StudentProgress.NOTSTARTED;
        private Long timespent;

public SubscribedGradeBuilder(Long userId,Long gradeId){
    this.userId=userId;
    this.gradeId=gradeId;
}

public SubscribedGradeBuilder withSubscribedPlanId(Long planId){
    this.subscribedPlanId=planId;
    return this;
}
        public SubscribedGradeBuilder withSubscribedPlanName(Long planname){
            this.subscribedPlanName=planname;
            return this;
        }

        public SubscribedGradeBuilder withStartDate(LocalDate startDate){
            this.startDate=startDate;
            return this;
        }

        public SubscribedGradeBuilder withEndDate(LocalDate endDate){
            this.endDate=endDate;
            return this;
        }

        public SubscribedGradeBuilder withStudentCourseStatus( StudentProgress studentcourseStatus){
            this.gradestatus=studentcourseStatus;
            return this;
        }

        public SubscribedGradeBuilder withTimeSpent( Long timeSpent){
            this.timespent=timeSpent;
            return this;
        }

        public SubscribedGrade build(){
            SubscribedGrade subscribedGrade=new SubscribedGrade(this);
            return subscribedGrade;
        }
    }
}
