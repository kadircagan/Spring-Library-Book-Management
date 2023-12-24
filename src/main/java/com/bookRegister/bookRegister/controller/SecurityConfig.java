package com.bookRegister.bookRegister.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
