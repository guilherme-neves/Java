package com.br.gestor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TrabalharData {

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
