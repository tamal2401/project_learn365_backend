package org.learn365.file.uploader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class StandardResponse extends BaseEntity {
	private Long id;
	private String name;
	private Integer order;
	@JsonIgnore
	private Boolean active;
	@JsonIgnore
	private String picture;
	private String trialVideo;
	private List<SubjectResponse> subjects;
	private String testid;
	private Boolean teststatus;
	public StudentProgress studentProgress = StudentProgress.NOTSTARTED;
	private Long studentgradeProfileId;

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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
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

	public Long getStudentgradeProfileId() {
		return studentgradeProfileId;
	}

	public void setStudentgradeProfileId(Long studentgradeProfileId) {
		this.studentgradeProfileId = studentgradeProfileId;
	}
}
