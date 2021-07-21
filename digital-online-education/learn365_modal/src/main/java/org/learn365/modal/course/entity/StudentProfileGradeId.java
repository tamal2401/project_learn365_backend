package org.learn365.modal.course.entity;

import java.io.Serializable;

public class StudentProfileGradeId implements Serializable {
    private Long userId;
    private Long gradeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
}
