package org.learn365.modal.course.request;


import org.learn365.modal.constants.ProgressTables;
import org.learn365.modal.constants.StudentProgress;

import javax.validation.constraints.NotNull;


public class TrackStudentProgressRequest
{

    @NotNull
    public ProgressTables tableName;
    @NotNull
    public Long id;
    @NotNull
    public Long userId;
    public Long timespent;
    private StudentProgress progressStatus;
    private Boolean testflag;
    private Boolean lastVideo = false;

    public ProgressTables getTableName()
    {
        return tableName;
    }

    public void setTableName(ProgressTables tableName)
    {
        this.tableName = tableName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public StudentProgress getProgressStatus()
    {
        return progressStatus;
    }

    public void setProgressStatus(StudentProgress progressStatus)
    {
        this.progressStatus = progressStatus;
    }

    public Long getTimespent()
    {
        return timespent;
    }

    public void setTimespent(Long timespent)
    {
        this.timespent = timespent;
    }

    public Boolean getTestflag()
    {
        return testflag;
    }

    public void setTestflag(Boolean testflag)
    {
        this.testflag = testflag;
    }

    public Boolean getLastVideo()
    {
        return lastVideo;
    }

    public void setLastVideo(Boolean lastVideo)
    {
        this.lastVideo = lastVideo;
    }
}
