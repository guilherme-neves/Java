package com.br.gestor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.gestor.dao.GestorDao;
import com.br.gestor.model.Gestor;
import com.br.gestor.tarefas.*;

@RestController
@CrossOrigin
public class GestorController {

	@Autowired
	private GestorDao dao;

	@RequestMapping("/start")
	public String Iniciar() {
		Runnable tarefa = new Tarefas(dao);
		Thread thread = new Thread(tarefa);
		thread.start();
		return "Iniciar";
	}

	@RequestMapping("/lista")
	public List<Gestor> lista2() {
		return dao.listar();
	}


	@RequestMapping("/gestor/lista")
	public Iterable<Gestor> lista() {
		return dao.listarOrdem();
	}


	@RequestMapping("/gestor/dados")
	public List dados() throws ParseException {
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
		Date t = new Date();
		List<Object> dados = new ArrayList<>();
		Long entrada = dao.Entrada("1", st.parse(st.format(t)));
		Long saida = dao.Entrada("2", st.parse(st.format(t)));
		Long Transf = dao.Entrada("35", st.parse(st.format(t)));
		dados.add(entrada);
		dados.add(saida);
		dados.add(Transf);
    	return dados;
	}


	@RequestMapping("/gestor/dados2")
	public String dados2() throws ParseException {
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
		Long entrada = dao.Entrada("2", st.parse("2018-05-25"));
		Long saida = dao.Entrada("1", st.parse("2018-05-25"));
		System.out.println("Entrada:" + entrada + " " + "Saida: " + saida);
		return "String";
	}
	

	@RequestMapping(value= {"/gestor/ramal/{ramal}"})
	public List<Gestor> ramal(@PathVariable("ramal") String ramal){
		return dao.Buscar(ramal);
	}
	

	@RequestMapping(value= {"/gestor/ramal/{numero}/{opcao}"})
	public List<Gestor> ramal(@PathVariable("numero") String numero,@PathVariable("opcao") String opcao){
		if(opcao.equals("Ramal")) {
		   return	dao.Buscar(numero);
		}else if(opcao.equals("Telefone")) {
			return dao.BuscarTelefone(numero);
		}
		
		return null;
	}

}
