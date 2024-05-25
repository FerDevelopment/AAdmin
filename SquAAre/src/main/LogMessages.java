package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogMessages implements Serializable
{
	private static final long serialVersionUID = 7L;
	LocalDate date;
	LocalTime time;
	String msg;

	public LogMessages()
	{

	}

	public LogMessages(String msg)
	{
		date = LocalDate.now();
		time = LocalTime.now();
		this.msg = msg;
	}

	public String salidaDocumento()
	{
		return date.toString() + ";" + time.toString() + ";" + msg;
	}

	public String toString()
	{
		return "\nFecha: " + date.toString() + "\nHora: " + time.toString() + "\nMensaje: " + msg + "\n";
	}
}
