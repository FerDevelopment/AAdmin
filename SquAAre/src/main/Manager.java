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

	public void modifyEmployee(StaticData data, Employee employee, int option, String newValue)
	{
		String oldValue = "";


		switch (option)
		{
			case 0:
				// Modificar nombre
				oldValue = employee.getName();
				employee.setName(newValue);
				data.addLogMessage("Nombre del empleado " + oldValue + " cambiado a " + newValue);
				break;



			case 1:
				// Modificar apellido
				oldValue = employee.getSurname();
				employee.setSurname(newValue);
				data.addLogMessage("Apellido del empleado " + oldValue + " cambiado a " + newValue);
				break;



			case 2:
				// Modificar email
				oldValue = employee.getEmail();
				employee.setEmail(newValue);
				data.addLogMessage("Email del empleado " + oldValue + " cambiado a " + newValue);
				break;



			case 3:
				// Modificar teléfono
				oldValue = employee.getPhone();
				employee.setPhone(newValue);
				data.addLogMessage("Teléfono del empleado " + oldValue + " cambiado a " + newValue);
				break;



			case 4:
				// Modificar fecha de nacimiento
				oldValue = employee.getBirth().toString();
				employee.setBirth(newValue);
				data.addLogMessage(
						"Fecha de nacimiento del empleado " + oldValue + " cambiada a " + newValue);
				break;



			case 5:
				// Modificar área de trabajo
				oldValue = employee.getArea();
				employee.setArea(newValue);
				data.addLogMessage("Área de trabajo del empleado " + oldValue + " cambiada a " + newValue);
				break;



			case 6:
				// Modificar nickname
				oldValue = employee.getNickname();
				employee.setNickname(newValue);
				data.addLogMessage("Nickname del empleado " + oldValue + " cambiado a " + newValue);
				break;



			case 7:
				// Modificar contraseña
				oldValue = employee.getESC();
				employee.setESC(newValue);
				data.addLogMessage("Contraseña del empleado cambiada.");
				break;



			default:
				System.out.println("Opción no válida");
		}

	}


	@Override
	public int compareTo(Manager otherUser)
	{
		return this.getName().compareTo(otherUser.getName());
	}

	public static Manager loginManager(ArrayList<Manager> managerList)
	{
		System.out.println("\n\n" + StaticData.BARRA + " Inicio de sesión del Manager " + StaticData.BARRA + "\n\n");
		System.out.println("Introduzca su nombre de usuario (Nickname): ");
		String nickname = Entrada.cadena();
		System.out.println("\nAhora escriba su contraseña: ");
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
