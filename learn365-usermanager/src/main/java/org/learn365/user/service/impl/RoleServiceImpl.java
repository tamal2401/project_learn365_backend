package org.learn365.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.learn365.exception.RoleNotFoundException;
import org.learn365.modal.user.entity.Role;
import org.learn365.user.repository.RoleRepository;
import org.learn365.user.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role roleWithAliasAvailable(String roleAlias) {
		Optional<Role> role = roleRepository.findByRoleAlias(roleAlias);
		return role.orElseThrow(() -> new RoleNotFoundException("No mapping Role is available"));
	}

	@Override
	public List<String> getAllRoleAvailable() {
		return roleRepository.findAllAvailableRole();
	}

}
