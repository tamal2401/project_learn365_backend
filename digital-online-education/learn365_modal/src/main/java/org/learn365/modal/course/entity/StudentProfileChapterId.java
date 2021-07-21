package org.learn365.modal.course.entity;

import java.io.Serializable;

public class StudentProfileChapterId implements Serializable {
    private Long userId;
    private Long chapterid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChapterid() {
        return chapterid;
    }

    public void setChapterid(Long chapterid) {
        this.chapterid = chapterid;
    }
}
