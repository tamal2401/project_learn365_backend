package org.learn365.modal.user.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userOtp")
public class UserOtp extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "userotp_generator")
    private Long otpid;
    @Column(name = "user_email", nullable = false, updatable = false)
    private String emailid;
    @Column(name = "user_otp", nullable = false, updatable = false)
    private String otp;
    private LocalDateTime receiveddate;
    private LocalDateTime expireddate;

    public Long getOtpid()
    {
        return otpid;
    }

    public void setOtpid(Long otpid)
    {
        this.otpid = otpid;
    }

    public String getEmailid()
    {
        return emailid;
    }

    public void setEmailid(String emailid)
    {
        this.emailid = emailid;
    }

    public String getOtp()
    {
        return otp;
    }

    public void setOtp(String otp)
    {
        this.otp = otp;
    }

    public LocalDateTime getReceiveddate()
    {
        return receiveddate;
    }

    public void setReceiveddate(LocalDateTime receiveddate)
    {
        this.receiveddate = receiveddate;
    }

    public LocalDateTime getExpireddate()
    {
        return expireddate;
    }

    public void setExpireddate(LocalDateTime expireddate)
    {
        this.expireddate = expireddate;
    }
}
