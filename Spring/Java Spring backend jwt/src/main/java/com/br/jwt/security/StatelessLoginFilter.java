package com.br.jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
	private final TokenAuthenticationService tokenAuthenticationService;
	private final UserDetailsService userDetailsService;

	protected StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
			UserDetailsService userDetailsService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		LoginDTO login = (LoginDTO) new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

		UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(login.getLogin(),
				login.getSenha());
		return getAuthenticationManager().authenticate(loginToken);
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		UserDetails authenticatedUser = this.userDetailsService.loadUserByUsername(authentication.getName());
		UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		this.tokenAuthenticationService.addAuthentication(response, userAuthentication);

		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}

}
