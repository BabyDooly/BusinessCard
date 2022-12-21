package com.sungmun.NoticeBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.sungmun.NoticeBoard.service.LoginSuccessHandler;
import com.sungmun.NoticeBoard.service.MemberService;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	MemberService service;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/ckeditor5/**", "lib/**", "/h2-console/*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")			
			.antMatchers("/member/logout","/*/update","/*/write").hasRole("BASIC")
			.antMatchers("/**").permitAll()
		.and()
			.formLogin().loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/")
			.successHandler(successHandler())
			.failureUrl("/member/login")
		.and()
			.logout()
			.logoutSuccessUrl("/")
			.logoutUrl("/member/logout");
		
		
		http.headers().frameOptions().disable();

	}

	public AuthenticationSuccessHandler successHandler() {
		return new LoginSuccessHandler("/");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}
}
