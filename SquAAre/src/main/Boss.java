
package main;

import com.comun.Entrada;

public class Boss extends Person implements Comparable<Boss>
{

	private static final long serialVersionUID = 3L;


	public Boss(String name, String surname, String email, String phone, String birth, String area)
	{
		super(name, surname, email, phone, birth, area);
	}




	public Boss()
	{
		// TODO Auto-generated constructor stub
	}




	public void createManager(StaticData data)
	{
		System.out.println("Introduce los datos del nuevo Manager:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Teléfono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("Área de trabajo: ");
		String area = Entrada.cadena();
		System.out.print("Nickname: ");
		String nickname = Entrada.cadena();
		System.out.print("Contraseña: ");
		String password = Entrada.cadena();
		Manager manager = new Manager(name, surname, email, phone, birth, area);
		manager.nickname = nickname;
		String eSC = Person.encrypt(password);
		manager.eSC = eSC;
		data.savedManager.add(manager);
		data.addLogMessage(
				"Se ha creado un nuevo Manager: " + manager.getName() + " " + manager.getSurname());
	}




	public void createEmployee(StaticData data)
	{
		System.out.println("Introduce los datos del nuevo Employee:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Teléfono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("Área de trabajo: ");
		String area = Entrada.cadena();

		Employee employee = new Employee(name, surname, email, phone, birth, area);
		data.savedEmployee.add(employee);
		data.addLogMessage("Se ha creado un nuevo Employee: " + employee.getName());
	}




	public void modifyManager(StaticData data, Manager manager, int option, String newValue)
	{
		String oldValue = "";


		switch (option)
		{
			case 0:
				// Modificar nombre
				oldValue = manager.getName();
				manager.setName(newValue);
				data.addLogMessage("Nombre del manager " + oldValue + " cambiado a " + newValue);
				break;



			case 1:
				// Modificar apellido
				oldValue = manager.getSurname();
				manager.setSurname(newValue);
				data.addLogMessage("Apellido del manager " + oldValue + " cambiado a " + newValue);
				break;



			case 2:
				// Modificar email
				oldValue = manager.getEmail();
				manager.setEmail(newValue);
				data.addLogMessage("Email del manager " + oldValue + " cambiado a " + newValue);
				break;



			case 3:
				// Modificar teléfono
				oldValue = manager.getPhone();
				manager.setPhone(newValue);
				data.addLogMessage("Teléfono del manager " + oldValue + " cambiado a " + newValue);
				break;



			case 4:
				// Modificar fecha de nacimiento
				oldValue = manager.getBirth().toString();
				manager.setBirth(newValue);
				data.addLogMessage("Fecha de nacimiento del manager " + oldValue + " cambiada a " + newValue);
				break;



			case 5:
				// Modificar área de trabajo
				oldValue = manager.getArea();
				manager.setArea(newValue);
				data.addLogMessage("Área de trabajo del manager " + oldValue + " cambiada a " + newValue);
				break;



			case 6:
				// Modificar nickname
				oldValue = manager.getNickname();
				manager.setNickname(newValue);
				data.addLogMessage("Nickname del manager " + oldValue + " cambiado a " + newValue);
				break;



			case 7:
				// Modificar contraseña
				oldValue = manager.getESC();
				manager.setESC(newValue);
				data.addLogMessage("Contraseña del manager cambiada.");
				break;



			default:
				System.out.println("Opción no válida");
		}

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
		data.addLogMessage("Se ha modificado el Employee: " + employee.getName());
	}




	@Override
	public int compareTo(Boss otherUser)
	{
		return this.getName().compareTo(otherUser.getName());
	}
	public static Boss createBoss() {
		System.out.println("Introduce los datos del nuevo Boss:");
		System.out.print("Nombre: ");
		String name = Entrada.cadena();
		System.out.print("Apellido: ");
		String surname = Entrada.cadena();
		System.out.print("Email: ");
		String email = Entrada.cadena();
		System.out.print("Teléfono: ");
		String phone = Entrada.cadena();
		System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
		String birth = Entrada.cadena();
		System.out.print("Área de trabajo: ");
		String area = Entrada.cadena();
		System.out.print("Nickname: ");
		String nickname = Entrada.cadena();
		System.out.print("Contraseña: ");
		String password = Entrada.cadena();

		Boss newBoss = new Boss();
		newBoss.setName(name);
		newBoss.setSurname(surname);
		newBoss.setEmail(email);
		newBoss.setPhone(phone);
		newBoss.setBirth(birth);
		newBoss.setArea(area);
		newBoss.setNickname(nickname);
		String eSC = Person.encrypt(password);
		newBoss.eSC = eSC;

		
		System.out.println("\n*** Nuevo Boss creado exitosamente ***\n");

		return newBoss;
	}

}
