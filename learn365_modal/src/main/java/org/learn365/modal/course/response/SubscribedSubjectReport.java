package org.learn365.modal.course.response;

import org.learn365.modal.constants.StudentProgress;

public class SubscribedSubjectReport {

    private Long subjectid;
    private String subjectname;
    private Long timespent;
    private StudentProgress progressStatus;

    public SubscribedSubjectReport(Long subjectid, String subjectname, Long timespent) {
        this.subjectid = subjectid;
        this.subjectname = subjectname;
        this.timespent = timespent;
    }

    public StudentProgress getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(StudentProgress progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Long getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }
}
