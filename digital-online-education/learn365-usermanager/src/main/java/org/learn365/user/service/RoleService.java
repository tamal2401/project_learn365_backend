package org.learn365.user.service;

import org.learn365.modal.user.entity.Role;

import java.util.List;

public interface RoleService {

	public Role roleWithAliasAvailable(String roleAlias);
	public List<String> getAllRoleAvailable();

}
