
package main;

import java.util.ArrayList;
import java.util.Collections;

import com.comun.*;

public class Main
{

	private static StaticData data;
	private static OutTextRun text;
	private static Boss actualBoss;
	private static Manager actualManager;
	private static Employee actualEmploy;


	public static void main(String[] args)
	{
		data = new StaticData();
		first();
		end();
	}




	private static void first()
	{
		System.out.println(StaticData.BARRA + " �Qu� tipo de usuario eres? " + StaticData.BARRA);
		System.out.println("1. Boss");
		System.out.println("2. Manager");
		System.out.print("3. Employee\n-->");
		int userType = Entrada.entero();


		if (userType == 1234)
		{
			createBoss();
		}
		else
		{


			switch (userType)
			{
				case 1:
					startServer("boss");
					startBossSession();
					break;



				case 2:
					startServer("manager");
					startManagerSession();
					break;



				case 3:
					startServer("employee");
					startEmployeeSession();
					break;



				default:
					System.out.println("***Opci�n no v�lida***");
					first();
					break;
			}

		}

	}




	private static void startServer(String option)
	{
		text = new OutTextRun();
		Thread hilo1 = new Thread(text);
		StaticData.cls();

		hilo1.start();

		Thread hilo2 = new Thread(new Runnable()
		{

			public void run()
			{
				StaticData.cls();


				if (option.equals("boss"))
				{
					data.getBoss();
					data.getManagers();
					data.getEmployee();
				}
				else if (option.equals("manager"))
				{
					data.getManagers();
					data.getEmployee();
				}
				else if (option.equals("employee"))
				{
					data.getEmployee();
					data.getProducts();
				}

			}

		});

		hilo2.start();


		try
		{
			hilo2.join();
		}
		catch (Exception e)
		{
			System.out.println("XD malito");
		}

		text.stop();


		try
		{
			hilo1.join();
			StaticData.cls();
			data.pause(2);

		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("\nSevidor iniciado");


		try
		{
			data.pause(2);
		}
		catch (Exception e)
		{
			System.out.println("Pasusa malita");
		}

		StaticData.cls();
	}




	private static void createBoss()
	{
		System.out.println("Introduce los datos del nuevo Boss:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Tel�fono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("�rea de trabajo: ");
		String area = Entrada.cadena();
		System.out.print("Nickname: ");
		String nickname = Entrada.cadena();
		System.out.print("Contrase�a: ");
		String password = Entrada.cadena();

		Boss newBoss = new Boss();
		newBoss.setName(name);
		newBoss.setSurname(surname);
		newBoss.setEmail(email);
		newBoss.setPhone(phone);
		newBoss.setBirth(birth);
		newBoss.setArea(area);
		newBoss.setNickname(nickname);
		String eSC = Person.encrypt(password);
		newBoss.eSC = eSC;

		data.savedBoss.add(newBoss);
		System.out.println("\n*** Nuevo Boss creado exitosamente ***\n");

		first(); // Volver al men� inicial despu�s de crear el Boss
	}




	private static void startBossSession()
	{
		actualBoss = (Boss) Person.login(data.savedBoss);
		int option;


		do
		{
			System.out.println(StaticData.BARRA + " Boss " + StaticData.BARRA);
			System.out.println("\n�Qu� deseas hacer?");
			System.out.println("1. Crear Manager");
			System.out.println("2. Crear Employee");
			System.out.println("3. Modificar Manager");
			System.out.println("4. Modificar Employee");
			System.out.println("5. Ver informe de actividad");
			System.out.println("6. Salir");
			option = Entrada.entero();


			switch (option)
			{
				case 1:
					actualBoss.createManager(data);
					break;



				case 2:
					actualBoss.createEmployee(data);
					break;



				case 3:
					modifyManager();
					break;



				case 4:
					modifyEmployee();
					break;



				case 5:
					viewActivityReport();
					break;



				case 6:
					end();
					first();
					break;



				default:
					System.out.println("Opci�n no v�lida");
					break;
			}

		} while (option != 6);

	}




	private static void end()
	{
		data.end();
	}




	private static void modifyManager()
	{
		System.out.println("Introduce el nombre del Manager que deseas modificar:");
		String name = Entrada.cadena();
		System.out.println("Introduce el apellido del Manager que deseas modificar:");
		String surname1 = Entrada.cadena();
		Manager manager = (Manager) getPersonByName(data.savedManager, name, surname1);


		if (manager != null)
		{
			System.out.println(
					"\nQu� dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Telefono\n->4.Fecha de nacimiento\n->5.Area de trabajao\n->6.Nickname\n->7.Contrase�a");
			Integer option = Entrada.entero();


			switch (option)
			{

				case 1:
				{
					System.out.print("Nombre: ");
					String newName = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 0,newName);
					System.out.print("Apellido: ");
					String surname = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 1,surname);
				}



				case 2:
				{
					System.out.print("Email: ");
					String email = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 2,email);
				}



				case 3:
				{
					System.out.print("Tel�fono: ");
					String phone = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 3,phone);
				}
				case 4:
				{
					System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
					String birth = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 4,birth);
				}



