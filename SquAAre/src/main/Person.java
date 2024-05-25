
package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import com.comun.*;

public class Person implements Serializable, Comparable<Person>
{

	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private LocalDate birth;
	private String area;
	protected String eSC;
	protected String nickname;

	CustomLogMessagesList logMessages = new CustomLogMessagesList();

	public Person(String name, String surname, String email, String phone, String birth, String area)
	{
		this.logMessages = new CustomLogMessagesList();
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

	public Person(String name, String surname, String email, String phone, LocalDate birth, String area)
	{
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;

		try
		{
			this.birth = birth;
		} catch (Exception e)
		{
			System.out.println("***La fecha no es válida***");
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
		} catch (Exception e)
		{
			System.out.println("***La fecha no es válida***\n\nIntroduzcala de nuevo aaaa-mm-dd\n-->");
			birth = Entrada.cadena();
			setBirth(birth);
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
			ascii = ((ascii - 32 + StaticData.ENCRYPNUM) % 95) + 32;
			aux.append((char) ascii);
		}

		return aux.toString();
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

	public static Person login(ArrayList<? extends Person> people)
	{

		if (StaticData.maxTry <= 0)
		{
			System.out.println("Ha excedido el numero de intentos\n");
			StaticData.maxTry = 3;
			return null;
		}

		Printer.print("Introduzca su NickName: ");
		String nickname = Entrada.cadena();

		Person aux = new Person();
		aux.setNickname(nickname);
		int a = Collections.binarySearch(people, aux);

		if (a >= 0)
		{
			aux = people.get(a);
			System.out.println(aux.toString());
			Printer.print("Introduzca su contraseña: ");
			String password = Entrada.cadena();
			String eSC = encrypt(password);

			if (people.get(a).getESC().equals(eSC))
			{
				Printer.print("Inicio de sesión exitoso.\n");
				StaticData.maxTry = 4;
				people.get(a).logMessages.add("Usted inició la sesión");
				return people.get(a);
			}
			else
			{
				System.out.println("Contraseña incorrecta");
			}

		}
		else
		{
			System.out.println("Nombre no econtrado");
		}

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
			data.addLogMessage("El manager " + this.getName() + " " + this.getSurname() + " ha creado al empleado "
					+ newEmployee.getName() + " " + newEmployee.getSurname());
			this.logMessages.add("El manager " + this.getName() + " " + this.getSurname() + " ha creado al empleado "
					+ newEmployee.getName() + " " + newEmployee.getSurname());
		}
		else
		{
			System.out.println("\n*** Error al crear el nuevo empleado ***\n");
		}

	}

	public void viewActivityReport()
	{

		if (this.logMessages != null)
		{
			System.out.println("Informe de actividad:");

			for (LogMessages message : this.logMessages)
			{
				System.out.println(message.toString());
			}

		}
		else
		{
			this.logMessages = new CustomLogMessagesList();
		}

	}

	public void modifyEmployee(StaticData data, Employee employee)
	{

		if (employee != null)
		{
			String oldValue = "";
			String newValue = "";
			System.out.println(
					"\n¿Qué dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Teléfono\n->4.Fecha de nacimiento\n->5.Área de trabajo\n->6.Nickname\n->7.Contraseña");
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
					System.out.print("Teléfono: ");
					oldValue = employee.getPhone();
					employee.setPhone(newValue);
					data.addLogMessage("Teléfono del empleado " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Teléfono del empleado " + oldValue + " cambiado a " + newValue);
					break;
				}

				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					oldValue = employee.getBirth().toString();
					employee.setBirth(newValue);
					data.addLogMessage("Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages.add("Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
					break;
				}

				case 5:
				{
					System.out.print("Área de trabajo: ");
					oldValue = employee.getArea();
					employee.setArea(newValue);
					data.addLogMessage("Área de trabajo del empleado " + oldValue + " cambiada a " + newValue);
					this.logMessages.add("Área de trabajo del empleado " + oldValue + " cambiada a " + newValue);
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
					data.addLogMessage("Contraseña del empleado cambiada.");
					this.logMessages.add("Contraseña del empleado cambiada.");
					break;
				}

				default:
					System.out.println("Opción no válida");
					break;
			}

			System.out.println("Employee modificado correctamente.");
		}
		else
		{
			System.out.println("Employee no encontrado.");
		}

	}

	public void modifyManager(StaticData data, Manager manager, Person subBoss)
	{

		if (manager != null)
		{
			String oldValue = "";
			String newValue = "";
			System.out.println(
					"\n¿Qué dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Teléfono\n->4.Fecha de nacimiento\n->5.Área de trabajo\n->6.Nickname\n->7.Contraseña");
			Integer option = Entrada.entero();

			switch (option)
			{
				case 1:
				{
					System.out.print("Nombre: ");

					oldValue = manager.getName();
					manager.setName(newValue);
					data.addLogMessage("Nombre del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nombre del manager " + oldValue + " cambiado a " + newValue);
					System.out.print("Apellido: ");

					oldValue = manager.getSurname();
					manager.setSurname(newValue);
					data.addLogMessage("Apellido del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Apellido del manager " + oldValue + " cambiado a " + newValue);

					break;
				}

				case 2:
				{
					System.out.print("Email: ");
					oldValue = manager.getEmail();
					manager.setEmail(newValue);
					data.addLogMessage("Email del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Email del manager " + oldValue + " cambiado a " + newValue);

					break;
				}

				case 3:
				{
					System.out.print("Teléfono: ");
					oldValue = manager.getPhone();
					manager.setPhone(newValue);
					data.addLogMessage("Teléfono del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Teléfono del manager " + oldValue + " cambiado a " + newValue);

					break;
				}

				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					oldValue = manager.getBirth().toString();
					manager.setBirth(newValue);
					data.addLogMessage("Fecha de nacimiento del manager " + oldValue + " cambiada a " + newValue);
					this.logMessages.add("Fecha de nacimiento del manager " + oldValue + " cambiada a " + newValue);

					break;
				}

				case 5:
				{
					System.out.print("Área de trabajo: ");
					oldValue = manager.getArea();
					manager.setArea(newValue);
					data.addLogMessage("Área de trabajo del manager " + oldValue + " cambiada a " + newValue);
					this.logMessages.add("Área de trabajo del manager " + oldValue + " cambiada a " + newValue);
					break;
				}

				case 6:
				{
					System.out.print("Nickname: ");
					oldValue = manager.getNickname();
					manager.setNickname(newValue);
					data.addLogMessage("Nickname del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Nickname del manager " + oldValue + " cambiado a " + newValue);
					break;
				}

				case 7:
				{
					oldValue = manager.getESC();
					manager.setESC(newValue);
					data.addLogMessage("Contraseña del manager cambiada.");
					this.logMessages.add("Contraseña del manager cambiada.");
					break;
				}

				default:
					System.out.println("Opción no válida");
					break;
			}

			System.out.println("Manager modificado correctamente.");
		}
		else
		{
			System.out.println("Manager no encontrado.");
		}

	}

	@Override
	public int compareTo(Person otherUser)
	{
		return this.nickname.compareTo(otherUser.nickname);
	}

	public String toString()
	{
		return eSC;
	}
}
