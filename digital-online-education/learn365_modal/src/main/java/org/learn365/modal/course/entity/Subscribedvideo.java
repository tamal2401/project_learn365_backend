package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.StudentCourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="student_profile_video")
@IdClass(StudentProfileVideoId.class)
public class Subscribedvideo{

    @Id
    private Long userId;
    private String videoName;
    @Id
    private Long videoId;
    private StudentProgress videoStatus=StudentProgress.NOTSTARTED;
    private boolean testStatus;
    private Long timespent;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "subscribed_user_Id",
                    referencedColumnName = "userId"),
            @JoinColumn(
                    name = "chapter_id",
                    referencedColumnName = "chapterid")
    })
    private SubscribedChapter subscribedChapter;
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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public StudentProgress getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(StudentProgress videoStatus) {
        this.videoStatus = videoStatus;
    }

    public boolean isTestStatus() {
        return testStatus;
    }

    public void setTestStatus(boolean testStatus) {
        this.testStatus = testStatus;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public SubscribedChapter getSubscribedChapter() {
        return subscribedChapter;
    }

    public void setSubscribedChapter(SubscribedChapter subscribedChapter) {
        this.subscribedChapter = subscribedChapter;
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
