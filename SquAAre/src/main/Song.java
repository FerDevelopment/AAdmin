package main;

public class Song {
    private String name;
    private int duration; // Duraci�n en segundos
    private String logo; // Ruta de la imagen o identificador visual

    // Constructor
    public Song(String name, int duration, String logo) {
        this.name = name;
        this.duration = duration;
        this.logo = logo;
    }

    // M�todo para obtener el nombre de la canci�n
    public String getName() {
        return name;
    }

    // M�todo para establecer el nombre de la canci�n
    public void setName(String name) {
        this.name = name;
    }

    // M�todo para obtener la duraci�n de la canci�n
    public int getDuration() {
        return duration;
    }

    // M�todo para establecer la duraci�n de la canci�n
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // M�todo para obtener el logo de la canci�n
    public String getLogo() {
        return logo;
    }

    // M�todo para establecer el logo de la canci�n
    public void setLogo(String logo) {
        this.logo = logo;
    }

    // M�todo para documentar la canci�n
    public void document() {
        // Aqu� podr�as implementar la l�gica para documentar la canci�n
        // por ejemplo, guardar los detalles en un archivo o en una base de datos
        System.out.println("Canci�n documentada: " + name);
    }

    // M�todo para convertir la canci�n a una representaci�n de cadena
    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", logo='" + logo + '\'' +
                '}';
    }

    // M�todo main para probar la clase Song
    public static void main(String[] args) {
        // Ejemplo de creaci�n de una canci�n
        Song song = new Song("Canci�n de ejemplo", 180, "ruta/imagen.jpg");

        // Imprimir la representaci�n de cadena de la canci�n
        System.out.println(song);

        // Cambiar el nombre de la canci�n y la duraci�n
        song.setName("Nueva Canci�n");
        song.setDuration(240);

        // Imprimir la representaci�n de cadena actualizada
        System.out.println(song);

        // Documentar la canci�n
        song.document();
    }
}

