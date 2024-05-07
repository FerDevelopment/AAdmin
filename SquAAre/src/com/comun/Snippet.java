
package com.comun;

import java.text.*;
import java.util.*;

public class Snippet
{

	public static Date ParseFecha(String fecha)
	{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;


		try
		{
			fechaDate = formato.parse(fecha);
		}
		catch (ParseException ex)
		{
			System.out.println(ex);
		}

		return fechaDate;
	}




	public static void main(String[] args)
	{
		Date fecha = ParseFecha("23/07/1983");
		System.out.println("La fecha es :" + fecha.toString());
		SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss a z");
		System.out.println("La fecha actual en un formato dado: " + dateformatter.format(fecha.getTime()));
	}
	
	
}
