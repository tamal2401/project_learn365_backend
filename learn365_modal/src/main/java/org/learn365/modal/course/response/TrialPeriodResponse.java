package org.learn365.modal.course.response;

import org.learn365.modal.constants.TrialStatus;

public class TrialPeriodResponse extends BaseEntity {

    private Long trialid;
    private Long userid;
    private String userName;
    private TrialStatus status;

    public Long getTrialid() {
        return trialid;
    }

    public void setTrialid(Long trialid) {
        this.trialid = trialid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TrialStatus getStatus() {
        return status;
    }

    public void setStatus(TrialStatus status) {
        this.status = status;
    }
}
