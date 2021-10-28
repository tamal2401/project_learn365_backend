package org.learn365.modal.course.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student_profile")
public class StudentProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentprofile_generator")
    @SequenceGenerator(name = "studentprofile_generator", sequenceName = "studentprofile_seq")
    private Long id;
    @Column(name = "student_id",nullable = false)
    private Long userId;
    private Long timespent;
    @OneToMany(mappedBy = "studentprofile", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SubscribedGrade> subscribedGrade = new ArrayList<>();

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

}
