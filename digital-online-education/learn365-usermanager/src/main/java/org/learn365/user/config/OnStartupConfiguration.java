package org.learn365.user.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.learn365.modal.user.entity.Permission;
import org.learn365.modal.user.entity.Role;
import org.learn365.user.repository.RoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnStartupConfiguration implements ApplicationListener<ApplicationReadyEvent> {

	private RoleRepository roleRepository;

	private static Boolean isDataPopulated = true;

	public OnStartupConfiguration(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (!isDataPopulated) {
			build();
		}
	}

	private void build() {
		Role admin = new Role("ROLE_ADMIN", "ADMIN", "Super user Role");
		admin.setRolePermission(buildpermission(admin.getRoleAlias()));
		updateCommanRoleDetails(admin);
		roleRepository.save(admin);

		Role student = new Role("ROLE_STUDENT", "STUDENT", "Student will register with this role");
		student.setRolePermission(buildpermission(student.getRoleAlias()));
		updateCommanRoleDetails(student);
		roleRepository.save(student);

		Role teacher = new Role("ROLE_TEACHER", "TEACHER", "Teacher can register with this role");
		teacher.setRolePermission(buildpermission(teacher.getRoleAlias()));
		updateCommanRoleDetails(teacher);
		roleRepository.save(teacher);
	}

	private void updateCommanRoleDetails(Role role) {
		role.setCreatedAt(LocalDate.now());
		role.setActive(true);
		role.setUpdatedAt(LocalDate.now());
	}

	public Set<Permission> buildpermission(String rolename) {
		Set<Permission> permissionDetails = null;
		if ("ADMIN".equals(rolename)) {
			permissionDetails = new HashSet<>();
			Permission p3 = new Permission("Register", "User can Add Item");
			addPermissionDetails(p3);
			Permission p4 = new Permission("Update", "User can Delete Item");
			addPermissionDetails(p4);
			Permission p5 = new Permission("Delete", "User can update Item");
			addPermissionDetails(p5);
			Permission p = new Permission("Create_content", "User can Add Item");
			addPermissionDetails(p);
			Permission p1 = new Permission("Update_content", "User can Delete Item");
			addPermissionDetails(p1);
			Permission p2 = new Permission("Delete_content", "User can update Item");
			addPermissionDetails(p2);
			permissionDetails.add(p);
			permissionDetails.add(p1);
			permissionDetails.add(p2);

			permissionDetails.add(p3);
			permissionDetails.add(p4);
			permissionDetails.add(p5);

		}
		if ("STUDENT".equals(rolename)) {
			permissionDetails = new HashSet<>();
			Permission p = new Permission("Register", "User can Add Item");
			addPermissionDetails(p);
			Permission p1 = new Permission("Update", "User can Delete Item");
			addPermissionDetails(p1);
			Permission p2 = new Permission("Delete", "User can update Item");
			addPermissionDetails(p2);
			Permission p3 = new Permission("Attend_Classes", "Can attend class");
			addPermissionDetails(p3);
			Permission p4 = new Permission("Can_Subscribe", "Can Subscribe for course ");
			addPermissionDetails(p4);
			permissionDetails.add(p);
			permissionDetails.add(p1);
			permissionDetails.add(p2);
			permissionDetails.add(p3);
			permissionDetails.add(p4);
		}
		if ("TEACHER".equals(rolename)) {
			permissionDetails = new HashSet<>();
			Permission p = new Permission("View_Student_Test_Profile", "Create user");
			addPermissionDetails(p);
			Permission p0 = new Permission("Register", "User can Add Item");
			addPermissionDetails(p0);
			Permission p1 = new Permission("Update", "User can Delete Item");
			addPermissionDetails(p1);
			Permission p2 = new Permission("Delete", "User can update Item");
			addPermissionDetails(p2);
			permissionDetails.add(p);
			permissionDetails.add(p0);
			permissionDetails.add(p1);
			permissionDetails.add(p2);

		}
		return permissionDetails;
	}

	private void addPermissionDetails(Permission p) {
		p.setActive(true);
		p.setCreatedAt(LocalDate.now());
		p.setUpdatedAt(LocalDate.now());

	}

}
