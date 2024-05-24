// Modificaci�n de la clase Employee

package main;

import java.util.ArrayList;
import java.util.Random;

import com.comun.*;

public class Employee extends Person implements Comparable<Employee>
{

	private static final long serialVersionUID = 2L;
	private String acountCode;
	private String pathId;
	private String nickname;


	public Employee(String name, String surname, String email, String phone, String birth, String area,
			String acountCode, String pathId, String nickname, String eSC)
	{
		super(name, surname, email, phone, birth, area);
		this.acountCode = acountCode;
		this.pathId = pathId;
		this.nickname = nickname;
		this.eSC = eSC;
	}




	public static Employee newEmployee()
	{
		Person aux = Person.newPerson();
		Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrase�a: ");
		String dSC = Entrada.cadena();
		String eSC = Person.encrypt(dSC);
		dSC = "";
		String pathId = StaticData.DIRECTORY_INFORM + aux.getSurname() + aux.getName() + ".csv";
		String aux1 = "";

		Random r = new Random();


		for (int i = 0; i < 0; i++)
		{
			aux1 += r.nextInt() + "";
		}

		String acountCode = aux.getName() + nickname + aux1;
		return new Employee(aux.getName(), aux.getSurname(), aux.getEmail(), aux.getPhone(),
				aux.getBirth().toString(), aux.getArea(), acountCode, pathId, nickname, eSC);
	}




	public Employee(String nickname, String eSC)
	{
		super();
		this.nickname = nickname;
		this.eSC = eSC;
	}




	public Employee(String name, String surname, String email, String phone, String birth, String area)
	{
		super(name, surname, email, phone, birth, area);
	}




	public String getAcountCode()
	{
		return acountCode;
	}




	public String getPathId()
	{
		return pathId;
	}




	@Override
	public String toString()
	{
		return "Employee [acountCode=" + acountCode + ", pathId=" + pathId + ", nickname=" + nickname
				+ ", toString()=" + super.toString() + "]";
	}




	@Override
	public int compareTo(Employee otherUser)
	{
		return this.nickname.compareTo(otherUser.nickname);
	}




	public void a�adirProducto(ArrayList<Product> productos, Product producto)
	{
		productos.add(producto);
	}




	public void quitarProducto(ArrayList<Product> productos, String nombreProducto)
	{
		productos.removeIf(producto -> producto.getName().equals(nombreProducto));
	}




	public void modifyProduct(ArrayList<Product> productos)
	{
		/*
		 * 
		 * for (int i = 0; i < productos.size(); i++) {
		 * 
		 * 
		 * if (productos.get(i).getName().equals(nombreProducto)) {
		 * productos.set(i, productoModificado); break; }
		 * 
		 * }�
		 */

	}




	public void sendProducts(ArrayList<Product> productos)
	{
		// Implementaci�n simple para demostrar el env�o
		StringBuilder envioDetalle = new StringBuilder("Env�o realizado con los siguientes productos:\n");


		for (Product producto : productos)
		{
			envioDetalle.append(producto.getName()).append(" - Cantidad: ").append(producto.getCantidad())
					.append("\n");
		}

		Printer.print(envioDetalle.toString());
		productos.clear(); // Asumimos que despu�s del env�o, se vac�a la lista de productos
	}




	public void createPersonalizedReport()
	{
		// Implementar la l�gica para crear un reporte personalizado
		System.out.println("Creando un reporte personalizado...");
		this.logMessages.add("Empleado " + nickname + " cre� un reporte personalizado.");
		// L�gica espec�fica para crear el reporte
	}




	public void createSalesReport()
	{
		// Implementar la l�gica para crear un reporte de ventas
		System.out.println("Creando un reporte de ventas...");
		this.logMessages.add("Empleado " + nickname + " cre� un reporte de ventas.");
		// L�gica espec�fica para crear el reporte de ventas
	}




	public void viewProductQuantities()
	{
		// Implementar la l�gica para ver la cantidad de productos
		System.out.println("Viendo la cantidad de productos...");
		this.logMessages.add("Empleado " + nickname + " vio la cantidad de productos.");
		// L�gica espec�fica para ver las cantidades de los productos
	}

}
