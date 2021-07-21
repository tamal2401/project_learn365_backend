package org.learn365.modal.course.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Standard extends BaseEntity {

	@Column(name = "grade_name", nullable = false, updatable = false, unique = true)
	private String name;
	@Column(name = "grade_order", nullable = false, updatable = false)
	private Integer order;
	@Column(name = "isActive", nullable = false, updatable = true)
	private Boolean active;
	@Column(name = "grade_pic_url", nullable = false, updatable = true)
	private String picture;
	@Column(name = "grade_trialvideo_url", nullable = false, updatable = true)
	private String trialVideo;
	@OneToMany(mappedBy = "standard", cascade = CascadeType.ALL)
	private List<Subject> subjects;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}


}
