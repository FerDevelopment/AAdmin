
package finalBoss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.*;

public class InDataToTry
{

	private static StaticData data;


	public static void main(String[] args)
	{
		String nombreArchivo = "./import/names/names.txt";
		data = new StaticData();


		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)))
		{
			String linea;


			while ((linea = br.readLine()) != null)
			{

				
				data.savedEmployee.add(niu(linea));

			}

			data.end();
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}
		
		System.out.println("Proceso de nombres terminado");
		InDataToTryPro.main(args);
	}




	public static Employee niu(String name)
	{
		return new Employee(name);
	}

}
