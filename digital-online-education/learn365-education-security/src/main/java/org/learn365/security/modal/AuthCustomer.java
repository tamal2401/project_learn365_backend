package org.learn365.security.modal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.learn365.modal.user.entity.Learn365User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthCustomer implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101508266137817546L;
	private Learn365User user;

	public AuthCustomer(Learn365User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		user.getRole().forEach(role -> {
			if (role.isActive()) {
				grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
				role.getRolePermission().forEach(permission -> {
					if (permission.isActive()) {
						grantedAuthority.add(new SimpleGrantedAuthority(permission.getPermissionName()));
					}
				});
			}
		});

		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.isActive();
	}

}
