package org.learn365.modal.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "learn365_permission")
public class Permission extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "permission_name", nullable = false, updatable = false)
	private String permissionName;
	private String permissionDescription;

	public Permission() {
	}

	public Permission(String permissionName, String permissionDescription) {
		super();
		this.permissionName = permissionName;
		this.permissionDescription = permissionDescription;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}

}
