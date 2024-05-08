
package comun.ejemplo;

import java.io.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.*;

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
		
		
		
	}

}
