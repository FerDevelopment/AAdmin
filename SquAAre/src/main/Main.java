
package main;

import java.util.ArrayList;

import com.comun.*;

public class Main
{

	/*
	 * Son variables auxiliares para evitar enviar parametros por
	 * parametro
	 */
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




	/**
	 * Este metodo es el primero que se ejecuta en todo el codigo
	 */
	private static void first()
	{
		System.out.println(StaticData.BARRA + " ¿Qué tipo de usuario eres? " + StaticData.BARRA);
		System.out.println("1. Boss");
		System.out.println("2. Manager");
		System.out.println("3. Employee");
		System.out.print("4. ComeBackHome\n-->");
		Integer userType = Entrada.entero();


		/* Codigo secreto para crear un boss */
		if (userType == 1234)
		{
			data.savedBoss.add(Boss.createBoss());


			try
			{
				data.pause(2);
			}
			catch (Exception e)
			{

			}
			finally
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
					}
					catch (InterruptedException e)
					{

						e.printStackTrace();
					}
					finally
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




	/**
	 * Este metodo se encarga de devolver una persona en base a una lista,
	 * su nombre y apellidos
	 * 
	 * @param saved,   es la lista donde esta almacenada la persona
	 * @param name,    nombre de la persona
	 * @param surname, apellido de la persona @return, en caso de
	 *                 encontrar, la persona
	 */
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




	/**
	 * Este es el menu del boss, a partir de aqui funciona todo el
	 * programa
	 */
	private static void startBossSession()
	{
		/* Intentamos buscar el boss con el metodo del login */

		actualBoss = (Boss) Person.login(data.savedBoss);


		/* En caso de no contrarse lo devolvemos al menu inicial */
		if (actualBoss == null)
		{
			first();
			return;
		}

		Integer option;
		String auxName = "";
		String auxSurname = "";
		Integer max = 3;


		/* Menu del boss */
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
					while (aux1 == null && max >= 1)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						auxSurname = Entrada.cadena();
						aux1 = (Manager) getPersonByName(data.savedEmployee, auxName, auxSurname);
						max--;
					}
					if (aux1 != null)
					{

						actualBoss.modifyManager(data, aux1);
						max = 3;
					}
					else
					{
						max = 3;
						System.out.println("Haz alcanzado el maximo de intentos para esto :(");


						try
						{
							data.pause(2);
						}
						catch (Exception e)
						{
							System.out.println("a");
						}

						StaticData.cls();
					}
					break;



				case 4:
					Employee aux = null;
					while (aux == null && max >= 1)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						auxSurname = Entrada.cadena();
						aux = (Employee) getPersonByName(data.savedEmployee, auxName, auxSurname);
						max--;
					}
					if (aux != null)
					{

						actualBoss.modifyEmployee(data, aux);
						max = 3;
					}
					else
					{
						System.out.println("Haz alcanzado el maximo de intentos para esto :(");


						try
						{
							data.pause(2);
						}
						catch (Exception e)
						{
							System.out.println("a");
						}

						max = 3;
						StaticData.cls();

					}

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
					}
					catch (InterruptedException e)
					{

						e.printStackTrace();
					}
					finally
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




	/**
	 * Metodo para el menu del empleado
	 */
	private static void startEmployeeSession()
	{
		/* Hacemos el login del empleado */
		actualEmployee = (Employee) Person.login(data.savedEmployee);
		System.out.println(actualEmployee.getPathId());


		if (actualEmployee == null)
		{
			first();
			return;
		}


		if (actualEmployee != null)
		{

			Integer option = 0;


			do
			{
				System.out.println(
						"\n\n" + StaticData.BARRA + " Menú de Empleado " + StaticData.BARRA + "\n\n");
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
						}
						catch (InterruptedException e)
						{

							e.printStackTrace();
						}
						finally
						{
							StaticData.cls();
						}

						break;
				}

			} while (option != 6);

			first();

		}

	}




	/**
	 * Menu del manager
	 */
	private static void startManagerSession()
	{
		/* Intentamos hacer el login */
		actualManager = (Manager) Person.login(data.savedManager);


		if (actualManager == null)
		{
			first();
			return;
		}

		Integer option = 0;
		Integer max = 3;
		Integer trye = max;


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
					while (aux == null && max >= 1)
					{
						System.out.println("Introduzca el nombre del empleado a modificar");
						String auxName = Entrada.cadena();
						System.out.println("Introduzca el apellido del empleado a modificar");
						String auxSurname = Entrada.cadena();

						aux = (Employee) getPersonByName(data.savedEmployee, auxName, auxSurname);
						max--;
					}
					if (aux != null)
					{

						actualManager.modifyEmployee(data, aux);
						max = trye;
					}
					else
					{
						System.out.println("Se ha alcanzado el numero maximo de ingresos");
						max = trye;
					}

					break;



				case 3:
					actualManager.viewActivityReport();
					break;



				case 4:
					System.out.println("\nCerrando sesión del Manager...");
					try
					{
						data.pause(2);
					}
					catch (InterruptedException e)
					{

						e.printStackTrace();
					}
					finally
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




	/**
	 * Este metodo se utiliza para arrancar el programa
	 * 
	 * @param option, depende del tipo de usuario
	 */
	private static void startServer(String option)
	{

		text = new OutTextRun();
		/* Creamos un hilo con la salida de texto */
		Thread hilo1 = new Thread(text);
		StaticData.cls();

		/*
		 * iniciamos el hilo con el texto de ". . .  iniciando servidor . . ."
		 */
		hilo1.start();

		/*
		 * Declaramos un segundo hilo donde le pondremos que tipo de usuario
		 * somos
		 */
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

		/* INiciamos el hilo dos */
		hilo2.start();


		/* Entramos el hilo dos en cola para que tenga prioridad */
		try
		{
			hilo2.join();
		}
		catch (Exception e)
		{
			System.out.println("XD malito");
		}

		/* Una vez acabado el proceso dos se para el primero */
		text.stop();


		/*
		 * Lo ponemos en cola para que el programa espere a que acabe el
		 * proceso 1 para seguir
		 */
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


		/* Hacemos una pausa y despues limpiamos la consola */
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

}
