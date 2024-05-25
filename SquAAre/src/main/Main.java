
package main;

import java.util.ArrayList;

import com.comun.*;

public class Main
{

	private static StaticData data;
	private static OutTextRun text;
	private static Boss actualBoss;
	private static Manager actualManager;
	private static Employee actualEmployee;

	public static void main(String[] args)
	{
		data = new StaticData();
		first();
		data.end();
	}

	private static void first()
	{
		System.out.println(StaticData.BARRA + " ¿Qué tipo de usuario eres? " + StaticData.BARRA);
		System.out.println("1. Boss");
		System.out.println("2. Manager");
		System.out.println("3. Employee");
		System.out.print("4. ComeBackHome\n-->");
		int userType = Entrada.entero();

		if (userType == 1234)
		{
			data.savedBoss.add(Boss.createBoss());
			try
			{
				data.pause(2);
			} catch (Exception e)
			{

			} finally
			{
				first();
			}

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

				case 4:
					System.out.println("\nADIOSSSSSSSSSSSSSSSSSSS");

					try
					{
						data.pause(2);
					} catch (InterruptedException e)
					{

						e.printStackTrace();
					} finally
					{
						StaticData.cls();
					}

					data.printEstrella();
					break;

				default:
					System.out.println("***Opción no válida***");
					first();
					break;
			}

		}

	}

	private static Person getPersonByName(ArrayList<? extends Person> saved, String name, String surname)
	{

		for (int i = 0; i < saved.size(); i++)
		{
			Person manager = (Person) saved.get(i);

			try
			{

				if (manager.getName().equalsIgnoreCase(name) && manager.getSurname().equalsIgnoreCase(surname))
				{
					return manager;
				}

			} catch (Exception e)
			{
				System.out.println("No se enviaron manager");
			}

		}

		return null;
	}

	private static void startBossSession()
	{
		actualBoss = (Boss) Person.login(data.savedBoss);
		if (actualBoss == null)
		{
			first();
			return;
		}
		int option;
		String auxName = "";
		String auxSurname = "";

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
					actualBoss.createManager(data);
					break;

				case 2:
					actualBoss.createEmployee(data);
					break;

				case 3:
					Manager aux1 = null;
					while (aux1 == null)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						auxSurname = Entrada.cadena();
						aux1 = (Manager) getPersonByName(data.savedEmployee, auxName, auxSurname);
					}

					actualBoss.modifyManager(data, aux1, actualBoss);
					break;

				case 4:
					Employee aux = null;
					while (aux == null)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						auxSurname = Entrada.cadena();
						aux = (Employee) getPersonByName(data.savedEmployee, auxName, auxSurname);
					}

					actualBoss.modifyEmployee(data, aux);
					break;

				case 5:
					actualBoss.viewActivityReport();
					break;

				case 6:
					actualBoss.logMessages.add("Usted cerró la sesión");
					actualBoss = null;
					try
					{
						data.pause(1);
					} catch (InterruptedException e)
					{

						e.printStackTrace();
					} finally
					{
						StaticData.cls();
					}

					break;

				default:
					System.out.println("Opción no válida");
					break;
			}

		} while (option != 6);
		first();

	}

	private static void startEmployeeSession()
	{
		actualEmployee = (Employee) Person.login(data.savedEmployee);
		System.out.println(actualEmployee.getPathId());
		if (actualEmployee == null)
		{
			first();
			return;
		}
		if (actualEmployee != null)
		{

			int option = 0;

			do
			{
				System.out.println("\n\n" + StaticData.BARRA + " Menú de Empleado " + StaticData.BARRA + "\n\n");
				System.out.println("1. Realizar un reporte personalizado");
				System.out.println("2. Realizar un reporte de ventas");
				System.out.println("3. Enviar productos");
				System.out.println("4. Ver la cantidad de productos");
				System.out.println("5. Modificar algún producto y su cantidad");
				System.out.println("6. Cerrar sesión");

				option = Entrada.entero();

				switch (option)
				{
					case 1:
						actualEmployee.createPersonalizedReport();
						break;

					case 2:
						actualEmployee.createSalesReport();
						break;

					case 3:
						actualEmployee.sendProducts(data.savedProducts);
						break;

					case 4:
						actualEmployee.viewProduct(data.savedProducts);
						break;

					case 5:
						actualEmployee.modifyProduct(data.savedProducts);
						break;

					case 6:
						System.out.println("\nCerrando sesión...\n");
						actualEmployee.logMessages.add("Sesión cerrada");
						actualEmployee.saveDocument();
						Product.savedProduct(data.savedProducts);
						actualEmployee = null;
						break;

					default:
						System.out.println("\nOpción no válida. Intente de nuevo.\n");
						try
						{
							data.pause(2);
						} catch (InterruptedException e)
						{

							e.printStackTrace();
						} finally
						{
							StaticData.cls();
						}

						break;
				}

			} while (option != 6);
			first();

		}

	}

	private static void startManagerSession()
	{

		actualManager = (Manager) Person.login(data.savedManager);
		if (actualManager == null)
		{
			first();
			return;
		}
		int option = 0;

		do
		{
			System.out.println(StaticData.BARRA + " Menú del Manager " + StaticData.BARRA);
			System.out.println("\n¿Qué desea hacer, " + actualManager.getName() + "?");
			System.out.println("1. Crear nuevo empleado");
			System.out.println("2. Modificar empleado existente");
			System.out.println("3. Ver informe de actividades");
			System.out.println("4. Cerrar sesión");

			option = Entrada.entero();

			switch (option)
			{
				case 1:
					actualManager.createEmployee(data);
					break;

				case 2:
					Employee aux = null;
					while (aux == null)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						String auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						String auxSurname = Entrada.cadena();

						aux = (Employee) getPersonByName(data.savedEmployee, auxName, auxSurname);
					}

					actualManager.modifyEmployee(data, aux);
					break;

				case 3:
					actualManager.viewActivityReport();
					break;

				case 4:
					System.out.println("\nCerrando sesión del Manager...");
					try
					{
						data.pause(2);
					} catch (InterruptedException e)
					{

						e.printStackTrace();
					} finally
					{
						StaticData.cls();
					}
					actualManager.logMessages.add("Sesión cerrada");
					actualManager = null;

					break;

				default:
					System.out.println("Opción no válida");

					break;
			}

		} while (option != 4);
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

}
