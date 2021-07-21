package org.learn365.modal.course.entity;

import org.learn365.modal.course.StudentCourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profile")
public class StudentProfile extends BaseEntity{

    private Long userId;
    private Long timespent;
    @OneToMany(mappedBy = "studentprofile",cascade = CascadeType.ALL)
    private List<SubscribedGrade> subscribedGrade=new ArrayList<>();
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

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public List<SubscribedGrade> getSubscribedGrade() {
        return subscribedGrade;
    }

    public void setSubscribedGrade(List<SubscribedGrade> subscribedGrade) {
        this.subscribedGrade = subscribedGrade;
    }

    @Override
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
