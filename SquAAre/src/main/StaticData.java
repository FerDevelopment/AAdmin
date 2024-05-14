// Modificación de la clase StaticData

package main;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StaticData
{

	public static final String BARRA = "-------------------------";
	public static final String LOCALUSER = "./keys/data/acc/users.ser";
	public static final String LOCALMANAGER = "./keys/data/acc/managers.ser";
	public static final String LOCALCREATOR = "./keys/data/acc/creator.ser";
	public static final String LOCALPRODUCTS = "./keys/data/products.ser"; // Nueva ubicación para guardar los
																			// productos
	public static final String LOG_FILE = "./keys/data/log.txt";
	public static final String DIRECTORY_INFORM = "./keys/data/acc/inform";
	public static int encrypNum = 3;

	public ArrayList<Employee> savedUser = new ArrayList<>();
	public ArrayList<Manager> savedManager = new ArrayList<>();
	public ArrayList<Boss> savedCreator = new ArrayList<>();
	public ArrayList<Producto> savedProducts = new ArrayList<>(); // Nuevo ArrayList para guardar los
																	// productos
	private ArrayList<String> logMessages = new ArrayList<>();


	public StaticData()
	{
	}




	public ArrayList<Employee> getSavedUser()
	{
		return savedUser;
	}




	public void setSavedUser(ArrayList<Employee> savedUser)
	{
		this.savedUser = savedUser;
	}




	public ArrayList<Manager> getSavedManager()
	{
		return savedManager;
	}




	public void setSavedManager(ArrayList<Manager> savedManager)
	{
		this.savedManager = savedManager;
	}




	public ArrayList<Boss> getSavedCreator()
	{
		return savedCreator;
	}




	public void setSavedCreator(ArrayList<Boss> savedCreator)
	{
		this.savedCreator = savedCreator;
	}




	public ArrayList<Producto> getSavedProducts()
	{
		return savedProducts;
	}




	public void setSavedProducts(ArrayList<Producto> savedProducts)
	{
		this.savedProducts = savedProducts;
	}




	public ArrayList<String> getLogMessages()
	{
		return logMessages;
	}




	public void setLogMessages(ArrayList<String> logMessages)
	{
		this.logMessages = logMessages;
	}




	public void getUsers()
	{
		File file = new File(LOCALUSER);


		if (file.exists())
		{


			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALUSER);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedUser = (ArrayList<Employee>) objectIn.readObject();
				objectIn.close();
			}
			catch (Exception e)
			{
				savedUser = new ArrayList<>();
			}

		}

	}




	public void getManagers()
	{
		File file = new File(LOCALMANAGER);


		if (file.exists())
		{


			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALMANAGER);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedManager = (ArrayList<Manager>) objectIn.readObject();
				objectIn.close();
			}
			catch (Exception e)
			{
				savedManager = new ArrayList<>();
			}

		}

	}




	public void getCreator()
	{
		File file = new File(LOCALCREATOR);


		if (file.exists())
		{


			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALCREATOR);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedCreator = (ArrayList<Boss>) objectIn.readObject();
				objectIn.close();
			}
			catch (Exception e)
			{
				savedCreator = new ArrayList<>();
			}

		}

	}




	public void getProducts()
	{ // Método para cargar los productos guardados
		File file = new File(LOCALPRODUCTS);


		if (file.exists())
		{


			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALPRODUCTS);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedProducts = (ArrayList<Producto>) objectIn.readObject();
				objectIn.close();
			}
			catch (Exception e)
			{
				savedProducts = new ArrayList<>();
			}

		}

	}




	public void addEmployee(Employee employee)
	{
		savedUser.add(employee);
		saveUsers();
	}




	public void addManager(Manager manager)
	{
		savedManager.add(manager);
		saveManagers();
	}




	public void addProduct(Producto producto)
	{ // Método para agregar un producto y guardarlo
		savedProducts.add(producto);
		saveProducts();
	}




	public void addLogMessage(String message)
	{
		logMessages.add(LocalDate.now() + " - " + message);
		saveLog();
	}




	private void saveUsers()
	{


		try
		{
			FileOutputStream fileOut = new FileOutputStream(LOCALUSER);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(savedUser);
			objectOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}




	private void saveManagers()
	{


		try
		{
			FileOutputStream fileOut = new FileOutputStream(LOCALMANAGER);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(savedManager);
			objectOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}




	private void saveProducts()
	{ // Método para guardar los productos


		try
		{
			FileOutputStream fileOut = new FileOutputStream(LOCALPRODUCTS);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(savedProducts);
			objectOut.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}




	private void saveLog()
	{


		try
		{
			FileWriter fileWriter = new FileWriter(LOG_FILE, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(logMessages.get(logMessages.size() - 1));
			printWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
