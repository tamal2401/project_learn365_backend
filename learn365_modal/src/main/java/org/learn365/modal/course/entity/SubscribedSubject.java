package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="student_profile_subject")
@EntityListeners(AuditingEntityListener.class)
public class SubscribedSubject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sSubject_generator")
    @SequenceGenerator(name="sSubject_generator", sequenceName = "sSubject_seq")
    private Long id;

    private Long userId;
    private Long subjectid;
    @Enumerated(EnumType.STRING)
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

    @CreatedDate
    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_At", updatable = true, nullable = false)
    private LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
