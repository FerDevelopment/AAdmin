
package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import com.comun.*;

public class Person implements Serializable, Comparable<Person>
{

	/* Atributos */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private LocalDate birth;
	private String area;
	protected String eSC;
	protected String nickname;
	/* ArrayList Personalizado */
	CustomLogMessagesList logMessages = new CustomLogMessagesList();


	/* Constructor por defecto */
	public Person()
	{
		this.name = "";
		this.surname = "";
		this.email = "";
		this.phone = "";
		this.area = "";
	}




	/* Constructor parametrizado */
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
		}
		catch (Exception e)
		{
			System.out.println("***La fecha no es v�lida***");
		}

		this.area = area;
	}




	/* Constructor parametrizado con fecha directa */
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




	/* Getters and setters */
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




	/* Para que la fecha no se vaya de formato */
	public void setBirth(String birth)
	{


		try
		{
			this.birth = LocalDate.parse(birth);
		}
		catch (Exception e)
		{
			System.out.println("***La fecha no es v�lida***\n\nIntroduzcala de nuevo aaaa-mm-dd\n-->");
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




	public void setESC(String newValue)
	{
		this.eSC = newValue;

	}




	/**
	 * Metodo utilizado para encriptar contrasenyas. Siempre se encripta,
	 * nunca al contrario
	 * 
	 * @param intro, texto a encriptar @return, texto encriptado
	 */
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




	/**
	 * Metodo para crear una nueva persona @return, new Person
	 */
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
		birth = birth(birthPattern);
		Printer.print("Introduzca su �rea de trabajo: ");
		area = getText(area, noNumPattern);

		Printer.print("\nUsted ha completado la informaci�n personal.\n");

		return new Person(name, surname, email, phone, birth, area);
	}




	/**
	 * Este metodo comprueba de manera interna que una fecha est� bien
	 * puesta
	 * 
	 * @param birthPattern, birthPattern, es el patron que utilizaremos
	 *                      para validar la fecha @return, un string bien
	 *                      formateado y la fecha es totalmente valida
	 */
	protected static String birth(String birthPattern)
	{
		String birth = "";
		Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
		birth = getText(birth, birthPattern);


		try
		{
			LocalDate.parse(birth);
		}
		catch (Exception e)
		{
			System.out.println("*** Fecha mal ***");
			birth = birth(birthPattern);
		}

		return birth;
	}




	/**
	 * Este metodo comprueba el formato de una cadena
	 * 
	 * @param text,    la cadena a comprobar
	 * @param pattern, el patron a utilizar
	 * @return
	 */
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




	/**
	 * Este metodo es exclusivamente para hacer el login de cualquier hijo
	 * de people, en este caso los boss, employee, manager
	 * 
	 * @param people, es una lista con los datos de cada usuario
	 * @return
	 */
	public static Person login(ArrayList<? extends Person> people)
	{


		/* Comprobamos el numero de intentos disponibles */
		if (StaticData.maxTry <= 0)
		{
			System.out.println("Ha excedido el numero de intentos\n");
			StaticData.maxTry = 3;
			return null;
		}

		/* Introducimos el nickname en una variable auxiliar */
		Printer.print("Introduzca su NickName: ");
		String nickname = Entrada.cadena();

		Person aux = new Person();
		aux.setNickname(nickname);
		/* Invocamos el metodo de busqueda */
		Integer a = Collections.binarySearch(people, aux);


		/* En caso de que encuentre una posici�n se hace el login */
		if (a >= 0)
		{
			aux = people.get(a);
			System.out.println(aux.toString());

			/* Se introduce la contrasenya */
			Printer.print("Introduzca su contrase�a: ");
			String password = Entrada.cadena();
			String eSC = encrypt(password);


			/*
			 * Comprobamso la contrasenya y, en caso de estar mal, lo devolvemos
			 * al inicio
			 */
			if (people.get(a).getESC().equals(eSC))
			{
				Printer.print("Inicio de sesi�n exitoso.\n");
				StaticData.maxTry = 4;
				people.get(a).logMessages.add("Usted inici� la sesi�n");
				return people.get(a);
			}
			else
			{
				System.out.println("Contrase�a incorrecta");
			}

		}
		else
		{
			System.out.println("Nombre no econtrado");
		}

		StaticData.maxTry--;
		return login(people);
	}




	/**
	 * Metodo para crear un nuevo empleado, se utiliza en la clase person
	 * para facilitar el uso gracias a la herencia
	 * 
	 * @param data, es una instancia de la clase data, donde se guarda
	 *              casi toda la informacion como tal
	 */
	public void createEmployee(StaticData data)
	{
		/* Se intenta crear el empleado */
		Employee newEmployee = Employee.newEmployee();


		/* En caso de poderse crear se agrega despues los mensajes de log */
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




	/**
	 * Metodo para visualizar el informe de actividad
	 */
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




	/**
	 * Metodo para modificar empleados atributo por atributo
	 * 
	 * @param data,     instancia de data para guardar las informaciones
	 *                  pertinentes
	 * @param employee, empleado a modificar
	 */
	public void modifyEmployee(StaticData data, Employee employee)
	{


		/* Confirmamos que nos est� vac�o el empleado */
		if (employee != null)
		{
			/* Variable auxiliar para los cambios */
			String oldValue = "";
			String newValue = "";
			System.out.println(
					"\n�Qu� dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Tel�fono\n->4.Fecha de nacimiento\n->5.�rea de trabajo\n->6.Nickname\n->7.Contrase�a");
			Integer option = Entrada.entero();


			/* Se pregunta el valor nuevo, se cambiay se agrega el log */
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




	/**
	 * Metodo igual que el anterior, pero esta vez para modificar un
	 * manager
	 * 
	 * @param data,    instancia de data para guardar las informaciones
	 *                 pertinentes
	 * @param manager, manager a modificar
	 */
	public void modifyManager(StaticData data, Manager manager)
	{


		if (manager != null)
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
					System.out.print("Tel�fono: ");
					oldValue = manager.getPhone();
					manager.setPhone(newValue);
					data.addLogMessage("Tel�fono del manager " + oldValue + " cambiado a " + newValue);
					this.logMessages.add("Tel�fono del manager " + oldValue + " cambiado a " + newValue);

					break;
				}



				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					oldValue = manager.getBirth().toString();
					manager.setBirth(newValue);
					data.addLogMessage(
							"Fecha de nacimiento del manager " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("Fecha de nacimiento del manager " + oldValue + " cambiada a " + newValue);

					break;
				}



				case 5:
				{
					System.out.print("�rea de trabajo: ");
					oldValue = manager.getArea();
					manager.setArea(newValue);
					data.addLogMessage("�rea de trabajo del manager " + oldValue + " cambiada a " + newValue);
					this.logMessages
							.add("�rea de trabajo del manager " + oldValue + " cambiada a " + newValue);
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
					data.addLogMessage("Contrase�a del manager cambiada.");
					this.logMessages.add("Contrase�a del manager cambiada.");
					break;
				}



				default:
					System.out.println("Opci�n no v�lida");
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




	@Override
	public String toString()
	{
		return eSC;
	}

}
