package org.learn365.modal.course.request;

import org.learn365.modal.constants.TrialStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TrialPeriodRequest extends BaseEntity {

    private Long trialid;
    @NotNull(message = "userId should not be null or empty")
    private Long userid;
    @NotEmpty(message = "userId should not be null or empty")
    private String userName;
    private Integer validity;
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

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public TrialStatus getStatus() {
        return status;
    }

    public void setStatus(TrialStatus status) {
        this.status = status;
    }
}
