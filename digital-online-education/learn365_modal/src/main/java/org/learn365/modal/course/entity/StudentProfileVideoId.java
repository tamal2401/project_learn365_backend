package org.learn365.modal.course.entity;

import java.io.Serializable;

public class StudentProfileVideoId implements Serializable {
    private Long userId;
    private Long videoId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
