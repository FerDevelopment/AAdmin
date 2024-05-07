
package com.comun;

import java.util.Random;

public class prueba
{

	public static int max = 58885558;


	public static void main(String[] args)
	{
		/* Indicamos los maximos de las de las matrices */
		int cantNombres = 10;
		int cantMaterias = 7;

		/* Matrices y arrrays para los datos */
		String[] materias = new String[cantMaterias];
		String[] nombres = new String[cantNombres];

		Integer[][] notas = new Integer[cantMaterias][cantNombres];

		double promedioG = 0;
		/* Variables auxiliares */
		Random random = new Random();
		/*
		 * Rellenamos los nombres y las materias recordando que el split
		 * devuelve un array
		 */
		nombres = "J Luiaaaaaaaaaaaaaaaaaaaaaaaso Pedro María Javi Lucas Iván Ana Pepe Judith".split(" ");
		materias = "PRO SIS ING LM FOL ED BD".split(" ");

		/*
		 * Invocamos el metodo rellenar notas el cual le da valor a las
		 * matrices de las notas y el promedio
		 */
		promedioG = rellenarNota(cantNombres, cantMaterias, notas, promedioG, random);
		promedioG = promedioG / (cantNombres * cantMaterias);
		/* Invocamos el metodo de la salida bonita */

		Printer.print(notas, materias, nombres);

	}




	public static double rellenarNota(Integer cantNombres, Integer cantMaterias, Integer[][] notas,
			Double promedioG, Random random)
	{


		/*
		 * Rellenamos la matriz con numeros random y vamos rellenando el
		 * promedio
		 */
		for (int i = 0; i < cantMaterias; i++)
		{


			for (int j = 0; j < cantNombres; j++)
			{
				notas[i][j] = random.nextInt(max - 4) + 4;
				promedioG += notas[i][j];
			}

		}

		return promedioG;
	}




	public static void salidaNotasAlu(Integer cantNombres, Integer cantMaterias, String[] materias,
			String[] nombres, Integer[][] notas)
	{
		/* Primero imprimimos los nombres con un espacio en blanco */
		System.out.print("        ");


		for (int i = 0; i < cantNombres; i++)
		{
			System.out.print(nombres[i] + " ||");
		}

		System.out.println();


		/*
		 * Imprimos las materias junto con las notas de cada alumno simulando
		 * una interfaz grafica
		 */
		for (int i = 0; i < cantMaterias; i++)
		{


			if (materias[i].length() < 3)
			{
				System.out.print(materias[i] + "     ");
			}
			else
			{
				System.out.print(materias[i] + "    ");
			}


			for (int j = 0; j < cantNombres; j++)
			{


				if (j % 3 == 0 && j != 0)
				{
					System.out.print(" ");
				}

				System.out.print("   " + notas[i][j] + "   ");
			}

			System.out.println();
		}

	}

}
