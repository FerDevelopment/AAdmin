package main;

import java.util.ArrayList;

public class CustomLogMessagesList extends ArrayList<LogMessages>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	public boolean add(String message)
	{
		LogMessages logMessage = new LogMessages(message);
		// L�gica personalizada antes de a�adir el mensaje de log

		// A�adir el mensaje de log a la lista
		boolean result = super.add(logMessage);

		// Devolver el resultado de la operaci�n de a�adir
		return result;
	}
}
