
package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogMessages implements Serializable
{

	/* Atributos */
	private static final long serialVersionUID = 7L;
	LocalDate date;
	LocalTime time;
	String msg;


	/* Constructor vacio */
	public LogMessages()
	{

	}




	/* Constructor parametrizado, solo nos hace falta un string */
	public LogMessages(String msg)
	{
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.msg = msg;
	}




	/* Salida documento */
	public String salidaDocumento()
	{
		return date.toString() + ";" + time.toString() + ";" + msg;
	}




	@Override
	public String toString()
	{
		return "\nFecha: " + date.toString() + "\nHora: " + time.toString() + "\nMensaje: " + msg + "\n";
	}

}
