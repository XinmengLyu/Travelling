package com.Travelling.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()  // disable csrf
			.authorizeRequests()  // allow load resource files
				.antMatchers("/css/**","/fonts/**","/images/**","/js/**","/scss/**")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/","/home","/signup","/update","/test","/test1")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.authorizeRequests()
				.antMatchers("/update", "/test", "/test1")
				.hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}


	@Bean
	public UserDetailsService userDetailService(){
		UserDetails user = User.withDefaultPasswordEncoder().username("hhaarryy").password("123456").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

}
