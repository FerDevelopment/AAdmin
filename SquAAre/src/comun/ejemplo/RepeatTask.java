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

            // Si se marca para detener, salimos del bucle después de esta iteración
            if (shouldStop) {
                break;
            }
        }
    }

    public void stop() {
        // Marca para detener después de la próxima iteración
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

        // Esperar algunos segundos para observar la ejecución
      

        // Detener la tarea después de la próxima iteración
        task.stop();
    }
}
