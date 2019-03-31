package com.br.gestor.tarefas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;
import com.br.gestor.dao.GestorDao;
import com.br.gestor.model.Gestor;
import com.br.gestor.util.Diretorio;
import com.br.gestor.util.TrabalharData;

public class Tarefas implements Runnable {

	private Gestor gestor;
	private GestorDao dao;
	private Diretorio diretorio = new Diretorio();
	private TrabalharData td = new TrabalharData();
	private String l;

	public Tarefas(GestorDao dao) {
		this.dao = dao;
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
				l = s.nextLine();
				linha = l.split("\\|");
				if (linha.length == 1) {

				} else if (linha.length == 9) {
					System.out.println("tamanho 9");
					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4], "");

				} else if (linha.length == 14) {
					System.out.println("tamanho 14");

					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4], linha[13]);

				} else if (linha.length == 13) {
					System.out.println("Tamanho 13");
					salvar(linha[0], linha[1], linha[2], linha[3], linha[5], linha[6], linha[8], linha[4], "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvar(String data, String horaInicial, String tronco, String ramal, String duracao, String origem,
			String TipoLicacao, String duracaoToque, String ddr) throws IOException {
		gestor = new Gestor();

		try {
			gestor.setData_inicio(td.converteData(data + " " + horaInicial));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// business.setHoraFinal(SomaHora(horaInicial, duracao));
		gestor.setTronco(tronco);
		gestor.setRamal(ramal);
		gestor.setDuracao(duracao);
		gestor.setNumero(origem);
		gestor.setTipo(TipoLicacao);

		if (TipoLicacao.equals("1")) {
			gestor.setDdr(ddr);
		}
		gestor.setToque(duracaoToque);
		diretorio.CriarArquivo(l);
		dao.save(gestor);
	}

}
