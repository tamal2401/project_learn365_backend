package org.learn365.modal.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chapter_video")
public class ChapterVideo extends BaseEntity {

	@Column(name = "chapter_video_name", nullable = false, unique = true)
	private String videoname;
	@Column(name = "chapter_video_url", nullable = false)
	private String videourl;
	@Column(name = "chapter_video_order", nullable = false)
	private Integer order;
	@ManyToOne
	@JoinColumn(name = "fk_chapter_id")
	private Chapter chapter;
	@Column(name = "concept_test_id")
	private Long test;

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

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}
}
