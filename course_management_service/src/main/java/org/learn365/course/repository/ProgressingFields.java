package org.learn365.course.repository;

import org.learn365.modal.constants.StudentProgress;

public class ProgressingFields {

    private Long timespent;
    private StudentProgress studentprogress;
    private Boolean isTestCompleted;

    public ProgressingFields(Long timespent, StudentProgress studentprogress, Boolean isTestCompleted) {
        this.timespent = timespent;
        this.studentprogress = studentprogress;
        this.isTestCompleted = isTestCompleted;
    }

    public ProgressingFields(Long timespent, StudentProgress studentprogress) {
        this.timespent = timespent;
        this.studentprogress = studentprogress;
    }

    public ProgressingFields() {
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public StudentProgress getStudentprogress() {
        return studentprogress;
    }

    public void setStudentprogress(StudentProgress studentprogress) {
        this.studentprogress = studentprogress;
    }

    public Boolean getTestCompleted() {
        return isTestCompleted;
    }

    public void setTestCompleted(Boolean testCompleted) {
        isTestCompleted = testCompleted;
    }
}
