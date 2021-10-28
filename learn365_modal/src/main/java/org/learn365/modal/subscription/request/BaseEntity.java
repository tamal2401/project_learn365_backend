package org.learn365.modal.subscription.request;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BaseEntity {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String appName;
	private Integer order;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
