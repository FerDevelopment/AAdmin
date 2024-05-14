
package main;

import java.util.ArrayList;
import com.comun.*;

public class Main implements Runnable
{

	private static StaticData data;
	private static OutTextRun text;

	public static void main(String[] args)
	{
		data = new StaticData();
		first();

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

				}
				else if (option.equals("manager"))
				{
					data.getManagers();

				}
				else if (option.equals("employee"))
				{
					data.getEmployee();

				}
			}

		});

		hilo2.start();

		try
		{
			hilo2.join();
		} catch (Exception e)
		{
			System.out.println("XD malito");
		}

		text.stop();

		try
		{
			hilo1.join();
			StaticData.cls();
			data.pause(2);

		} catch (InterruptedException e)
		{

			e.printStackTrace();
		}

		System.out.println("\nSevidor iniciado");

		try
		{
			data.pause(2);
		} catch (Exception e)
		{
			System.out.println("Pasusa malita");
		}

		StaticData.cls();
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
		System.out.print("3. Employee\n-->");
		int userType = Entrada.entero();

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
			option = Entrada.entero();

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
					end();
					first();
					break;

				default:
					System.out.println("Opción no válida");
					break;
			}

		} while (option != 6);

	}

	private static void end()
	{
		// TODO Auto-generated method stub

	}

	private static void createManager()
	{
		System.out.println("Introduce los datos del nuevo Manager:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Teléfono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("Área de trabajo: ");
		String area = Entrada.cadena();

		Boss boss = new Boss();
		boss.createManager(data, name, surname, email, phone, birth, area);
		System.out.println("Manager creado correctamente.");
	}

	private static void createEmployee()
	{
		System.out.println("Introduce los datos del nuevo Employee:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Teléfono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("Área de trabajo: ");
		String area = Entrada.cadena();

		Boss boss = new Boss();
		boss.createEmployee(data, name, surname, email, phone, birth, area);
		System.out.println("Employee creado correctamente.");
	}

	private static void modifyManager()
	{
		System.out.println("Introduce el nombre del Manager que deseas modificar:");
		String name = Entrada.cadena();

		Manager manager = getManagerByName(name);

		if (manager != null)
		{
			System.out.println("Introduce los nuevos datos del Manager:");
			System.out.print("Nombre: ");
			String newName = Entrada.cadena();
			System.out.print("Apellido: ");
			String surname = Entrada.cadena();
			System.out.print("Email: ");
			String email = Entrada.cadena();
			System.out.print("Teléfono: ");
			String phone = Entrada.cadena();
			System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
			String birth = Entrada.cadena();
			System.out.print("Área de trabajo: ");
			String area = Entrada.cadena();

			Boss boss= new Boss();
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
			System.out.print("Teléfono: ");
			String phone = Entrada.cadena();
			System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
			String birth = Entrada.cadena();
			System.out.print("Área de trabajo: ");
			String area = Entrada.cadena();

			Boss boss= new Boss();
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
			System.out.println("\n¿Qué desea hacer?");
			System.out.println("1. Iniciar sesión");
			System.out.println("2. Volver al menú de inicio");

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
					System.out.println("Opción no válida");
					break;
			}
		} while (option < 1 || option > 2);
	}

	private static void managerMenu(Manager currentManager)
	{
		int option = 0;

		do
		{
			System.out.println(StaticData.BARRA + " Menú del Manager " + StaticData.BARRA);
			System.out.println("\n¿Qué desea hacer, " + currentManager.getName() + "?");
			System.out.println("1. Crear nuevo empleado");
			System.out.println("2. Modificar empleado existente");
			System.out.println("3. Ver informe de actividades");
			System.out.println("4. Cerrar sesión");

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
					System.out.println("\nCerrando sesión del Manager...");
					return;
				default:
					System.out.println("Opción no válida");
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
	}

	private static void startEmployeeSession()
	{
		// Implementación de la sesión del Employee
	}

}
