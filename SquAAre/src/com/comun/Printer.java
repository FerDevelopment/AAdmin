
package com.comun;

import java.util.ArrayList;

public class Printer
{

	/**
	 * Method to print an ArrayList with a simple form
	 * 
	 * @param <T>, type of ArrayList, 'T' means anyone class
	 * @param var, ArrayList
	 */
	public static <T> void print(ArrayList<?> var)
	{


		for (int i = 0; i < var.size(); i++)
		{
			System.out.println(var.get(i).toString());
		}

	}




	/**
	 * method to print anything printable
	 * 
	 * @param var, objeto a print
	 */
	public static void print(Object var)
	{
		System.out.print(var.toString());

	}




	/**
	 * This method print an Array separate by | plus one space
	 * 
	 * @param array, array to print
	 */
	public static void print(Object[] array)
	{

		System.out.print("| ");


		for (int i = 0; i < array.length; i++)
		{
			System.out.print(String.valueOf(array[i]) + " | ");
		}

	}




	/**
	 * This method print a Matrix in a normal form
	 * 
	 * @param matriz, matrix to Print
	 */
	public static void print(Object[][] matriz)
	{

		String[][] matriz2 = new String[matriz.length][matriz[0].length];
		String barra = "";
		copyMatriz(matriz, matriz2);
		int acuY = 0;


		for (int i = 0; i < matriz2.length; i++)
		{

			acuY = cantColum(matriz2, i);


			for (int j = 0; j < matriz2[0].length; j++)
			{

				ponerEspacios(acuY, matriz2[j]);
			}

		}

		barra = barra(matriz2[0]);
		System.out.println(barra);


		for (int i = 0; i < matriz2.length; i++)
		{


			for (int j = 0; j < matriz2[0].length; j++)
			{

				System.out.print(" " + matriz2[i][j] + "|");

			}

			System.out.println("\n" + barra);

		}

	}




	/**
	 * This method is special. This method print a Matrix, rows and
	 * columns symmetrically
	 * 
	 * @param matriz,   matrix data
	 * @param filas,    rows indentifier
	 * @param columnas, columns parts
	 */
	public static void print(Object[][] matriz, Object[] filas, Object[] columnas)
	{

		String[] columnas2 = new String[columnas.length];
		String[] filas2 = new String[filas.length];
		String[][] matriz2 = new String[matriz.length][matriz[0].length];
		int acumulador = 0;
		String barra = "";
		String espacioPrimero = "";

		/*
		 * Este acumulador nos copia la fila1 en la fila2 y nos dice el maximo
		 * de la fila para controlar los espacios iniciales
		 */
		acumulador = cumuloCopy(filas, filas2);

		/*
		 * Le pone los espacios adicionales a las filas(solo las "materias")
		 */
		ponerEspacios(acumulador, filas2);


		/*
		 * A partir del acumuador tambine obtenemos el espacio adicional que
		 * habra al inicio a modo de la primera posicion de las columnas
		 */
		for (int i = 0; i < acumulador + 1; i++)
		{
			espacioPrimero += " ";
		}

		acumulador = cumuloCopy(columnas, columnas2);

		/* Copiamos la matriz con su ToString correspondiente */
		copyMatriz(matriz, matriz2);

		/*------------------------------------------------------*/

		/* Iniciamos la funcion para poner los espacios */
		putSpace(columnas2, matriz2);

		/*-----------------------------------------IMPRMIR---------------------------------------------------------*/
		/*
		 * Este método nos calcula la barra que necesitaremos para darle la
		 * forma de tabla
		 */
		barra = barra(matriz2[0], filas2);

		System.out.print(barra + "\n" + espacioPrimero + "|");


		/* Imprimimos todas las columnas para luego comenzar con la matriz */
		for (int i = 0; i < columnas2.length; i++)
		{
			System.out.print(" " + columnas2[i] + "|");
		}

		System.out.println("\n" + barra);


		/* Aqui comenzamos con la matriz */
		for (int i = 0; i < matriz2.length; i++)
		{

			System.out.print(filas2[i] + " |");


			for (int j = 0; j < matriz2[0].length; j++)
			{
				System.out.print(" " + matriz2[i][j] + "|");
			}

			System.out.println("\n" + barra);
		}

		/*-------------------------------------------------------------------------------------------------------------------------------------------*/

	}




