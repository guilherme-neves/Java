package com.br.relatorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.relatorio.dao.UserDao2;
import com.br.relatorio.dao.UsersDao;
import com.br.relatorio.model.tblusers;

import com.br.relatorio.model.tblusers3;

@RestController
@RequestMapping("api/users")
public class UsersController {

	@Autowired
	private UsersDao dao;
	

	private UserDao2 dao2;
	
	@GetMapping
	public Iterable<tblusers> lista(){
		return dao.findAll();
	}
	
	@GetMapping("/1")
	public List<tblusers3> lista2(){
		return dao2.lista2();
	}
	
}
