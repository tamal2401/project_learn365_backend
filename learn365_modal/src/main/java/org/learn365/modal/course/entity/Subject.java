package org.learn365.modal.course.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_generator")
	@SequenceGenerator(name="subject_generator", sequenceName = "subject_seq")
	private Long id;
	@Column(name = "subject_name", nullable = false)
	private String name;
	@Column(name = "subject_order", nullable = false)
	private Integer order;
	@Column(name = "subject_profile_pic", nullable = false)
	private String subjectPic;
	@Column(name = "standard_trialvideo_url", updatable = true)
	private String trialVideo;
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Chapter> chapters;
	@ManyToOne
	@JoinColumn(name = "fk_standard_id")
	private Standard standard;
	@Column(name = "subject_test_id")
	private String testid;

	@Column(name = "isForTrial")
	private Boolean markedforTrial=false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getSubjectPic() {
		return subjectPic;
	}

	public void setSubjectPic(String subjectPic) {
		this.subjectPic = subjectPic;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
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
}
