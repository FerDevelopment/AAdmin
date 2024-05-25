package finalBoss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import main.*;

public class InDataToTryPro
{
	private static StaticData data;

	public static void main(String[] args)
	{
		String nombreArchivo = "./import/products/products.txt";
		data = new StaticData();

		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)))
		{
			String linea;

			while ((linea = br.readLine()) != null)
			{
				StringTokenizer help = new StringTokenizer(linea, ",");
				Product aux = new Product();
				aux.setNombre(help.nextToken());
				aux.setPrecio(Double.parseDouble(help.nextToken()));
				aux.setCantidad(Integer.parseInt(help.nextToken()));

				data.savedProducts.add(aux);
			}
			data.end();
			System.out.println("Listou");
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}

}
