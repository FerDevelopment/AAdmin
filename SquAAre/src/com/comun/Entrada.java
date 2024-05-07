
package com.comun;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Método proporcionado por el profesor para leer de teclado los
 * valores que asignemos a las variables
 */

public class Entrada
{

	static String inicializar()
	{
		String buzon = "";
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);


		try
		{
			buzon = teclado.readLine();
		}
		catch (Exception e)
		{
			System.out.append("Entrada incorrecta)");
		}

		return buzon;
	}




	public static int entero()
	{
		int valor = 1;


		try
		{
			valor = Integer.parseInt(inicializar());
		}
		catch (Exception e)
		{
			System.out.println("\n\n******** No es un entero *******\n");
			valor = entero();
		}

		return valor;
	}




	public static double real()
	{
		double valor = 1;


		try
		{
			valor = Double.parseDouble(inicializar());
		}
		catch (Exception e)
		{
			System.out.println("\n\n******** No es un real(double) *******\n");
			valor = real();
		}

		return valor;
	}




	public static String cadena()
	{
		String valor = inicializar();
		return valor;
	}




	public static char caracter()
	{
		String valor = inicializar();


		if (valor.length() > 1)
		{
			System.out.println("\n\n******** No es un caracter *******\n");
			valor = String.valueOf(caracter());
		}

		return valor.charAt(0);
	}

}
