package org.learn365.modal.course.entity;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.TestResult;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_profile_video")
@EntityListeners(AuditingEntityListener.class)
public class Subscribedvideo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "svideo_generator")
    @SequenceGenerator(name = "svideo_generator", sequenceName = "svideo_seq")
    private Long id;
    private Long userId;
    private String videoName;
    private Long videoId;
    @Enumerated(EnumType.STRING)
    private StudentProgress videoStatus = StudentProgress.NOTSTARTED;
    @Column(name = "test_status")
    private boolean testStatus = false;
    private Long timespent;
    @Enumerated(EnumType.STRING)
    private TestResult testResult = TestResult.PENDING;
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

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }
}
