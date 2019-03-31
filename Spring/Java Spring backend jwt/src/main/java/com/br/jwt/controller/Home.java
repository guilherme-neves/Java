package com.br.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	
	@RequestMapping("/api/public/home")
	public String home() {
	   return "String";	

	}
	
}
