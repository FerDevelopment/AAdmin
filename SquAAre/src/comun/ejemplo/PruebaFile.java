
package comun.ejemplo;

import java.io.*;


public class PruebaFile
{

	public static void main(String[] args)
	{
		String ruta = "./import/music/";
		File directorio = new File(ruta);
		File[] canciones= directorio.listFiles();
		String[] lista = directorio.list();


		for (int i = 0; i < lista.length; i++)
		{
			System.out.println(lista[i]);
		}
		for (int i = 0; i < canciones.length; i++)
		{
			System.out.println(canciones[i].getName());
		}
		
		
		
		
	}

}
