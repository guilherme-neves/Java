package br.com.alura.forum.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

public class Autenticaoviatokenfilter extends OncePerRequestFilter {

    
	private TokenService tokenService;
	private UsuarioRepository userrepository;
	
	public Autenticaoviatokenfilter(TokenService tokenService,UsuarioRepository userrepository) {
		this.tokenService = tokenService;
		this.userrepository = userrepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			autenticarCliente(token);
		}
		
		filterchain.doFilter(request, response);
		
		
	}

	private String recuperarToken(HttpServletRequest request) {
	    String token = request.getHeader("Authorization");
	    if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
	    	return null;	
	    }
	    
	    return token.substring(7, token.length());
		
	}
	
	
	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = userrepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario,null , usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	

}
