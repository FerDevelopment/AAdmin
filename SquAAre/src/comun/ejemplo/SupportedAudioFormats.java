package comun.ejemplo;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

public class SupportedAudioFormats {
    public static void main(String[] args) {
        AudioFileFormat.Type[] types = AudioSystem.getAudioFileTypes();
        System.out.println("Supported Audio File Formats:");
        for (AudioFileFormat.Type type : types) {
            System.out.println(type);
            System.out.println("xd");
        }
    }
}
