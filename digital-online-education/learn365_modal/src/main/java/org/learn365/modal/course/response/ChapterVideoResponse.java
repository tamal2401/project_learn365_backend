package org.learn365.modal.course.response;

import org.learn365.modal.constants.StudentProgress;

import javax.persistence.Column;

public class ChapterVideoResponse extends BaseEntity {

	private String videoname;
	private String videourl;
	private String order;
	private Long test;
	private boolean testStatus;
	private Long timespent;
	public StudentProgress studentProgress = StudentProgress.NOTSTARTED;

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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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
}
