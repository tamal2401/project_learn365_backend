package org.learn365.modal.course.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_inprogress_course")
public class UserInprogressCourse extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userInprogressCourse_generator")
	@SequenceGenerator(name="userInprogressCourse_generator", sequenceName = "userInprogressCourse_seq")
	private Long id;
	@Column(name = "user_id", nullable = false, updatable = false)
	private String userid;
	@Column(name = "user_name", nullable = false, updatable = false)
	private String userName;
	@Column(name = "class_id", nullable = false, updatable = false)
	private String classid;
	@Column(name = "subject_id", nullable = false, updatable = false)
	private String subjectid;
	@Column(name = "chapter_id", nullable = false, updatable = false)
	private String chapterid;
	@Column(name = "video_id", nullable = false, updatable = false)
	private String videoid;
	@Column(name = "video_name", nullable = false, updatable = false)
	private String videoName;
	@Column(name = "status", nullable = false, updatable = false)
	private String status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
