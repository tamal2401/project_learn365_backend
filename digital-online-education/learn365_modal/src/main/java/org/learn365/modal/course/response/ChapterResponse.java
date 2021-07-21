package org.learn365.modal.course.response;

import java.util.List;

import org.learn365.modal.constants.StudentProgress;

public class ChapterResponse extends BaseEntity {

	private String chapterName;
	private String chapterpic;
	private String trialVideo;
	private Integer order;
	private Boolean active;
	private SubjectResponse subject;
	private List<ChapterVideoResponse> chaptervideo;
	private Long test;
	private Boolean teststaus;
	private StudentProgress studentProgress = StudentProgress.NOTSTARTED;

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

	public SubjectResponse getSubject() {
		return subject;
	}

	public void setSubject(SubjectResponse subject) {
		this.subject = subject;
	}

	public List<ChapterVideoResponse> getChaptervideo() {
		return chaptervideo;
	}

	public void setChaptervideo(List<ChapterVideoResponse> chaptervideo) {
		this.chaptervideo = chaptervideo;
	}

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
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
}
