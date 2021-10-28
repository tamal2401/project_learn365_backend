package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="student_profile_grade")
@EntityListeners(AuditingEntityListener.class)
public class SubscribedGrade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sgrade_generator")
    @SequenceGenerator(name="sgrade_generator", sequenceName = "sgrade_seq")
    private Long id;
    private Long userId;
    private Long gradeId;
    private String gradeName;
    private Long subscribedPlanId;
    private String subscribedPlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private StudentProgress gradestatus=StudentProgress.NOTSTARTED;
    private Long timespent;
    @ManyToOne
    @JoinColumn(name = "fk_userprofileId")
    private StudentProfile studentprofile;

    @OneToMany(mappedBy = "subscribedGrade",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SubscribedSubject> subscribedSubject;

    @CreatedDate
    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDate createdAt;

    @LastModifiedDate
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
        this.gradeName=builder.gradeName;
    }

    public SubscribedGrade() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public static class SubscribedGradeBuilder{

        private Long userId;
        private Long gradeId;
        private Long subscribedPlanId;
        private String subscribedPlanName;
        private LocalDate startDate;
        private LocalDate endDate;
        private StudentProgress gradestatus=StudentProgress.NOTSTARTED;
        private Long timespent;
        private String gradeName;

public SubscribedGradeBuilder(Long userId,Long gradeId,String gradeName){
    this.userId=userId;
    this.gradeId=gradeId;
    this.gradeName=gradeName;
}

public SubscribedGradeBuilder withSubscribedPlanId(Long planId){
    this.subscribedPlanId=planId;
    return this;
}
        public SubscribedGradeBuilder withSubscribedPlanName(String planname){
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
