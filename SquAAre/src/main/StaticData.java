// Modificación de la clase StaticData

package main;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StaticData
{

	public static final String BARRA = "-------------------------";
	public static final String LOCALEMPLOYEE = "./keys/data/acc/employees.ser";
	public static final String LOCALMANAGER = "./keys/data/acc/managers.ser";
	public static final String LOCALBOSS = "./keys/data/acc/bosses.ser";
	public static final String LOCALPRODUCTS = "./keys/data/products.ser"; // Nueva ubicación para
																			// guardar los
																			// productos
	public static final String LOG_FILE = "./keys/data/log.txt";
	public static final String DIRECTORY_INFORM = "./keys/data/acc/inform";
	public static int encrypNum = 3;

	public ArrayList<Employee> savedEmployee = new ArrayList<>();
	public ArrayList<Manager> savedManager = new ArrayList<>();
	public ArrayList<Boss> savedBoss = new ArrayList<>();
	public ArrayList<Producto> savedProducts = new ArrayList<>(); // Nuevo ArrayList para guardar los
																	// productos
	private ArrayList<String> logMessages = new ArrayList<>();

	public StaticData()
	{
	}

	public ArrayList<Manager> getSavedManager()
	{
		return savedManager;
	}

	public void setSavedManager(ArrayList<Manager> savedManager)
	{
		this.savedManager = savedManager;
	}

	public ArrayList<Boss> getSavedBoss()
	{
		return savedBoss;
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

	@SuppressWarnings("unchecked")
	public void getEmployee()
	{
		File file = new File(LOCALEMPLOYEE);

		if (file.exists())
		{

			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALEMPLOYEE);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedEmployee = (ArrayList<Employee>) objectIn.readObject();
				objectIn.close();
			} catch (Exception e)
			{
				savedEmployee = new ArrayList<>();
			}

		}

	}

	@SuppressWarnings("unchecked")
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
			} catch (Exception e)
			{
				savedManager = new ArrayList<>();
			}

		}

	}

	@SuppressWarnings("unchecked")
	public void getBoss()
	{
		File file = new File(LOCALBOSS);

		if (file.exists())
		{

			try
			{
				FileInputStream fileIn = new FileInputStream(LOCALBOSS);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				savedBoss = (ArrayList<Boss>) objectIn.readObject();
				objectIn.close();
			} catch (Exception e)
			{
				savedBoss = new ArrayList<>();
			}

		}

	}

	@SuppressWarnings("unchecked")
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
			} catch (Exception e)
			{
				savedProducts = new ArrayList<>();
			}

		}

	}

	public void addEmployee(Employee employee)
	{
		if (savedEmployee != null)
		{
			savedEmployee = new ArrayList<>();
		}
		savedEmployee.add(employee);
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
			FileOutputStream fileOut = new FileOutputStream(LOCALEMPLOYEE);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(savedEmployee);
			objectOut.close();
		} catch (IOException e)
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
		} catch (IOException e)
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
		} catch (IOException e)
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
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void pause(int i) throws InterruptedException
	{

		Thread.sleep((int) 1000 * i);

	}

	public static void cls()
	{
		for (int i = 0; i < 50; i++)
		{
			System.out.print("\n\n");
		}

		try
		{
			Thread.sleep(100);
		} catch (Exception e)
		{
			// TODO: handle exception
		}

	}

}
