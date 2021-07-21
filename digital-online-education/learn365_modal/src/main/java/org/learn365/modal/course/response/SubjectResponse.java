package org.learn365.modal.course.response;

import java.util.List;

import org.learn365.modal.constants.StudentProgress;

public class SubjectResponse extends BaseEntity {

	private String name;
	private Integer order;
	private Boolean active;
	private Boolean delete;
	private String subjectPic;
	private String trialVideo;
	private List<ChapterResponse> chapters;
	private Long testid;
	private Boolean teststatus;
	private StudentProgress studentProgress = StudentProgress.NOTSTARTED;

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

	public Long getTestid() {
		return testid;
	}

	public void setTestid(Long testid) {
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
}
