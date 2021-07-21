package org.learn365.modal.course.request;

import javax.validation.constraints.NotBlank;

public class UserVideoTrackerRequest extends BaseEntity {

	@NotBlank
	private Long userId;
	private String sessionId;
	@NotBlank
	private Long subjectid;
	@NotBlank
	private Long standardid;
	@NotBlank
	private Long chapterid;
	@NotBlank
	private String videoid;
	@NotBlank
	private String videoorder;
	@NotBlank
	private String videoname;
	@NotBlank
	private String videostoptime;
	@NotBlank
	private Boolean test;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Long getStandardid() {
		return standardid;
	}

	public void setStandardid(Long standardid) {
		this.standardid = standardid;
	}

	public Long getChapterid() {
		return chapterid;
	}

	public void setChapterid(Long chapterid) {
		this.chapterid = chapterid;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public String getVideoorder() {
		return videoorder;
	}

	public void setVideoorder(String videoorder) {
		this.videoorder = videoorder;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getVideostoptime() {
		return videostoptime;
	}

	public void setVideostoptime(String videostoptime) {
		this.videostoptime = videostoptime;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
