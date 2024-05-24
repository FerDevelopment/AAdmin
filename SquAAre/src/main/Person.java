
package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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
	ArrayList<String> logMessages= new ArrayList<>();


	public Person(String name, String surname, String email, String phone, String birth, String area)
	{
		this.logMessages=new ArrayList<>();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;


		try
		{
			this.birth = LocalDate.parse(birth);
		}
		catch (Exception e)
		{
			System.out.println("***La fecha no es v�lida***");
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




	public Person(String name, String surname, String email, String phone, LocalDate birth, String area)
	{
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;


		try
		{
			this.birth = birth;
		}
		catch (Exception e)
		{
			System.out.println("***La fecha no es v�lida***");
		}

		this.area = area;
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
		}
		catch (Exception e)
		{
			System.out.println("***La fecha no es v�lida***");
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
		Printer.print("Introduzca su n�mero de tel�fono: ");
		phone = getText(phone, phonePattern);
		Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
		birth = getText(birth, birthPattern);
		Printer.print("Introduzca su �rea de trabajo: ");
		area = getText(area, noNumPattern);

		Printer.print("\nUsted ha completado la informaci�n personal.\n");

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
		Printer.print("\n\n" + StaticData.BARRA + "Datos de inicio de sesi�n" + StaticData.BARRA + "\n\n");
		Printer.print("Introduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrase�a: ");
		String dSC = Entrada.cadena();
		String eSC = encrypt(dSC);
		dSC = "";
		return new Person(nickname, eSC);
	}




	public static Person login(ArrayList<? extends Person> people)
	{


		if (StaticData.maxTry <= 0)
		{
			System.out.println("Ha excedido el numero de intentos");
			StaticData.maxTry = 4;
		}

		Printer.print("Introduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("Introduzca su contrase�a: ");
		String password = Entrada.cadena();
		String eSC = encrypt(password);


		for (int i = 0; i < people.size(); i++)
		{

			Person person = people.get(i);


			if (person.getNickname().equals(nickname) && person.getESC().equals(eSC))
			{
				Printer.print("Inicio de sesi�n exitoso.\n");
				StaticData.maxTry = 4;
				return person;
			}

		}

		Printer.print("Nickname o contrase�a incorrectos.\n\n");
		StaticData.maxTry--;
		return login(people);
	}




	public void setESC(String newValue)
	{
		this.eSC = newValue;

	}




	public void createEmployee(StaticData data)
	{
		Employee newEmployee = Employee.newEmployee();


		if (newEmployee != null)
		{
			data.savedEmployee.add(newEmployee);
			System.out.println("\n*** Nuevo empleado creado exitosamente ***\n");
			data.addLogMessage("El manager " + this.getName() + " " + this.getSurname()
					+ " ha creado al empleado " + newEmployee.getName() + " " + newEmployee.getSurname());
			this.logMessages.add("El manager " + this.getName() + " " + this.getSurname()
					+ " ha creado al empleado " + newEmployee.getName() + " " + newEmployee.getSurname());
		}
		else
		{
			System.out.println("\n*** Error al crear el nuevo empleado ***\n");
		}

	}




	public void viewActivityReport()
	{


		if (logMessages != null)
		{
			System.out.println("Informe de actividad:");


			for (String message : logMessages)
			{
				System.out.println(message);
			}

		}

	}




	public void modifyEmployee(StaticData data, Employee employee)
	{


		if (employee != null)
		{
			String oldValue = "";
			String newValue = "";
			System.out.println(
					"\n�Qu� dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Tel�fono\n->4.Fecha de nacimiento\n->5.�rea de trabajo\n->6.Nickname\n->7.Contrase�a");
			Integer option = Entrada.entero();


			switch (option)
			{
				case 1:
				{
					System.out.print("Nombre: ");

					oldValue = employee.getName();
					employee.setName(newValue);
					data.addLogMessage("Nombre del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nombre del empleado " + oldValue + " cambiado a " + newValue);
					System.out.print("Apellido: ");

					oldValue = employee.getSurname();
					employee.setSurname(newValue);
					data.addLogMessage("Apellido del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Apellido del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}



				case 2:
				{
					System.out.print("Email: ");
					oldValue = employee.getEmail();
					employee.setEmail(newValue);
					data.addLogMessage("Email del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Email del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}



				case 3:
				{
					System.out.print("Tel�fono: ");
					oldValue = employee.getPhone();
					employee.setPhone(newValue);
					data.addLogMessage("Tel�fono del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Tel�fono del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}



				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					oldValue = employee.getBirth().toString();
					employee.setBirth(newValue);
					data.addLogMessage(
							"Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
					break;
				}



				case 5:
				{
					System.out.print("�rea de trabajo: ");
					oldValue = employee.getArea();
					employee.setArea(newValue);
					data.addLogMessage(
							"�rea de trabajo del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("�rea de trabajo del empleado " + oldValue + " cambiada a " + newValue);
					break;
				}



				case 6:
				{
					System.out.print("Nickname: ");
					oldValue = employee.getNickname();
					employee.setNickname(newValue);
					data.addLogMessage("Nickname del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nickname del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}



				case 7:
				{
					oldValue = employee.getESC();
					employee.setESC(newValue);
					data.addLogMessage("Contrase�a del empleado cambiada.");
					this.logMessages.add("Contrase�a del empleado cambiada.");
					break;
				}



				default:
					System.out.println("Opci�n no v�lida");
					break;
			}

			System.out.println("Employee modificado correctamente.");
		}
		else
		{
			System.out.println("Employee no encontrado.");
		}

	}




	public void modifyManager(StaticData data, Manager employee, Person subBoss)
	{


		if (employee != null)
		{
			String oldValue = "";
			String newValue = "";
			System.out.println(
					"\n�Qu� dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Tel�fono\n->4.Fecha de nacimiento\n->5.�rea de trabajo\n->6.Nickname\n->7.Contrase�a");
			Integer option = Entrada.entero();


			switch (option)
			{
				case 1:
				{
					System.out.print("Nombre: ");

					oldValue = employee.getName();
					employee.setName(newValue);
					data.addLogMessage("Nombre del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nombre del empleado " + oldValue + " cambiado a " + newValue);
					System.out.print("Apellido: ");

					oldValue = employee.getSurname();
					employee.setSurname(newValue);
					data.addLogMessage("Apellido del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Apellido del empleado " + oldValue + " cambiado a " + newValue);

					break;
				}



				case 2:
				{
					System.out.print("Email: ");
					oldValue = employee.getEmail();
					employee.setEmail(newValue);
					data.addLogMessage("Email del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Email del empleado " + oldValue + " cambiado a " + newValue);

					break;
				}



				case 3:
				{
					System.out.print("Tel�fono: ");
					oldValue = employee.getPhone();
					employee.setPhone(newValue);
					data.addLogMessage("Tel�fono del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Tel�fono del empleado " + oldValue + " cambiado a " + newValue);

					break;
				}



				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					oldValue = employee.getBirth().toString();
					employee.setBirth(newValue);
					data.addLogMessage(
							"Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);

					break;
				}



				case 5:
				{
					System.out.print("�rea de trabajo: ");
					oldValue = employee.getArea();
					employee.setArea(newValue);
					data.addLogMessage(
							"�rea de trabajo del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("�rea de trabajo del empleado " + oldValue + " cambiada a " + newValue);
					break;
				}



				case 6:
				{
					System.out.print("Nickname: ");
					oldValue = employee.getNickname();
					employee.setNickname(newValue);
					data.addLogMessage("Nickname del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nickname del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}



				case 7:
				{
					oldValue = employee.getESC();
					employee.setESC(newValue);
					data.addLogMessage("Contrase�a del empleado cambiada.");
					break;
				}



				default:
					System.out.println("Opci�n no v�lida");
					break;
			}

			System.out.println("Employee modificado correctamente.");
		}
		else
		{
			System.out.println("Employee no encontrado.");
		}

	}

}
