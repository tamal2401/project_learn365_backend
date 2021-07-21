package org.learn365.modal.course.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class StandardRequest extends BaseEntity {

	@NotBlank(message = "Standard name should not be blank")
	private String name;
	private Integer order;
	private Boolean active;
	@NotBlank(message = "picture should not be null or blank")
	private String picture;
	private String trialVideo;
	private List<SubjectRequest> subjects;
	private Long testid;

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

	public List<SubjectRequest> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectRequest> subjects) {
		this.subjects = subjects;
	}

	public Long getTestid() {
		return testid;
	}

	public void setTestid(Long testid) {
		this.testid = testid;
	}

}
