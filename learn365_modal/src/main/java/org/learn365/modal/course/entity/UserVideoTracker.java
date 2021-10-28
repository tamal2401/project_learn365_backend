package org.learn365.modal.course.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_video_tracker")
public class UserVideoTracker extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserVideoTracker_generator")
	@SequenceGenerator(name="UserVideoTracker_generator", sequenceName = "UserVideoTracker_seq")
	private Long id;
	@Column(name = "track_userid", nullable = false)
	private Long userId;
	@Column(name = "track_subjectid", nullable = false)
	private Long subjectid;
	@Column(name = "track_standardid", nullable = false)
	private Long standardid;
	@Column(name = "track_chapterid", nullable = false)
	private Long chapterid;
	@Column(name = "track_videoid", nullable = false)
	private String videoid;
	@Column(name = "track_videoOrder", nullable = false)
	private String videoorder;
	@Column(name = "track_videoname", nullable = false)
	private String videoname;
	@Column(name = "track_videostop_time", nullable = false)
	private String videostoptime;
	@Column(name = "isTest")
	private Boolean test;
	@Column(name = "session_id", nullable = false)
	private String sessionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
