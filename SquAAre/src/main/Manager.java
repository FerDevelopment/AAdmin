package main;

import java.util.ArrayList;

import com.comun.Entrada;

public class Manager extends Person implements Comparable<Manager>
{

	private static final long serialVersionUID = 4L;

	public Manager(String name, String surname, String email, String phone, String birth, String area)
	{
		super(name, surname, email, phone, birth, area);
	}

	public void createEmployee(StaticData data, String name, String surname, String email, String phone, String birth,
			String area)
	{
		Employee employee = new Employee(name, surname, email, phone, birth, area);
		data.addEmployee(employee);
		data.addLogMessage("Se ha creado un nuevo Employee: " + name);
	}

	public void modifyEmployee(StaticData data, Employee employee, String name, String surname, String email,
			String phone, String birth, String area)
	{
		employee.setName(name);
		employee.setSurname(surname);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setBirth(birth);
		employee.setArea(area);
		data.addLogMessage("Se ha modificado el Employee: " + name);
	}

	@Override
	public int compareTo(Manager otherUser)
	{
		return this.getName().compareTo(otherUser.getName());
	}

	public static Manager loginManager(ArrayList<Manager> managerList)
	{
		System.out.println("\n\n" + StaticData.BARRA + " Inicio de sesi�n del Manager " + StaticData.BARRA + "\n\n");
		System.out.println("Introduzca su nombre de usuario (Nickname): ");
		String nickname = Entrada.cadena();
		System.out.println("\nAhora escriba su contrase�a: ");
		String dSC = Entrada.cadena();
		String eSC = Person.encrypt(dSC);
		dSC = "";

		for (Manager manager : managerList)
		{
			if (manager.getNickname().equals(nickname) && manager.getESC().equals(eSC))
			{
				return manager;
			}
		}

		System.out.println("\n\n***Login fallido, intente nuevamente***\n\n");
		return null;
	}

	

}
