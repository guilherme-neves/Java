package com.br.jwt.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.MalformedJwtException;

public class TokenAuthenticationService {

	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final TokenHandler tokenHandler;

	public TokenAuthenticationService(String secret, UserDetailsService userService) {
		this.tokenHandler = new TokenHandler(secret, userService);
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		UserDetails user = authentication.getDetails();
		response.addHeader("X-AUTH-TOKEN", this.tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("X-AUTH-TOKEN");
		if (!StringUtils.hasText(token)) {
			token = request.getParameter("X-AUTH-TOKEN");
		}
		if (token != null) {
			try {
				UserDetails user = this.tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			} catch (MalformedJwtException exception) {
				exception.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
