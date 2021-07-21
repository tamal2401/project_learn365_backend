package org.learn365.modal.course.response;

import java.util.List;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.subscription.entity.OfferedSubject;

public class StandardResponse extends BaseEntity {

	private String name;
	private Integer order;
	private Boolean active;
	private String picture;
	private String trialVideo;
	private List<SubjectResponse> subjects;
	private OfferedSubject courseSubscriptionPortfolio;
	private Long testid;
	private Boolean teststatus;
	public StudentProgress studentProgress = StudentProgress.NOTSTARTED;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
	}

	public List<SubjectResponse> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectResponse> subjects) {
		this.subjects = subjects;
	}

	public Long getTestid() {
		return testid;
	}

	public void setTestid(Long testid) {
		this.testid = testid;
	}

	public OfferedSubject getCourseSubscriptionPortfolio() {
		return courseSubscriptionPortfolio;
	}

	public void setCourseSubscriptionPortfolio(OfferedSubject courseSubscriptionPortfolio) {
		this.courseSubscriptionPortfolio = courseSubscriptionPortfolio;
	}

	public StudentProgress getStudentProgress() {
		return studentProgress;
	}

	public void setStudentProgress(StudentProgress studentProgress) {
		this.studentProgress = studentProgress;
	}

	public Boolean getTeststatus() {
		return teststatus;
	}

	public void setTeststatus(Boolean teststatus) {
		this.teststatus = teststatus;
	}
}
