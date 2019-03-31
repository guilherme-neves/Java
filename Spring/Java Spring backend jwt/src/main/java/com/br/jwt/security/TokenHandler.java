package com.br.jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenHandler {
	private final String secret;
	private final UserDetailsService userService;
	static final long EXPIRATION_TIME = 860_000_000;

	public TokenHandler(String secret, UserDetailsService userService) {
		this.secret = secret;
		this.userService = userService;
	}

	public UserDetails parseUserFromToken(String token) {
		String username = ((Claims) Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token)
				.getBody())
				.getSubject();
		return this.userService.loadUserByUsername(username);
	}

	public String createTokenForUser(UserDetails user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}
}
