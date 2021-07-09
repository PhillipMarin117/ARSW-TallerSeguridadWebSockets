package edu.eci.arsw.segurity.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
			.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
		// remember the password that is printed out and use in the next step
		// System.out.println(encoder.encode("password"));

		UserDetails user = User.withUsername("user")
			.password(encoder.encode("password"))
			.roles("USER")
			.build();

			PasswordEncoder encoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// outputs {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
		// remember the password that is printed out and use in the next step
		// System.out.println(encoder.encode("password"));

		UserDetails user2 = User.withUsername("user2")
			.password(encoder2.encode("password2"))
			.roles("USER")
			.build();

		InMemoryUserDetailsManager imUserDetails =  new InMemoryUserDetailsManager();
		imUserDetails.createUser(user);
		imUserDetails.createUser(user2);
		return imUserDetails;
	}
}