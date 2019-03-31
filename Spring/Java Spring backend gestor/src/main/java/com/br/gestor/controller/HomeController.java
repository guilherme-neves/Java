package com.br.gestor.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestor.model.Municipio;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String Home() {
		return "Iniciar";
	}

	@RequestMapping("/teste")
	public List<Municipio> teste() throws FileNotFoundException {

		BufferedReader br = null;
		List<Municipio> listMunicipio = new ArrayList<>();
		FileReader f = new FileReader("util/11.txt");
		br = new BufferedReader(f);
		try {
			while (br.ready()) {
				Municipio municipio = new Municipio();
				StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), "|");
				municipio.setCodigo(stringTokenizer.nextToken());
				municipio.setNome(stringTokenizer.nextToken());
				listMunicipio.add(municipio);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listMunicipio ;
	}

}
