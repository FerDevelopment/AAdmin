
package com.comun;

import java.util.Random;

public class Pruebas
{

	static Integer max = 10;


	public static void main(String[] args)
	{

		Integer[][] matrix = new Integer[max][max];
		Random random = new Random();


		for (int i = 0; i < max; i++)
		{


			for (int j = 0; j < max; j++)
			{
				matrix[i][j] = random.nextInt(11);
			}

			Printer.print("\n");

		}
		
		

		Printer.print(matrix);

	}

}
