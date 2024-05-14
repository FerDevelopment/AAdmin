
package main;

import java.io.Serializable;
import java.time.LocalDate;

import com.comun.*;

public class Person implements Serializable
{

	private static final long serialVersionUID = 1L;
	protected String name;
	protected String surname;
	protected String email;
	protected String phone;
	protected LocalDate birth;
	protected String area;
	protected String eSC;
	protected String nickname;

	public Person(String name, String surname, String email, String phone, String birth, String area)
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
			System.out.println("***La fecha no es válida***");
		}

		this.area = area;
	}

	public Person()
	{
		this.name = "";
		this.surname = "";
		this.email = "";
		this.phone = "";
		this.area = "";
	}

	public Person(String nickname, String eSC2)
	{
		this.name = "";
		this.surname = "";
		this.email = "";
		this.phone = "";
		this.area = "";
		this.nickname = nickname;
		this.eSC = eSC2;
	}

	public String getESC()
	{
		return eSC;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public LocalDate getBirth()
	{
		return birth;
	}

	public void setBirth(String birth)
	{

		try
		{
			this.birth = LocalDate.parse(birth);
		} catch (Exception e)
		{
			System.out.println("***La fecha no es válida***");
		}

	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	protected static String encrypt(String intro)
	{
		StringBuilder aux = new StringBuilder();

		for (int i = 0; i < intro.length(); i++)
		{
			char caracter = intro.charAt(i);
			int ascii = (int) caracter;
			ascii = ((ascii - 32 + StaticData.encrypNum) % 95) + 32;
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
			ascii = ((ascii - 32 - StaticData.encrypNum + 95) % 95) + 32;
			resultado.append((char) ascii);
		}

		return resultado.toString();
	}

	public static Person newPerson()
	{
		String name = "";
		String surname = "";
		String email = "";
		String phone = "";
		String birth = "";
		String area = "";
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
		Printer.print("Introduzca su número de teléfono: ");
		phone = getText(phone, phonePattern);
		Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
		birth = getText(birth, birthPattern);
		Printer.print("Introduzca su área de trabajo: ");
		area = getText(area, noNumPattern);

		Printer.print("\nUsted ha completado la información personal.\n");

		return new Person(name, surname, email, phone, birth, area);
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

	public static Person newLoginUser()
	{
		Printer.print("\n\n" + StaticData.BARRA + "Datos de inicio de sesión" + StaticData.BARRA + "\n\n");
		Printer.print("Introduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contraseña: ");
		String dSC = Entrada.cadena();
		String eSC = encrypt(dSC);
		dSC = "";
		return new Person(nickname, eSC);
	}

}
