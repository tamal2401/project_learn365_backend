package org.learn365.user.repository;

import java.util.List;
import java.util.Optional;

import org.learn365.modal.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Optional<Role> findByRoleAlias(String roleName);

	@Query(value = "select r.roleAlias from Role r")
	public List<String> findAllAvailableRole();

}
