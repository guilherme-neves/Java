package com.br.gestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestor.dao.ContatoDao;
import com.br.gestor.model.Contato;

@RequestMapping("api/contato")
@RestController
@CrossOrigin
public class ContatoController {

	@Autowired
	private ContatoDao dao;
	
	@GetMapping
	public Iterable<Contato> lista(){
	   return dao.findAll();	
	}
	
	@PostMapping
	public Contato salvar(@RequestBody Contato contato) {
		return dao.save(contato);
	}
	
	
}
