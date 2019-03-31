package com.br.monitoramento.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.monitoramento.dao.ColetorDao;
import com.br.monitoramento.model.Coletor;
import com.br.monitoramento.tarefas.Tarefas;

@RestController
public class MonitoramentoController {

	// @Autowired
	private ColetorDao dao;

	public SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");

	public MonitoramentoController(ColetorDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/iniciar")
	public String Iniciar() {
		Runnable tarefa = new Tarefas(dao);
		Thread thread = new Thread(tarefa);
		thread.start();
		return "sucesso";
	}

	@CrossOrigin
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = { "application/json" })
	public Iterable<Coletor> Listar() {
		return dao.findAll();
	}

	// Ramal Origem
	@CrossOrigin
	@RequestMapping(value = "/listar2/{origem}", method = RequestMethod.GET, produces = { "application/json" })
	public Iterable<Coletor> Listar2(@PathVariable("origem") String origem) throws ParseException {
		System.out.println(origem);
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		Date t = new Date(Long.parseLong(origem));
		System.out.println(stf.format(t));

		return dao.Origem2(origem);
	}

	// Data
	@CrossOrigin
	@RequestMapping(value = "/listar3", method = RequestMethod.GET, produces = { "application/json" })
	public Iterable<Coletor> Listar3() throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		return dao.data(stf.parse("2018-01-22"));
	}

	@CrossOrigin
	@RequestMapping(value = "/listar4", method = RequestMethod.GET, produces = { "application/json" })
	public Iterable<Coletor> Listar4() throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		return dao.lista(stf.parse("2018-01-19"), stf.parse("2018-01-22"));
	}

	@CrossOrigin
	@RequestMapping(value = "/listar5", method = RequestMethod.GET, produces = { "application/json" })
	public List<Coletor> Listar5() throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		Date t = new Date();
		return dao.data2(stf.parse(stf.format(t)));
	}

	// PathVariable
	@CrossOrigin
	@RequestMapping(value = "/listar6/{data}/{ramal}", method = RequestMethod.GET, produces = { "application/json" })
	public List<Coletor> Listar6(@PathVariable("data") String data, @PathVariable("ramal") String ramal)
			throws ParseException {
		System.out.println(data);
		System.out.println(ramal);
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		Date t = new Date(data);
		System.out.println(t);
		return dao.data3(stf.parse(stf.format(t)));
	}

	// RequestParam
	@CrossOrigin
	@RequestMapping(value = "/listar6/", method = RequestMethod.GET, produces = { "application/json" })
	public List<Coletor> Listar7(@RequestParam(value="data", required=false) String data, @RequestParam(value="ramal", required=false) String ramal)
			throws ParseException {
		System.out.println(data);
		// System.out.println(ramal);
	    Date t;

		if (!ramal.equals("undefined") && !data.equals("undefined")) {
			t = new Date(data);
		     return	dao.OrigemData(ramal, st.parse(st.format(t)));
		} else if (data.equals("undefined") && !ramal.equals("undefined")) {
		     return	 dao.Origem2(ramal);
		} else if (!data.equals("undefined") && ramal.equals("undefined")) {
			t = new Date(data);
		    return	 dao.data3(st.parse(st.format(t)));
		} else {
			 t = new Date();
		   return dao.data2(st.parse(st.format(t)));
		}

	}

	// Tipo de saida de ligação
	@CrossOrigin
	@RequestMapping(value = "/conta/{tipo}", method = RequestMethod.GET, produces = { "application/json" })
	public long Tipo(@PathVariable("tipo") String tipo) {
		return dao.Tipo(tipo);
	}

	@CrossOrigin
	@RequestMapping(value = "/conta2/{tipo}", method = RequestMethod.GET, produces = { "application/json" })
	public long Tipo2(@PathVariable("tipo") String tipo) throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");

		return dao.TipoData2(tipo, stf.parse("2018-01-21"), stf.parse("2018-01-23"));
	}

	@CrossOrigin
	@RequestMapping(value = "/conta3/{tipo}", method = RequestMethod.GET, produces = { "application/json" })
	public long Tipo3(@PathVariable("tipo") String tipo) throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
		Date t = new Date();
		return dao.TipoData3(stf.parse(stf.format(t)), tipo);
	}

	@CrossOrigin
	@RequestMapping(value = "/destino/{destino}", method = RequestMethod.GET, produces = { "application/json" })
	public List<Coletor> Destino(@PathVariable("destino") String destino) {
		System.out.println(destino);
		return dao.Destino(destino);
	}

}
