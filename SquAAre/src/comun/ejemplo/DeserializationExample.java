
package comun.ejemplo;

import java.io.*;
import java.util.List;
import main.*;

public class DeserializationExample
{

	public static void main(
			String[] args)
	{


		// Deserializar la lista desde el archivo
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.ser")))
		{
			List<Person> deserializedPeople = (List<Person>) ois.readObject();


			// Iterar sobre la lista para procesar cada objeto
			for (Person person : deserializedPeople)
			{
				System.out.println("Deserializado: " + person.toString());
			}

		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
