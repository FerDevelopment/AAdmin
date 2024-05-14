
package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Runnable
{

	private static StaticData data;
	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args)
	{
		data = new StaticData();
		startServer();
		first();
	}




	private static void startServer()
	{
		System.out.println("Iniciando servidor...");
		data.getUsers();
		data.getManagers();
		data.getCreator();
		System.out.println("Servidor iniciado\n");
	}




	@Override
	public void run()
	{

	}




	private static void first()
	{
		System.out.println(StaticData.BARRA + " ¿Qué tipo de usuario eres? " + StaticData.BARRA);
		System.out.println("1. Boss");
		System.out.println("2. Manager");
		System.out.println("3. Employee");
		int userType = Integer.parseInt(scanner.nextLine());


		switch (userType)
		{
			case 1:
				startBossSession();
				break;



			case 2:
				startManagerSession();
				break;



			case 3:
				startEmployeeSession();
				break;



			default:
				System.out.println("***Opción no válida***");
				first();
				break;
		}

	}




	private static void startBossSession()
	{

		int option;


		do
		{
			System.out.println(StaticData.BARRA + " Boss " + StaticData.BARRA);
			System.out.println("\n¿Qué deseas hacer?");
			System.out.println("1. Crear Manager");
			System.out.println("2. Crear Employee");
			System.out.println("3. Modificar Manager");
			System.out.println("4. Modificar Employee");
			System.out.println("5. Ver informe de actividad");
			System.out.println("6. Salir");
			option = Integer.parseInt(scanner.nextLine());


			switch (option)
			{
				case 1:
					createManager();
					break;



				case 2:
					createEmployee();
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
					System.exit(0);
					break;



				default:
					System.out.println("Opción no válida");
					break;
			}

		} while (option != 6);

	}




	private static void createManager()
	{
		System.out.println("Introduce los datos del nuevo Manager:");
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		System.out.print("Apellido: ");
		String surname = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Teléfono: ");
		String phone = scanner.nextLine();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = scanner.nextLine();
		System.out.print("Área de trabajo: ");
		String area = scanner.nextLine();

		Boss boss = data.savedCreator.get(0);
		boss.createManager(data, name, surname, email, phone, birth, area);
		System.out.println("Manager creado correctamente.");
	}




	private static void createEmployee()
	{
		System.out.println("Introduce los datos del nuevo Employee:");
		System.out.print("Nombre: ");
		String name = scanner.nextLine();
		System.out.print("Apellido: ");
		String surname = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Teléfono: ");
		String phone = scanner.nextLine();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = scanner.nextLine();
		System.out.print("Área de trabajo: ");
		String area = scanner.nextLine();

		Boss boss = data.savedCreator.get(0);
		boss.createEmployee(data, name, surname, email, phone, birth, area);
		System.out.println("Employee creado correctamente.");
	}




	private static void modifyManager()
	{
		System.out.println("Introduce el nombre del Manager que deseas modificar:");
		String name = scanner.nextLine();

		Manager manager = getManagerByName(name);


		if (manager != null)
		{
			System.out.println("Introduce los nuevos datos del Manager:");
			System.out.print("Nombre: ");
			String newName = scanner.nextLine();
			System.out.print("Apellido: ");
			String surname = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();
			System.out.print("Teléfono: ");
			String phone = scanner.nextLine();
			System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
			String birth = scanner.nextLine();
			System.out.print("Área de trabajo: ");
			String area = scanner.nextLine();

			Boss boss = data.savedCreator.get(0);
			boss.modifyManager(data, manager, newName, surname, email, phone, birth, area);
			System.out.println("Manager modificado correctamente.");
		}
		else
		{
			System.out.println("Manager no encontrado.");
		}

	}




	private static void modifyEmployee()
	{
		System.out.println("Introduce el nombre del Employee que deseas modificar:");
		String name = scanner.nextLine();

		Employee employee = getEmployeeByName(name);


		if (employee != null)
		{
			System.out.println("Introduce los nuevos datos del Employee:");
			System.out.print("Nombre: ");
			String newName = scanner.nextLine();
			System.out.print("Apellido: ");
			String surname = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();
			System.out.print("Teléfono: ");
			String phone = scanner.nextLine();
			System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
			String birth = scanner.nextLine();
			System.out.print("Área de trabajo: ");
			String area = scanner.nextLine();

			Boss boss = data.savedCreator.get(0);
			boss.modifyEmployee(data, employee, newName, surname, email, phone, birth, area);
			System.out.println("Employee modificado correctamente.");
		}
		else
		{
			System.out.println("Employee no encontrado.");
		}

	}




	private static Manager getManagerByName(String name)
	{


		for (Manager manager : data.savedManager)
		{


			if (manager.getName().equalsIgnoreCase(name))
			{
				return manager;
			}

		}

		return null;
	}




	private static Employee getEmployeeByName(String name)
	{


		for (Employee employee : data.savedUser)
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
		// Implementación de la sesión del Manager
	}




	private static void startEmployeeSession()
	{
		// Implementación de la sesión del Employee
	}

}
