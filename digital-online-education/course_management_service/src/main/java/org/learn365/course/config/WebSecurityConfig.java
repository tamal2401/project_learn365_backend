package org.learn365.course.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${learn365.security.ignore}")
	String learn365ignore;

	@Override
	public void configure(WebSecurity web) throws Exception {
		if (learn365ignore != null) {
			String[] ignores = StringUtils.split(learn365ignore, ",");
			Arrays.asList(ignores).stream().forEach(ignore -> web.ignoring().antMatchers(ignore));

		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().httpStrictTransportSecurity().and().cacheControl().and().xssProtection().and()
				.contentTypeOptions().and().frameOptions().disable();// had to disable this coz it was blocking some of
																		// Jasper visualize.js calls

		http.authorizeRequests().antMatchers("/v1/user/service/**").permitAll().anyRequest().fullyAuthenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
	}

}
