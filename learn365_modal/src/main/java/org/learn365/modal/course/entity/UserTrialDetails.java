package org.learn365.modal.course.entity;

import org.learn365.modal.constants.TrialStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="user_trial_details")
public class UserTrialDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usrtrial_generator")
    @SequenceGenerator(name="usrtrial_generator", sequenceName = "usrtrial_seq")
    private Long trialid;
    @Column(name="user_id",nullable = false,unique = true)
    private Long userid;
    @Column(name="user_name", nullable = false)
    private String userName;
    @Column(name="trial_start_time", nullable = false)
    private LocalDate starttime;
    @Column(name="trial_end_time", nullable = false)
    private LocalDate endTime;
    @Enumerated(EnumType.STRING)
    @Column(name="trial_status", nullable = false)
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

    public LocalDate getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public TrialStatus getStatus() {
        return status;
    }

    public void setStatus(TrialStatus status) {
        this.status = status;
    }
}
