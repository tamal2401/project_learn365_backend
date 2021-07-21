package org.learn365.test.model;

import java.time.LocalDate;

public class BaseEntity {

	private LocalDate createdAt = LocalDate.now();
	private LocalDate updatedAt = LocalDate.now();
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
