
package comun.ejemplo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import main.*;

public class SerializationExample
{

	public static void main(
			String[] args)
	{
		// Crear varias instancias de Person
		List<Person> people = new ArrayList<>();
		people.add(new User());
	


		// Serializar la lista a un archivo
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people.ser")))
		{
			oos.writeObject(people);
			System.out.println("izi");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
