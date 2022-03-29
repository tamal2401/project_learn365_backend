package org.learn365.file.uploader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ChapterResponse extends BaseEntity {

	private Long id;
	private String chapterName;
	@JsonIgnore
	private String chapterpic;
	@JsonIgnore
	private String trialVideo;
	private Integer order;
	@JsonIgnore
	private Boolean active;
	private List<ChapterVideoResponse> chaptervideo;
	private String testid;
	private Boolean teststaus;
	private Boolean markedforTrial;
	private StudentProgress studentProgress = StudentProgress.NOTSTARTED;
	private Long studentChapterProfileId;
	private String chapterpath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterpic() {
		return chapterpic;
	}

	public void setChapterpic(String chapterpic) {
		this.chapterpic = chapterpic;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
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

	public List<ChapterVideoResponse> getChaptervideo() {
		return chaptervideo;
	}

	public void setChaptervideo(List<ChapterVideoResponse> chaptervideo) {
		this.chaptervideo = chaptervideo;
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

	public Boolean getTeststaus() {
		return teststaus;
	}

	public void setTeststaus(Boolean teststaus) {
		this.teststaus = teststaus;
	}

	public Boolean getMarkedforTrial() {
		return markedforTrial;
	}

	public void setMarkedforTrial(Boolean markedforTrial) {
		this.markedforTrial = markedforTrial;
	}

	public Long getStudentChapterProfileId() {
		return studentChapterProfileId;
	}

	public void setStudentChapterProfileId(Long studentChapterProfileId) {
		this.studentChapterProfileId = studentChapterProfileId;
	}

	public String getChapterpath() {
		return chapterpath;
	}

	public void setChapterpath(String chapterpath) {
		this.chapterpath = chapterpath;
	}
}
