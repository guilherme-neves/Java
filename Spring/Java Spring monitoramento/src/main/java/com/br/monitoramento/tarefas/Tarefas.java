package com.br.monitoramento.tarefas;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.monitoramento.dao.ColetorDao;
import com.br.monitoramento.model.Coletor;


public class Tarefas implements Runnable {

	private Coletor business;

	private ColetorDao bussinessDao;

	public Tarefas(ColetorDao bussinessDao) {
		this.bussinessDao = bussinessDao;
	}


	@Override
	public void run() {
		// tring linhas[];
		String linha[];
		// System.out.println("Iniciar tarefa");
		try {
			ServerSocket servidor = new ServerSocket(69);
			System.out.println("Porta aberta 69");
			Socket cliente = servidor.accept();
			System.out.println("Nova Conexao com o cliente " + cliente.getInetAddress().getHostAddress());

			Scanner s = new Scanner(cliente.getInputStream());
			while (s.hasNextLine()) {
				int i = 0;
				linha = s.nextLine().split("\\|");
                 
				for (String string : linha) {
					i = i + 1;
					System.out.print(i + "=" + string + "|");
				}
				
				if (linha.length == 1) {

				} else if (linha.length == 9) {
					System.out.println("tamanho 9");
					/*
					for (String string : linha) {
						i = i + 1;
						System.out.print(i + "=" + string + "|");
					}*/
					
					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4]);

				} else if (linha.length == 14) {
					System.out.println("tamanho 14");
               
					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4]);

				} else if (linha.length == 13) {
					System.out.println("Tamanho 13");
                   /*
					for (String string : linha) {
						i = i + 1;
						System.out.print(i + "=" + string + "|");
					}*/
					
					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void salvar(String data, String horaInicial, String tronco, String ramal, String duracao, String origem,
			String TipoLicacao, String duracaoToque) {
		business = new Coletor();

		try {
			business.setDataInicio(converteData(data + " " + horaInicial));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// business.setHoraFinal(SomaHora(horaInicial, duracao));
		business.setTronco(tronco);
		business.setOrigem(ramal);
		business.setDuracao(duracao);
		business.setDestino(origem);
		business.setTipo(TipoLicacao);
		business.setToque(duracaoToque);
		bussinessDao.save(business);
	}

	public Date converteData(String d) throws ParseException {
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date data = stf.parse(d.replace(".", "/"));
		return data;
	}

	public String SomaHora(String tempo, String segundos) {
		if (tempo.equals("")) {
		}
		String[] tempo2 = tempo.split(":");
		int hora = Integer.parseInt(tempo2[0]);
		int minuto = Integer.parseInt(tempo2[1]);
		int segundo = Integer.parseInt(tempo2[2]);

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdff = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, minuto);
		cal.set(Calendar.SECOND, segundo);
		gc.setTime(cal.getTime());
		gc.add(Calendar.SECOND, CalcularSegundos(segundos));

		return sdff.format(gc.getTime());
	}

	public int CalcularSegundos(String tempo) {
		if (tempo.equals("")) {
			return 0;
		}
		String[] tempo2 = tempo.split(":");
		int hora = Integer.parseInt(tempo2[0]);
		int minuto = Integer.parseInt(tempo2[1]);
		int segundo = Integer.parseInt(tempo2[2]);

		int hora1 = hora * 60 * 60;
		int minuto1 = minuto * 60;
		int totalSegundo = hora1 + minuto1 + segundo;
		return totalSegundo;
	}

}