				case 5:
				{
					System.out.print("�rea de trabajo: ");
					String area = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 5,area);
				}



				case 6:
				{
					System.out.println("Nickname: ");
					String nickname = Entrada.cadena();
					actualBoss.modifyManager(data, manager, 3,nickname);
				}



				case 7:
				{
					System.out.println("Contrasenya: ");
					String contrasenya = Entrada.cadena();
					contrasenya = Person.encrypt(contrasenya);
					actualBoss.modifyManager(data, manager, 7,contrasenya);
				}

			}

			
			System.out.println("Manager modificado correctamente.");
		}

	}




	private static Person getPersonByName(ArrayList<? extends Person> saved, String name, String surname)
	{


		for (int i = 0; i < saved.size(); i++)
		{
			Person manager = (Person) saved.get(i);


			try
			{


				if (manager.getName().equalsIgnoreCase(name)
						&& manager.getSurname().equalsIgnoreCase(surname))
				{
					return manager;
				}

			}
			catch (Exception e)
			{
				System.out.println("No se enviaron manager");
			}

		}

		return null;
	}




	private static void modifyEmployee()
	{
		System.out.println("Introduce el nombre del Employee que deseas modificar:");
		String name = Entrada.cadena();

		Employee employee = getEmployeeByName(name);


		if (employee != null)
		{
			System.out.println("Introduce los nuevos datos del Employee:");
			System.out.print("Nombre: ");
			String newName = Entrada.cadena();
			System.out.print("Apellido: ");
			String surname = Entrada.cadena();
			System.out.print("Email: ");
			String email = Entrada.cadena();
			System.out.print("Tel�fono: ");
			String phone = Entrada.cadena();
			System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
			String birth = Entrada.cadena();
			System.out.print("�rea de trabajo: ");
			String area = Entrada.cadena();

			Boss boss = new Boss();
			boss.modifyEmployee(data, employee, newName, surname, email, phone, birth, area);
			System.out.println("Employee modificado correctamente.");
		}
		else
		{
			System.out.println("Employee no encontrado.");
		}

	}




	private static Employee getEmployeeByName(String name)
	{


		for (Employee employee : data.savedEmployee)
		{


			if (employee.getName().equalsIgnoreCase(name))
			{
				return employee;
			}

		}

		return null;
	}




	private static void viewActivityReport()
	{
		System.out.println("Informe de actividad:");
		ArrayList<String> logMessages = data.getLogMessages();


		for (String message : logMessages)
		{
			System.out.println(message);
		}

	}




	private static void startManagerSession()
	{

		int option = 0;
		Manager currentManager = null;


		do
		{
			System.out.println(StaticData.BARRA + " Manager " + StaticData.BARRA);
			System.out.println("\n�Qu� desea hacer?");
			System.out.println("1. Iniciar sesi�n");
			System.out.println("2. Volver al men� de inicio");

			option = Entrada.entero();


			switch (option)
			{
				case 1:
					currentManager = Manager.loginManager(data.savedManager);
					if (currentManager != null)
					{
						managerMenu(currentManager);
					}
					break;



				case 2:
					first();
					break;



				default:
					System.out.println("Opci�n no v�lida");
					break;
			}

		} while (option < 1 || option > 2);

	}




	private static void managerMenu(Manager currentManager)
	{
		int option = 0;


		do
		{
			System.out.println(StaticData.BARRA + " Men� del Manager " + StaticData.BARRA);
			System.out.println("\n�Qu� desea hacer, " + currentManager.getName() + "?");
			System.out.println("1. Crear nuevo empleado");
			System.out.println("2. Modificar empleado existente");
			System.out.println("3. Ver informe de actividades");
			System.out.println("4. Cerrar sesi�n");

			option = Entrada.entero();


			switch (option)
			{
				case 1:
					createEmployee(currentManager);
					break;



				case 2:
					modifyEmployee();
					break;



				case 3:
					viewActivityReport();
					break;



				case 4:
					System.out.println("\nCerrando sesi�n del Manager...");
					return;



				default:
					System.out.println("Opci�n no v�lida");
					break;
			}

		} while (option != 4);

	}




	private static void createEmployee(Manager currentManager)
	{
		Employee newEmployee = Employee.newEmployee();


		if (newEmployee != null)
		{
			data.savedEmployee.add(newEmployee);
			System.out.println("\n*** Nuevo empleado creado exitosamente ***\n");
		}
		else
		{
			System.out.println("\n*** Error al crear el nuevo empleado ***\n");
		}

		data.addLogMessage("El manager " + currentManager.getName() + " " + currentManager.getSurname()
				+ " ha creado al empleado " + newEmployee.getName() + " " + newEmployee.getSurname());

	}




	private static void startEmployeeSession()
	{
		// Implementaci�n de la sesi�n del Employee
	}

}
