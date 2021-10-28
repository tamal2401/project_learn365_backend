package org.learn365.modal.user;

import java.time.LocalDate;
import java.util.List;

public class UserData
{
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String mobileNumber;
    private LocalDate dateofBirth;
    private List<Role> role;
    private boolean deleted;
    private boolean active;
    private String grade;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String profilepicturepath;
    private String tag;
    private String country;
    private String deviceId;
    private boolean otpverified;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public LocalDate getDateofBirth()
    {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth)
    {
        this.dateofBirth = dateofBirth;
    }

    public List<Role> getRole()
    {
        return role;
    }

    public void setRole(List<Role> role)
    {
        this.role = role;
    }

    public String getProfilepicturepath()
    {
        return profilepicturepath;
    }

    public void setProfilepicturepath(String profilepicturepath)
    {
        this.profilepicturepath = profilepicturepath;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public LocalDate getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public boolean isOtpverified()
    {
        return otpverified;
    }

    public void setOtpverified(boolean otpverified)
    {
        this.otpverified = otpverified;
    }
}
