package org.learn365.modal.user.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "learn365_permission")
public class Permission extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_generator")
	@SequenceGenerator(name="permission_generator", sequenceName = "permission_seq")
	private Long id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
