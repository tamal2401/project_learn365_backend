package org.learn365.modal.user;

public class UserRegisterStatus
{

    private String email;
    private Boolean otpVerified;
    private Boolean newUser;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Boolean getOtpVerified()
    {
        return otpVerified;
    }

    public void setOtpVerified(Boolean otpVerified)
    {
        this.otpVerified = otpVerified;
    }

    public Boolean getNewUser()
    {
        return newUser;
    }

    public void setNewUser(Boolean newUser)
    {
        this.newUser = newUser;
    }
}
