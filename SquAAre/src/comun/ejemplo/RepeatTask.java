package comun.ejemplo;
public class RepeatTask implements Runnable {
    private volatile boolean isRunning = true;
    private volatile boolean shouldStop = false;

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("Ejecutando tarea repetida...");
            try {
                Thread.sleep(1000); // Esperar 1 segundo entre ejecuciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            // Si se marca para detener, salimos del bucle despu�s de esta iteraci�n
            if (shouldStop) {
                break;
            }
        }
    }

    public void stop() {
        // Marca para detener despu�s de la pr�xima iteraci�n
        shouldStop = true;
    }

    public void start() {
        // Resetea las banderas
        isRunning = true;
        shouldStop = false;
        new Thread(this).start(); // Inicia un nuevo hilo para la tarea
    }

    public static void main(String[] args) throws InterruptedException {
        RepeatTask task = new RepeatTask();

        // Iniciar la tarea que se repite cada segundo
        task.start();

        // Esperar algunos segundos para observar la ejecuci�n
      

        // Detener la tarea despu�s de la pr�xima iteraci�n
        task.stop();
    }
}
