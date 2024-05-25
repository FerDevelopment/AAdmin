
package main;

public class OutTextRun implements Runnable
{

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
			} catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				break;
			}

			// Si se marca para detener, salimos del bucle despu�s de esta
			// iteraci�n
			if (shouldStop)
			{
				break;
			}

		}

	}

	public boolean stop()
	{
		// Marca para detener despu�s de la pr�xima iteraci�n
		shouldStop = true;
		return true;
	}

	public void start()
	{
		// Resetea las banderas
		isRunning = true;
		shouldStop = false;

	}

	public static void pause(double seg) throws InterruptedException
	{
		int aux = (int) (seg * 1000);
		Thread.sleep(aux);
	}

	public static boolean outString(String out)
	{

		for (int i = 0; i < out.length(); i++)
		{
			System.out.print(out.charAt(i));

			try
			{
				pause(0.09);
			} catch (InterruptedException e)
			{
				System.out.println("NO va");
			}

		}

		return false;

	}

}
