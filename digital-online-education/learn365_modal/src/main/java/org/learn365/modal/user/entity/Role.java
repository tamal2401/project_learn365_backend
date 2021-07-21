package org.learn365.modal.user.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "learn365_role")
public class Role extends BaseEntity implements Serializable {

	@Column(name = "rolename", nullable = false, updatable = false)
	private String roleName;
	@Column(name = "rolealiasname", nullable = false, updatable = false)
	private String roleAlias;
	@Column(name = "roledescription", nullable = false, updatable = false)
	private String roleDescription;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rolePermission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> rolePermission;

	public Role() {
	}

	public Role(String roleName, String roleAlias, String roleDescription) {
		super();
		this.roleAlias = roleAlias;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getRoleAlias() {
		return roleAlias;
	}

	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}

}
