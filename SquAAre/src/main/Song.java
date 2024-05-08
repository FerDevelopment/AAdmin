
package main;

public class Song
{

	private String name;
	private int    duration; // Duración en segundos
	private String logo;     // Ruta de la imagen o identificador visual


	// Constructor
	public Song(String name, int duration, String logo)
	{
		this.name     = name;
		this.duration = duration;
		this.logo     = logo;
	}




	// Método para obtener el nombre de la canción
	public String getName()
	{
		return name;
	}




	// Método para establecer el nombre de la canción
	public void setName(String name)
	{
		this.name = name;
	}




	// Método para obtener la duración de la canción
	public int getDuration()
	{
		return duration;
	}




	// Método para establecer la duración de la canción
	public void setDuration(int duration)
	{
		this.duration = duration;
	}




	// Método para obtener el logo de la canción
	public String getLogo()
	{
		return logo;
	}




	// Método para establecer el logo de la canción
	public void setLogo(String logo)
	{
		this.logo = logo;
	}




	// Método para documentar la canción
	public void document()
	{
		// Aquí podrías implementar la lógica para documentar la canción
		// por ejemplo, guardar los detalles en un archivo o en una base de
		// datos
		System.out.println("Canción documentada: " + name);
	}




	// Método para convertir la canción a una representación de cadena
	@Override
	public String toString()
	{
		return "Song{" + "name='" + name + '\'' + ", duration=" + duration + ", logo='" + logo + '\'' + '}';
	}




	// Método main para probar la clase Song
	public static void main(String[] args)
	{
		// Ejemplo de creación de una canción
		Song song = new Song("Canción de ejemplo", 180, "ruta/imagen.jpg");

		// Imprimir la representación de cadena de la canción
		System.out.println(song);

		// Cambiar el nombre de la canción y la duración
		song.setName("Nueva Canción");
		song.setDuration(240);

		// Imprimir la representación de cadena actualizada
		System.out.println(song);

		// Documentar la canción
		song.document();
	}

}
