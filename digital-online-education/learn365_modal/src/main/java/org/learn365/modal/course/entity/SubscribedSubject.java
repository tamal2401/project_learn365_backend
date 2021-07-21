package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="student_profile_subject")
@IdClass(StudentProfileSubjectId.class)
public class SubscribedSubject{

    @Id
    private Long userId;
    @Id
    private Long subjectid;
    private StudentProgress subjectStatus=StudentProgress.NOTSTARTED;
    private Boolean teststatus;
    @OneToMany(mappedBy ="subscribedSubject",cascade = CascadeType.ALL)
    private List<SubscribedChapter> subscribedChapter;
    private Long timespent;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "subscribed_user_Id",
                    referencedColumnName = "userId"),
            @JoinColumn(
                    name = "gradeId",
                    referencedColumnName = "gradeId")
    })
    private SubscribedGrade subscribedGrade;

    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_At", updatable = true, nullable = false)
    private LocalDate updatedAt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    public StudentProgress getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(StudentProgress subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public Boolean getTeststatus() {
        return teststatus;
    }

    public void setTeststatus(Boolean teststatus) {
        this.teststatus = teststatus;
    }

    public List<SubscribedChapter> getSubscribedChapter() {
        return subscribedChapter;
    }

    public void setSubscribedChapter(List<SubscribedChapter> subscribedChapter) {
        this.subscribedChapter = subscribedChapter;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public SubscribedGrade getSubscribedGrade() {
        return subscribedGrade;
    }

    public void setSubscribedGrade(SubscribedGrade subscribedGrade) {
        this.subscribedGrade = subscribedGrade;
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
}
