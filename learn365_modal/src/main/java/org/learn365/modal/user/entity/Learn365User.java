/**
 * User related data object for learn365
 */
package org.learn365.modal.user.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "learn365_digital_user")
public class Learn365User extends BaseEntity implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
    private Long id;
    @Column(name = "username", nullable = false, updatable = false)
    private String userName;
    @Column(name = "userpassword", nullable = false)
    private String password;
    @Column(name = "useremail", nullable = false)
    private String email;
    @Column(name = "usermobile", nullable = false)
    private String mobileNumber;
    @Column(name = "isDeleted", nullable = false)
    private boolean deleted;
    @Column(name = "userdateOfBirth", nullable = false, updatable = false)
    private LocalDate dateofBirth;
    @Column(name = "deviceId", nullable = false, updatable = false,
            unique = true)
    private String deviceId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;
    @Column(name = "user_profile_pic")
    private String profilepicturepath;
    @Column(name = "user_grade", nullable = false)
    private String grade;
    @Column(name = "user_tag", nullable = false, updatable = false)
    private String tag;
    @Column(name = "user_country", nullable = false, updatable = false)
    private String country;
    @Column(name = "otpverified", nullable = false)
    private boolean otpverified;

    public Learn365User()
    {
    }

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

    public String getProfilepicturepath()
    {
        return profilepicturepath;
    }

    public void setProfilepicturepath(String profilepicturepath)
    {
        this.profilepicturepath = profilepicturepath;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public Set<Role> getRole()
    {
        return role;
    }

    public void setRole(Set<Role> role)
    {
        this.role = role;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public LocalDate getDateofBirth()
    {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth)
    {
        this.dateofBirth = dateofBirth;
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
