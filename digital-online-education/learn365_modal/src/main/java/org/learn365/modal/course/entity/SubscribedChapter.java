package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="student_profile_chapter")
@IdClass(StudentProfileChapterId.class)
public class SubscribedChapter{

    @Id
    private Long userId;
    private String chapterName;
    @Id
    private Long chapterid;
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
