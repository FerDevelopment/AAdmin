package main;

import java.util.ArrayList;

/**
 * Clase que representa una lista personalizada de mensajes de registro.
 */
public class CustomLogMessagesList extends ArrayList<LogMessages> {

    /** SerialVersionUID para la serializaci�n. */
    private static final long serialVersionUID = 7L;

    /**
     * M�todo para a�adir un mensaje a la lista personalizada de mensajes de registro.
     * 
     * @param message Mensaje a a�adir.
     * @return true si se a�ade correctamente, false en caso contrario.
     */
    public boolean add(String message) {
        /* Se crea un objeto LogMessages con el mensaje proporcionado */
        LogMessages logMessage = new LogMessages(message);
        /* L�gica personalizada antes de a�adir el mensaje de log */

        /* A�adir el mensaje de log a la lista */
        boolean result = super.add(logMessage);

        /* Devolver el resultado de la operaci�n de a�adir */
        return result;
    }
}
