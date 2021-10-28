package org.learn365.modal.subscription.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "offered_subject")
public class OfferedSubject extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offeredSubject_generator")
	@SequenceGenerator(name="offeredSubject_generator", sequenceName = "offeredSubject_seq")
	private Long id;
	@Column(name = "offered_subjectId", nullable = false)
	private String offeredSubjectid;
	@Column(name = "offered_subjectName", nullable = false)
	private String offeredSubjectname;
	@Column(name = "subject_description")
	private String offeredSubjectDesccription;
	@Column(name = "subject_picture")
	private String SubjectPicture;
	@Column(name = "subject_isActive")
	private Boolean active;
	@ManyToOne
	@JoinColumn(name = "offered_planId")
	private SubscriptionPlan subscriptionPlan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfferedSubjectid() {
		return offeredSubjectid;
	}

	public void setOfferedSubjectid(String offeredSubjectid) {
		this.offeredSubjectid = offeredSubjectid;
	}

	public String getOfferedSubjectname() {
		return offeredSubjectname;
	}

	public void setOfferedSubjectname(String offeredSubjectname) {
		this.offeredSubjectname = offeredSubjectname;
	}

	public String getOfferedSubjectDesccription() {
		return offeredSubjectDesccription;
	}

	public void setOfferedSubjectDesccription(String offeredSubjectDesccription) {
		this.offeredSubjectDesccription = offeredSubjectDesccription;
	}

	public String getSubjectPicture() {
		return SubjectPicture;
	}

	public void setSubjectPicture(String subjectPicture) {
		SubjectPicture = subjectPicture;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}


}
