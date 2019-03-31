package com.br.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestor.dao.RamalDao;
import com.br.gestor.model.Ramal;

@CrossOrigin
@RestController
@RequestMapping("/api/ramal")
public class RamalController {

	@Autowired
	private RamalDao dao;
	
	@RequestMapping(method= RequestMethod.GET)
	public List<Ramal> Lista(){
	   return  dao.lista();	 
	}
	
	@RequestMapping(method= {RequestMethod.POST},produces={"application/json"})
	public Ramal Salvar(@RequestBody Ramal ramal) {
		return dao.save(ramal); 
	}
	
	@RequestMapping(value= {"/{ramal}"} ,method= {RequestMethod.DELETE},produces={"application/json"})
	public String deletar(@PathVariable("ramal") String ramal) {
		dao.deleteById(ramal); 
		return "deletar";
	}
	
	
}
