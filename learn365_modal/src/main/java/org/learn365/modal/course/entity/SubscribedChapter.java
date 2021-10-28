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
@Table(name="student_profile_chapter")
@EntityListeners(AuditingEntityListener.class)
public class SubscribedChapter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscribedChapter_generator")
    @SequenceGenerator(name="subscribedChapter_generator", sequenceName = "subscribedChapter_seq")
    private Long id;
    private Long userId;
    private String chapterName;
    private Long chapterid;
    @Enumerated(EnumType.STRING)
    private StudentProgress chapterStatus= StudentProgress.NOTSTARTED;
    private boolean teststatus;
    @OneToMany(mappedBy = "subscribedChapter",cascade = CascadeType.ALL)
    private List<Subscribedvideo> subscribedvideos;
    private Long timespent;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "subscribed_user_Id",
                    referencedColumnName = "userId"),
            @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "subjectid")
    })
    private SubscribedSubject subscribedSubject;
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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Long getChapterid() {
        return chapterid;
    }

    public void setChapterid(Long chapterid) {
        this.chapterid = chapterid;
    }

    public StudentProgress getChapterStatus() {
        return chapterStatus;
    }

    public void setChapterStatus(StudentProgress chapterStatus) {
        this.chapterStatus = chapterStatus;
    }

    public boolean isTeststatus() {
        return teststatus;
    }

    public void setTeststatus(boolean teststatus) {
        this.teststatus = teststatus;
    }

    public List<Subscribedvideo> getSubscribedvideos() {
        return subscribedvideos;
    }

    public void setSubscribedvideos(List<Subscribedvideo> subscribedvideos) {
        this.subscribedvideos = subscribedvideos;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public SubscribedSubject getSubscribedSubject() {
        return subscribedSubject;
    }

    public void setSubscribedSubject(SubscribedSubject subscribedSubject) {
        this.subscribedSubject = subscribedSubject;
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