	private static int acumuladorColumnas(String[][] matriz, int columna)
	{
		int acuY = 0;


		for (int i = 0; i < matriz.length; i++)
		{


			if (acuY < matriz[i][columna].length())
			{
				acuY = matriz[i][columna].length();
			}

		}

		return acuY;
	}




	private static String barra(String[] columnas2)
	{
		String barra = "";


		for (int i = 0; i < columnas2.length; i++)
		{
			barra += "-";


			for (int j = 0; j < columnas2[i].length() + 1; j++)
			{
				barra += "-";
			}

		}

		return barra;
	}




	/**
	 * This method calculate the necesary quantity for bars to implems a
	 * table arquitecture
	 * 
	 * @param columnas2
	 * @param filas2
	 * @return
	 */

	private static String barra(String[] columnas2, String[] filas2)
	{

		String barra = "";

		barra = barra(columnas2);


		for (int j = 0; j < filas2[0].length() + 1; j++)
		{
			barra += "-";
		}

		barra += "-";
		return barra;
	}




	private static int cantColum(String[][] matriz, int colum)
	{
		int max = 0;


		for (int i = 0; i < matriz.length; i++)
		{


			if (max < matriz[i][colum].length())
			{
				max = matriz[i][colum].length();
			}

		}

		return max;
	}




	private static void copyMatriz(Object[][] matriz, String[][] matriz2)
	{


		for (int i = 0; i < matriz.length; i++)
		{


			for (int j = 0; j < matriz[0].length; j++)
			{
				matriz2[i][j] = matriz[i][j].toString();
			}

		}

	}




	private static int cumuloCopy(Object[] filas, String[] filas2)
	{
		int aux = 0;
		int acumulador = 0;


		for (int i = 0; i < filas.length; i++, aux++)
		{
			filas2[aux] = filas[i].toString();


			if (acumulador < filas2[aux].length())
			{
				acumulador = filas2[aux].length();
			}

		}

		return acumulador;
	}




	private static void ponerEspacios(int acumulador, String[] filas2)
	{


		for (int i = 0; i < filas2.length; i++)
		{


			if (filas2[i].length() < acumulador)
			{
				String espacios = "";


				for (int j = 0; j < (acumulador - filas2[i].length()); j++)
				{
					espacios += " ";
				}

				filas2[i] = filas2[i] + espacios;
			}

		}

	}




	private static void putSpace(String[] columnas2, String[][] matriz2)
	{
		int acuY;


		for (int i = 0; i < matriz2.length; i++)
		{
			String espacios = "";


			for (int j = 0; j < matriz2[0].length; j++)
			{
				/* Buscamos la columna mas grande */
				acuY = acumuladorColumnas(matriz2, j);

				int espaciosAux = acuY;


				/*
				 * Nos aseguramos qué es más grande, si la columna con el campo o un
				 * valor
				 */
				if (columnas2[j].length() > acuY)
				{
					espaciosAux = columnas2[j].length();
				}

				else
				{
					/*
					 * En caso de ser más grande un dato se añade los espaciós al nombre
					 */
					int poner2 = espaciosAux - columnas2[j].length();
					espacios = "";


					for (int k = 0; k < poner2 / 2; k++)
					{
						espacios += " ";
					}

					columnas2[j] = espacios + columnas2[j] + espacios;


					if (poner2 % 2 != 0)
					{
						columnas2[j] += " ";
					}

				}

				/* Estos son los espacios que debemos poner */
				int poner = espaciosAux - matriz2[i][j].length();

				espacios = "";


				for (int k = 0; k < poner / 2; k++)
				{
					espacios += " ";
				}

				matriz2[i][j] = espacios + matriz2[i][j] + espacios;


				/*
				 * Si el numero es impar tendremos que añadir un espacio demás ya que
				 * al dividir entre 2 perdemos un espacio
				 */
				if (poner % 2 != 0)
				{
					matriz2[i][j] += " ";
				}

			}

		}

	}

}
