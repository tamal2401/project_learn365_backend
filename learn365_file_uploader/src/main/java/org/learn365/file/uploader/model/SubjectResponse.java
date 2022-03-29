package org.learn365.file.uploader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SubjectResponse extends BaseEntity {

	private Long id;
	private String name;
	private Integer order;
	@JsonIgnore
	private Boolean active;
	@JsonIgnore
	private Boolean delete;
	private String subjectPic;
	@JsonIgnore
	private String trialVideo;
	private List<ChapterResponse> chapters;
	private String testid;
	private Boolean teststatus;
	private Boolean markedforTrial;
	private StudentProgress studentProgress = StudentProgress.NOTSTARTED;
	private Long studentSubjectProfileId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getSubjectPic() {
		return subjectPic;
	}

	public void setSubjectPic(String subjectPic) {
		this.subjectPic = subjectPic;
	}

	public List<ChapterResponse> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterResponse> chapters) {
		this.chapters = chapters;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public Boolean getTeststatus() {
		return teststatus;
	}

	public void setTeststatus(Boolean teststatus) {
		this.teststatus = teststatus;
	}

	public StudentProgress getStudentProgress() {
		return studentProgress;
	}

	public void setStudentProgress(StudentProgress studentProgress) {
		this.studentProgress = studentProgress;
	}

	public Boolean getMarkedforTrial() {
		return markedforTrial;
	}

	public void setMarkedforTrial(Boolean markedforTrial) {
		this.markedforTrial = markedforTrial;
	}

	public Long getStudentSubjectProfileId() {
		return studentSubjectProfileId;
	}

	public void setStudentSubjectProfileId(Long studentSubjectProfileId) {
		this.studentSubjectProfileId = studentSubjectProfileId;
	}
}
