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
@Table(name = "subject")
public class Subject extends BaseEntity {

	@Column(name = "subject_name", nullable = false)
	private String name;
	@Column(name = "subject_order", nullable = false)
	private Integer order;
	@Column(name = "subject_profile_pic", nullable = false)
	private String subjectPic;
	@Column(name = "standard_trialvideo_url", nullable = false, updatable = true)
	private String trialVideo;
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Chapter> chapters;
	@ManyToOne
	@JoinColumn(name = "fk_standard_id")
	private Standard standard;
	@Column(name = "subject_test_id")
	private Long testid;

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

	public Long getTestid() {
		return testid;
	}

	public void setTestid(Long testid) {
		this.testid = testid;
	}

}
