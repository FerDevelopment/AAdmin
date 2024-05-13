
package main;

import java.util.*;

import com.comun.Entrada;
import com.comun.Printer;

public class Main implements Runnable
{

	private static StaticData data;
	private static OutTextRun text;
	private static User dummyUser;

	public static void main(String[] args)
	{
		text = new OutTextRun();
		first();
		end();
	}

	private static void end()
	{
		if (data.savedCreator != null)
		{

			Collections.sort(data.savedCreator);
			data.savedCreator.clear();
		}
		if (data.savedUser != null)
		{

			Collections.sort(data.savedUser);
			data.savedCreator.clear();
		}

	}

	@Override
	public void run()
	{

	}

	private static void checkStaticDataUser()
	{

		if (data == null || data.SLoginUser == null)
		{

			Thread hilo1 = new Thread(text);
			hilo1.start();

			Thread hilo2 = new Thread(new Runnable()
			{

				public void run()
				{

					data = new StaticData();
					data.getUsers();
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

	private static void checkStaticDataCreator()
	{
		if (data == null || data.SLoginCreator == null)
		{

			Thread hilo1 = new Thread(text);
			hilo1.start();

			Thread hilo2 = new Thread(new Runnable()
			{

				public void run()
				{

					data = new StaticData();
					data.getCreator();
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

	private static void first()
	{

		System.out.println("¿Eres usuario o creador?");
		System.out.println("1. Usuario");
		System.out.println("2. Creador");
		int userType = Entrada.entero();
		if (userType == 1)
		{
			startUser();
		}
		else if (userType == 2)
		{
			startCreator();
		}
		else
		{
			Printer.print("\n***Opcion no valida***\n");
			first();
		}
	}

	private static void startCreator()
	{
		checkStaticDataCreator();
		int option = 0;
		System.out.println(StaticData.BARRA + " Creador " + StaticData.BARRA);
		System.out.println("\n¿Quieres iniciar sesión o registrarte?");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Registrarte");
		System.out.println("3. volver al menu de inicio");

		do
		{
			option = Entrada.entero();

			if (option == 1)
			{
				loginUser(data.savedUser);
			}
			else if (option == 2)
			{
				registerUser();
			}
			else if (option == 3)
			{
				first();
			}
			else
			{
				System.out.println("Opción no válida");
			}

		} while (option < 1 || option > 3);
	}

	private static void startUser()
	{
		checkStaticDataUser();
		int option = 0;
		System.out.println(StaticData.BARRA + " Usuario " + StaticData.BARRA);
		System.out.println("\n¿Quieres iniciar sesión o registrarte?");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Registrarte");
		System.out.println("3. volver al menu de inicio");

		do
		{
			option = Entrada.entero();

			if (option == 1)
			{
				loginUser(data.savedUser);
			}
			else if (option == 2)
			{
				registerUser();
			}
			else if (option == 3)
			{
				first();
			}
			else
			{
				System.out.println("Opción no válida");
			}

		} while (option < 1 || option > 3);

	}

	private static void registerUser()
	{
		User new_ = User.newUser();
		data.savedUser.add(new_);

		try
		{
			data.pause(2);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" * * * Registro completado * * * ");
		startUser();
	}

	private static void loginUser(ArrayList<User> userList)
	{
		dummyUser = User.newLoginUser();

		int index = Collections.binarySearch(userList, dummyUser);

		if (index >= 0)
		{
			dummyUser = userList.get(index);

		}
		else
		{
			Printer.print("\n\n***Login fallido, intentelo otra vez***");
			try
			{
				data.pause(2);
			} catch (Exception e)
			{
				// TODO: handle exception
			} finally
			{
				loginUser(userList);
			}

		}

	}
}
