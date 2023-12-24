package com.bookRegister.bookRegister.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig  {
	
	protected void configure(final HttpSecurity http) throws Exception {
	    http
	        .formLogin()
	        .loginPage("/login.html")
	        .failureUrl("/login-error.html")
	      .and()
	        .logout()
	        .logoutSuccessUrl("/index.html");
	}
}
