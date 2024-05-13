
package main;

import java.io.*;
import java.time.*;
import com.comun.*;

public class Person implements Serializable
{

	private static final long serialVersionUID = 1L;
	private static Integer encrypNum = StaticData.ecn;
	protected String name = null;
	protected String surname = null;
	protected String email = null;
	protected String phone = null;
	protected LocalDate birth = null;
	protected String dSC = "";
	protected String eSC = "";

	public Person(String name, String surname, String email, String phone, String birth)
	{

		this.name = name;

		this.surname = surname;

		this.email = email;

		this.phone = phone;

		try
		{
			this.birth = LocalDate.parse(birth);
		} catch (Exception e)
		{
			Printer.print("\n\n***La fecha se fue al garete***\n\n");
		}

	}

	@Override
	public String toString()
	{
		return "Person [name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", birth="
				+ birth + ", dSC=" + dSC + ", eSC=" + eSC + "]";
	}

	public Person()
	{
		name = "";
		surname = "";
		email = "";
		phone = "";
		birth = LocalDate.parse("2004-10-27");

	}

	public void singIn()
	{

	}

	public static Person newPerson()
	{
		String name = "";
		String surname = "";
		String email = "";
		String phone = "";
		String birth = "";

		String emailPattern = ".*@.*[.][A-Za-z]{1,4}";
		String noNumPattern = "^[\\D]+$";
		String phonePattern = "^[0-9]{9}$";
		String birthPattern = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Printer.print("\n\n" + StaticData.BARRA + "Datos personales" + StaticData.BARRA + "\n\n");
		Printer.print("Introduzca su nombre: ");
		name = getText(name, noNumPattern);
		Printer.print("Introduzca sus apellidos: ");
		surname = getText(surname, noNumPattern);
		Printer.print("Introduzca su email: ");
		email = getText(email, emailPattern);
		Printer.print("Introduzca su numero de telefono: ");
		phone = getText(phone, phonePattern);
		Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
		birth = getText(birth, birthPattern);

		Printer.print("\nUsted a completado la informacion personal");
		return new Person(name, surname, email, phone, birth);
	}

	protected static String encrypt(String intro)
	{
		StringBuilder aux = new StringBuilder();

		for (int i = 0; i < intro.length(); i++)
		{
			char caracter = intro.charAt(i);
			int ascii = (int) caracter;
			ascii = ((ascii - 32 + encrypNum) % 95) + 32;

			aux.append((char) ascii);
		}

		return aux.toString();
	}

	protected static String decrypt(String intro)
	{
		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < intro.length(); i++)
		{
			char caracter = intro.charAt(i);
			int ascii = (int) caracter;
			ascii = ((ascii - 32 - encrypNum + 95) % 95) + 32;

			resultado.append((char) ascii);
		}

		return resultado.toString();
	}

	protected static String getText(String text, String pattern)
	{

		do
		{
			text = Entrada.cadena();

			if (!text.matches(pattern))
			{
				Printer.print("\n****Formato Incorrecto****\n");
			}

		} while (!text.matches(pattern));

		return text;
	}

}
