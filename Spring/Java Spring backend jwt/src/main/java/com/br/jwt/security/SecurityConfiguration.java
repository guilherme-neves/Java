package com.br.jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserDetailsService users;
	private TokenAuthenticationService tokenAuthenticationService;

	public SecurityConfiguration(UserDetailsService users) {
		this.users = users;
		this.tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", users);
	}

	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http
				.authorizeRequests().antMatchers(new String[] { "/api/public/**" })).permitAll()
						.antMatchers(HttpMethod.POST, new String[] { "/api/login" })).permitAll().anyRequest())
								.authenticated().and()).csrf().disable())
										.addFilterBefore(
												new StatelessLoginFilter("/api/login", this.tokenAuthenticationService,
														this.users, authenticationManager()),
												UsernamePasswordAuthenticationFilter.class)
										.addFilterBefore(
												new StatelessAuthenticationFilter(this.tokenAuthenticationService),
												UsernamePasswordAuthenticationFilter.class);
	}

	@SuppressWarnings("deprecation")
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(this.users)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	
		/*
		auth
		 .inMemoryAuthentication()
		 .passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("admin")
		.password("password")
		.roles("ADMIN");*/
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return this.tokenAuthenticationService;
	}

}
