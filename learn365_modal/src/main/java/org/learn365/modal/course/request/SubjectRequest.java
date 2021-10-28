package org.learn365.modal.course.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class SubjectRequest extends BaseEntity {

	@NotBlank(message = "Subject name should not be blank")
	private String name;
	private Integer order;
	@NotBlank(message = "SubjectPic should not be blank")
	private String subjectPic;
	private String trialVideo;
	private List<ChapterRequest> chapters;
	private String testid;

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

	public String getSubjectPic() {
		return subjectPic;
	}

	public void setSubjectPic(String subjectPic) {
		this.subjectPic = subjectPic;
	}

	public List<ChapterRequest> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterRequest> chapters) {
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
}
