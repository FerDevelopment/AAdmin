
package main;

public class OutTextRun implements Runnable
{

	/* Atributos */
	public volatile boolean isRunning = true;
	public volatile boolean shouldStop = false;


	@Override
	public void run()
	{


		while (isRunning)
		{
			outString("\n. . . Iniciando servidor . . .");


			try
			{
				Thread.sleep(1000); // Esperar 1 segundo entre ejecuciones
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				break;
			}


			/* Si tiene que parar detiene el bucle */
			if (shouldStop)
			{
				break;
			}

		}

	}




	/* Senyal de parada */
	public boolean stop()
	{
		// Marca para detener después de la próxima iteración
		shouldStop = true;
		return true;
	}




	/* Senyal de inicio */
	public void start()
	{
		// Resetea las banderas
		isRunning = true;
		shouldStop = false;

	}




	/* Pasua propia del metodo para no meter Hilos adicionales */
	public static void pause(double seg) throws InterruptedException
	{
		int aux = (int) (seg * 1000);
		Thread.sleep(aux);
	}




	/* Este es el mensaje que tinee que printear */
	public static boolean outString(String out)
	{


		for (int i = 0; i < out.length(); i++)
		{
			System.out.print(out.charAt(i));


			try
			{
				pause(0.09);
			}
			catch (InterruptedException e)
			{
				System.out.println("NO va");
			}

		}

		return false;

	}

}
