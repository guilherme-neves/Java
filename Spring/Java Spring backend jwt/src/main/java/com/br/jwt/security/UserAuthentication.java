package com.br.jwt.security;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthentication implements Authentication {
	private static final long serialVersionUID = -295250221436044510L;
	private final UserDetails user;
	private boolean authenticated = true;

	public UserAuthentication(UserDetails user) {
		this.user = user;
	}

	public String getName() {
		return this.user.getUsername();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getAuthorities();
	}

	public Object getCredentials() {
		return this.user.getPassword();
	}

	public UserDetails getDetails() {
		return this.user;
	}

	public Object getPrincipal() {
		return this.user;
	}

	public boolean isAuthenticated() {
		return this.authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
