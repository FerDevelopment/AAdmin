package main;

import java.util.ArrayList;

/**
 * Clase que representa una lista personalizada de mensajes de registro.
 */
public class CustomLogMessagesList extends ArrayList<LogMessages> {

    /** SerialVersionUID para la serialización. */
    private static final long serialVersionUID = 7L;

    /**
     * Método para añadir un mensaje a la lista personalizada de mensajes de registro.
     * 
     * @param message Mensaje a añadir.
     * @return true si se añade correctamente, false en caso contrario.
     */
    public boolean add(String message) {
        /* Se crea un objeto LogMessages con el mensaje proporcionado */
        LogMessages logMessage = new LogMessages(message);
        /* Lógica personalizada antes de añadir el mensaje de log */

        /* Añadir el mensaje de log a la lista */
        boolean result = super.add(logMessage);

        /* Devolver el resultado de la operación de añadir */
        return result;
    }
}
