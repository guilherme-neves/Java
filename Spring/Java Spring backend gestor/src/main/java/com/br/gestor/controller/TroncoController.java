package com.br.gestor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestor.dao.TroncoDao;
import com.br.gestor.model.Tronco;

@CrossOrigin
@RestController
@RequestMapping("/api/tronco")
public class TroncoController {

	@Autowired
	private TroncoDao dao;

	@RequestMapping(method = { RequestMethod.GET }, produces = { "application/json" })
	public List<Tronco> lista() {
		return (List<Tronco>) dao.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST }, produces = { "application/json" })
	public Tronco salvar(@RequestBody Tronco tronco) {
		return dao.save(tronco);
	}
	
	@RequestMapping(value= {"/{tronco}"} ,method= {RequestMethod.DELETE},produces={"application/json"})
	public String deletar(@PathVariable("tronco") String tronco) {
		dao.deleteById(tronco);
		return "Deletado";
	}
	

}
