package org.learn365.modal.course.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chapter_generator")
	@SequenceGenerator(name="chapter_generator", sequenceName = "chapter_seq")
	private Long id;
	@Column(name = "chapter_name", nullable = false)
	private String chapterName;
	@Column(name = "chapter_pic", nullable = false, updatable = true)
	private String chapterpic;
	@Column(name = "chapter_trialvideo_url", updatable = true)
	private String trialVideo;
	@Column(name = "chapter_order", nullable = false)
	private Integer order;
	@ManyToOne
	@JoinColumn(name = "fk_subject_id")
	private Subject subject;
	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ChapterVideo> chaptervideo;
	@Column(name = "isForTrial")
	private Boolean markedforTrial=false;
	@Column(name="chapter_path_url",nullable = false)
	private String chapterpath;

	@Column(name = "chapter_test_id")
	private String testid;

	@Column(name = "intailLockstate",nullable = false)
	private Boolean intialunlock=false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public Boolean getMarkedforTrial() {
		return markedforTrial;
	}

	public void setMarkedforTrial(Boolean markedforTrial) {
		this.markedforTrial = markedforTrial;
	}

	public String getChapterpath() {
		return chapterpath;
	}

	public void setChapterpath(String chapterpath) {
		this.chapterpath = chapterpath;
	}

	public Boolean getIntialunlock() {
		return intialunlock;
	}

	public void setIntialunlock(Boolean intialunlock) {
		this.intialunlock = intialunlock;
	}
}
