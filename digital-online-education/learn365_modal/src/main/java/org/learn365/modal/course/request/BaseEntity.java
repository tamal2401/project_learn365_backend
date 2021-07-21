package org.learn365.modal.course.request;

import java.time.LocalDate;

public class BaseEntity {

	private LocalDate createdAt;
	private LocalDate updatedAt;
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	

}
