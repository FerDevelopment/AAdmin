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
		// Lógica personalizada antes de añadir el mensaje de log

		// Añadir el mensaje de log a la lista
		boolean result = super.add(logMessage);

		// Devolver el resultado de la operación de añadir
		return result;
	}
}
