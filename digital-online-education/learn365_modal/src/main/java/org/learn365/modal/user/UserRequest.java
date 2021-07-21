package org.learn365.modal.user;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.learn365.modal.validation.DateOfBirthValidation;
import org.learn365.modal.validation.EmailValidation;
import org.learn365.modal.validation.MobileNumberValidation;

public class UserRequest extends BaseRequest {

	@NotEmpty(message = "UserName should not be empty")
	private String userName;
	private String password;
	@EmailValidation
	private String email;
	@MobileNumberValidation
	private String mobileNumber;
	@DateOfBirthValidation
	private LocalDate dateofBirth;
	@NotEmpty(message = "Role should not be empty")
	private List<String> role;
	private String profilepicturepath;
	private String grade;
	private String tag;
	private String country;
	@NotEmpty(message="Device Id should not be blank or Null")
	private String deviceId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getProfilepicturepath() {
		return profilepicturepath;
	}

	public void setProfilepicturepath(String profilepicturepath) {
		this.profilepicturepath = profilepicturepath;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
