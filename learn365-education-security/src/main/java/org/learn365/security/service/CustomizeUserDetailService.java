package org.learn365.security.service;

import java.util.Optional;

import org.learn365.modal.user.entity.Learn365User;
import org.learn365.security.modal.AuthCustomer;
import org.learn365.security.repository.Learn365UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomizeUserDetailService implements UserDetailsService {

	@Autowired
	private Learn365UserRepository learn365UserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Learn365User> learn365User = learn365UserRepository.findByEmailAndDeleted(email, false);
		learn365User.orElseThrow(() -> new UsernameNotFoundException("User is not available with email id:" + email));
		UserDetails userDetail = new AuthCustomer(learn365User.get());
		new AccountStatusUserDetailsChecker().check(userDetail);
		return userDetail;
	}

}
