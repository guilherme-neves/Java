package com.br.jwt.security;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.br.jwt.model.Usuario;

@Repository
public class UserLoginService implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		TypedQuery<Usuario> query = this.manager
				.createQuery("select u from Usuario u where u.login = :login", Usuario.class)
				.setParameter("login", login);
		List<Usuario> users = query.getResultList();
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("O usuario " + login + " não existe");
		}
		
		return  (UserDetails) users.get(0);
	}
}
