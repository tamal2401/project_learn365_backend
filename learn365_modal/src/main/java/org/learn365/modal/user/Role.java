package org.learn365.modal.user;

import java.util.Date;
import java.util.Set;

import org.learn365.modal.user.entity.Permission;

public class Role {

	private Long id;
	private String roleName;
	private boolean active;
	private String rolealiasName;
	private String roleDescription;
	private Set<Permission> rolePermission;
	private Date createdAt;
	private Date updatedAt;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRolealiasName() {
		return rolealiasName;
	}

	public void setRolealiasName(String rolealiasName) {
		this.rolealiasName = rolealiasName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<Permission> getRolePermission() {
		return rolePermission;
	}

	public void setRolePermission(Set<Permission> rolePermission) {
		this.rolePermission = rolePermission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
