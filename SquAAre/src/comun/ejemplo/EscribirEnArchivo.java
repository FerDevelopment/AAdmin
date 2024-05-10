
package comun.ejemplo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class EscribirEnArchivo
{

	public static void main(
			String[] args)
	{
		// Nombre del archivo
		String nombreArchivo = "archivo.txt";


		// Escribir en el archivo
		try
		{
			// Crear un BufferedWriter
			BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));

			// Leer la entrada del usuario
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Escribe algo para guardar en el archivo (Escribe 'fin' para terminar):");
			String entrada = reader.readLine();


			// Mientras la entrada no sea "fin", escribir en el archivo
			while (!entrada.equalsIgnoreCase("fin"))
			{
				writer.write(entrada);
				writer.newLine();
				entrada = reader.readLine();
			}

			// Cerrar el BufferedWriter
			writer.close();
			System.out.println("Se ha escrito en el archivo correctamente.");
		}
		catch (IOException e)
		{
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}


		// Leer desde el archivo
		try
		{
			// Crear un BufferedReader
			BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));

			// Leer línea por línea y mostrar en la consola
			String linea;
			System.out.println("Contenido del archivo:");


			while ((linea = reader.readLine()) != null)
			{
				System.out.println(linea);
			}

			// Cerrar el BufferedReader
			reader.close();
		}
		catch (IOException e)
		{
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

	}

}
