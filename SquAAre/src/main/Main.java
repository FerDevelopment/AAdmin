
package main;

import java.util.*;

import com.comun.Entrada;

public class Main implements Runnable
{
	private static StaticData data;
	private volatile static boolean isRunning = true;
	private volatile static boolean shouldStop = false;

	@Override
	public void run()
	{
		while (isRunning)
		{
			System.out.println(". . . Iniciado Servidor . . .");
			try
			{
				Thread.sleep(1000); // Esperar 1 segundo entre ejecuciones
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				break;
			}

			// Si se marca para detener, salimos del bucle después de esta iteración
			if (shouldStop)
			{
				break;
			}
		}
	}

	private static void start()
	{
		int option = 0;
		System.out.println("¿Eres usuario o creador?");
		System.out.println("1. Usuario");
		System.out.println("2. Creador");
		int userType = Entrada.entero();
		if (userType == 1)
		{
			System.out.println("¿Quieres iniciar sesión o registrarte?");
			System.out.println("1. Iniciar sesión");
			System.out.println("2. Registrarte");
			System.out.println("3. volver al menu de inicio");
			do
			{
				option = Entrada.entero();

				if (option == 1)
				{
					loginUser();
				}
				else if (option == 2)
				{
					registerUser();
				}
				else if (option == 3)
				{
					start();
				}
				else
				{
					System.out.println("Opción no válida");
				}
			} while (option < 1 || option > 3);
		}
		else if (userType == 2)
		{
			// Aquí puedes agregar la lógica para el tipo de usuario "creador"
		}
		else
		{
			System.out.println("Opción no válida");
		}
	}

	private static void loginUser()
	{

		// Obtener la lista de usuarios

		// Pedir al usuario que introduzca sus datos para iniciar sesión

		System.out.print("Introduzca su nombre de usuario: ");
		String username = Entrada.cadena();
		System.out.print("Introduzca su contraseña: ");
		String password = Entrada.cadena();

		// Realizar el inicio de sesión
		if (login(data.savedUser, username, password))
		{
			System.out.println("Inicio de sesión exitoso.");
		}
		else
		{
			System.out.println("Inicio de sesión fallido.");
		}

		// Detener el timer
	}

	private void checkStaticDate()
	{
		if (data == null || data.SLogin == null)
		{
			isRunning = true;
			shouldStop = false;
			new Thread(this).start();
			data = new StaticData();
			shouldStop = true;
			System.out.println("Sevidor iniciado");
			try
			{
				data.pause(2);
			} catch (InterruptedException e)
			{

				e.printStackTrace();
			}

		}
	}

	private static void registerUser()
	{
		// Aquí puedes agregar la lógica para el registro de usuarios
	}

	private static boolean login(ArrayList<User> userList, String username, String password)
	{
		User dummyUser = new User();
		dummyUser.nickname = username;
		dummyUser.dSC = password;
		String encryptedPassword = User.encrypt(dummyUser.dSC);

		int index = Collections.binarySearch(userList, dummyUser);
		if (index >= 0)
		{
			User storedUser = userList.get(index);
			return storedUser.eSC.equals(encryptedPassword);
		}
		return false;
	}

	public static void main(String[] args)
	{
		/* checkStaticDate(); */
		start();
		end();
	}

	private static void end()
	{

	}

}
