package org.learn365.modal.course.entity;

import java.io.Serializable;

public class StudentProfileSubjectId implements Serializable {
    private Long userId;
    private Long subjectid;

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
}
