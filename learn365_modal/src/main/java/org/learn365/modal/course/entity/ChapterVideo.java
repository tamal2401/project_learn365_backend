package org.learn365.modal.course.entity;

import javax.persistence.*;

@Entity
@Table(name = "chapter_video")
public class ChapterVideo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chaptervideo_generator")
	@SequenceGenerator(name="chaptervideo_generator", sequenceName = "chaptervideo_seq")
	private Long id;
	@Column(name = "chapter_video_name", nullable = false, unique = true)
	private String videoname;
	@Column(name = "chapter_video_url")
	private String videourl;
	@Column(name = "chapter_video_order", nullable = false)
	private Integer order;
	@Column(name = "intailLockstate",nullable = false)
	private Boolean intialunlock=false;
	@ManyToOne
	@JoinColumn(name = "fk_chapter_id")
	private Chapter chapter;
	@Column(name = "concept_test_id")
	private String testid;
	@Column(name = "video_thumbNail")
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

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Boolean getIntialunlock() {
		return intialunlock;
	}

	public void setIntialunlock(Boolean intialunlock) {
		this.intialunlock = intialunlock;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
