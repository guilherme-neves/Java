package com.br.gestor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Diretorio {

	public File diretorio = null;
	public int i;

	public void CriarArquivo(String linha) throws IOException {
		Date hoje = new Date();
		Calendar c = Calendar.getInstance();
		int A = c.get(2);
		if (this.i != A) {
			CriarDiretorio();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		File f = new File(this.diretorio.getPath(), sdf.format(hoje) + ".txt");
		FileWriter fw = new FileWriter(f, true);
		BufferedWriter bw = new BufferedWriter(fw);
		if (!f.exists()) {
			f.createNewFile();
		}
		bw.write(linha);
		bw.newLine();
		bw.flush();
		bw.close();
		fw.close();
	}

	public void CriarDiretorio() {
		Date hoje = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(hoje);
		this.i = c.get(2);
		switch (c.get(2)) {
		case 0:
			this.diretorio = new File("C:\\Bilhetes\\01" + c.getWeekYear());
			break;
		case 1:
			this.diretorio = new File("C:\\Bilhetes\\02" + c.getWeekYear());
			break;
		case 2:
			this.diretorio = new File("C:\\Bilhetes\\03" + c.getWeekYear());
			break;
		case 3:
			this.diretorio = new File("C:\\Bilhetes\\04" + c.getWeekYear());
			break;
		case 4:
			this.diretorio = new File("C:\\Bilhetes\\05" + c.getWeekYear());
			break;
		case 5:
			this.diretorio = new File("C:\\Bilhetes\\06" + c.getWeekYear());
			break;
		case 6:
			this.diretorio = new File("C:\\Bilhetes\\07" + c.getWeekYear());
			break;
		case 7:
			this.diretorio = new File("C:\\Bilhetes\\08" + c.getWeekYear());
			break;
		case 8:
			this.diretorio = new File("C:\\Bilhetes\\09" + c.getWeekYear());
			break;
		case 9:
			this.diretorio = new File("C:\\Bilhetes\\10" + c.getWeekYear());
			break;
		case 10:
			this.diretorio = new File("C:\\Bilhetes\\11" + c.getWeekYear());
			break;
		case 11:
			this.diretorio = new File("C:\\Bilhetes\\12" + c.getWeekYear());
		}
		if (!this.diretorio.exists()) {
			this.diretorio.mkdir();
			System.out.println("Diretorio Criado " + this.diretorio.getPath());
		}
	}

}
