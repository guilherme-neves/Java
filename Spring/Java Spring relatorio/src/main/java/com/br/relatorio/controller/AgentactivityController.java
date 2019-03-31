package com.br.relatorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.relatorio.dao.AgentactivityDao;
import com.br.relatorio.model.tblagentactivity;

@RestController
@RequestMapping("/api/agentactivity")
public class AgentactivityController {

	@Autowired
	private AgentactivityDao dao;
	
	@GetMapping
	public Iterable<tblagentactivity> lista(){
		return dao.findAll();
	}
	
	
}
