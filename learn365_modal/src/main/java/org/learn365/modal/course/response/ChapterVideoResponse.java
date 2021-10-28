package org.learn365.modal.course.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.learn365.modal.constants.StudentProgress;

import javax.persistence.Column;

public class ChapterVideoResponse extends BaseEntity {
	private Long id;
	private String videoname;
	private String videourl;
	private Integer order;
	private String testid;
	private boolean testStatus;
	private Long timespent;
	public StudentProgress studentProgress = StudentProgress.NOTSTARTED;
	@JsonIgnore
	private Boolean intialunlock=false;
	private Long studentchVideoProfileId;
	private String thumbnail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public Boolean getIntialunlock() {
		return intialunlock;
	}

	public void setIntialunlock(Boolean intialunlock) {
		this.intialunlock = intialunlock;
	}

	public StudentProgress getStudentProgress() {
		return studentProgress;
	}

	public void setStudentProgress(StudentProgress studentProgress) {
		this.studentProgress = studentProgress;
	}

	public boolean isTestStatus() {
		return testStatus;
	}

	public void setTestStatus(boolean testStatus) {
		this.testStatus = testStatus;
	}

	public Long getTimespent() {
		return timespent;
	}

	public void setTimespent(Long timespent) {
		this.timespent = timespent;
	}

	public Long getStudentchVideoProfileId() {
		return studentchVideoProfileId;
	}

	public void setStudentchVideoProfileId(Long studentchVideoProfileId) {
		this.studentchVideoProfileId = studentchVideoProfileId;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
