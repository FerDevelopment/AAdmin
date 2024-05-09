
package main;

import java.time.*;
import com.comun.*;

public class Person
{

	protected String name = null;
	protected String surname = null;
	protected String email = null;
	protected String phone = null;
	protected LocalDate birth = null;


	public Person(
			String name,
			String surname,
			String email,
			String phone,
			String birth)
	{
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.birth = LocalDate.parse(birth);
	}




	public Person()
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
		return new Person(name, surname, email, phone, birth);
	}




	private static String getText(
			String text,
			String pattern)
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




	public static void main(
			String[] args)
	{
		newPerson();
	}

}
