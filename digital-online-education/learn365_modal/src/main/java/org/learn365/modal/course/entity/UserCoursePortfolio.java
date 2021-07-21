package org.learn365.modal.course.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_course_progress_report")
public class UserCoursePortfolio extends BaseEntity {

	@Column(name = "user_id", nullable = false, updatable = false)
	private String userid;
	@Column(name = "user_name", nullable = false, updatable = false)
	private String userName;
	@Column(name = "class_id", nullable = false, updatable = false)
	private String classid;
	@Column(name = "class_name", nullable = false, updatable = false)
	private String classname;
	@Column(name = "subject_id", nullable = false, updatable = false)
	private String subjectid;
	@Column(name = "subject_name", nullable = false, updatable = false)
	private String subjectname;
	@Column(name = "chapter_id", nullable = false, updatable = false)
	private String chapterid;
	@Column(name = "chapter_name", nullable = false, updatable = false)
	private String chaptername;
	@Column(name = "video_id", nullable = false, updatable = false)
	private String videoid;
	@Column(name = "video_name", nullable = false, updatable = false)
	private String videoname;
	@Column(name = "completeDate", nullable = false, updatable = false)
	private LocalDate completionDate;
	@Column(name = "time_spent", nullable = false, updatable = false)
	private String timeSpent;
	@Column(name = "marks_obtained")
	private String totalmarks;
	@Column(name = "status", nullable = false)
	private String status;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getChapterid() {
		return chapterid;
	}

	public void setChapterid(String chapterid) {
		this.chapterid = chapterid;
	}

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(String totalmarks) {
		this.totalmarks = totalmarks;
	}

}
