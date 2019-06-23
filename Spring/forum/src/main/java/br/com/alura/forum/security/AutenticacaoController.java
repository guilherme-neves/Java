package br.com.alura.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.alura.forum.controller.dto.TokenTdo;

import br.com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody LoginForm form){
	 UsernamePasswordAuthenticationToken dadosLogin = form.converter();
	 
	 
	 try {
		 Authentication authentication = authenticationManager.authenticate(dadosLogin);
		 String token = tokenService.gerarToken(authentication);
		 return ResponseEntity.ok(new TokenTdo(token, "Bearer"));
	 }catch (Exception e) {
		 return ResponseEntity.badRequest().build();
	}
		
		
	}
	
	
}
