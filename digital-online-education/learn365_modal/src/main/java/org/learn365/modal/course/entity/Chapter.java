package org.learn365.modal.course.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chapter")
public class Chapter extends BaseEntity {

	@Column(name = "chapter_name", nullable = false)
	private String chapterName;
	@Column(name = "chapter_pic", nullable = false, updatable = true)
	private String chapterpic;
	@Column(name = "chapter_trialvideo_url", nullable = false, updatable = true)
	private String trialVideo;
	@Column(name = "chapter_order", nullable = false)
	private Integer order;
	@ManyToOne
	@JoinColumn(name = "fk_subject_id")
	private Subject subject;
	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
	private List<ChapterVideo> chaptervideo;

	@Column(name = "chapter_test_id")
	private Long test;

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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<ChapterVideo> getChaptervideo() {
		return chaptervideo;
	}

	public void setChaptervideo(List<ChapterVideo> chaptervideo) {
		this.chaptervideo = chaptervideo;
	}

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

}
