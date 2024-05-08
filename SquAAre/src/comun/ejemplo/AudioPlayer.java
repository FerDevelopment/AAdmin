package comun.ejemplo;

import java.io.*;


import javax.sound.sampled.*;

public class AudioPlayer {
	private Clip clip;

    public void loadAudio(String filePath) {
        try {
        	
            File audioFile= new File(filePath);
			// Obtener el archivo de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Obtener un Clip de audio
            clip = AudioSystem.getClip();

            // Abrir el Clip con el flujo de audio
            clip.open(audioInputStream);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }

    public static void main(String[] args) {
        
        String ruta = "./import/music/";
		File directorio = new File(ruta);
		String[] lista = directorio.list();
		
		AudioPlayer audioPlayer = new AudioPlayer();
 
		audioPlayer.loadAudio(ruta+lista[0]);
        
		audioPlayer.play();
		for (int i = 0; i < lista.length; i++)
		{
			System.out.println(ruta+lista[i]);
		}
		
		
		 
		
		
        // Esperar un tiempo para luego pausar
        try {
            Thread.sleep(5000); // Pausar después de 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audioPlayer.pause();

        // Esperar un tiempo para luego reanudar
        try {
            Thread.sleep(3000); // Reanudar después de 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audioPlayer.play();

        // Esperar un tiempo para luego detener
        try {
            Thread.sleep(5000); // Detener después de 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audioPlayer.stop();
    }
}
