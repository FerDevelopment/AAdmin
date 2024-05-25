
package main;

import com.comun.Entrada;
import com.comun.Printer;

public class Boss extends Person
{

	private static final long serialVersionUID = 3L;

	public Boss(String name, String surname, String email, String phone, String birth, String area)
	{
		super(name, surname, email, phone, birth, area);
	}

	public Boss()
	{

	}

	public void createManager(StaticData data)
	{

		Person aux = Person.newPerson();
		Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contraseña: ");
		String dSC = Entrada.cadena();
		String eSC = Person.encrypt(dSC);
		dSC = "";

		Manager manager = new Manager(aux.getName(), aux.getSurname(), aux.getEmail(), aux.getPhone(), aux.getBirth(),
				aux.getArea(), nickname, eSC);

		data.savedManager.add(manager);
		data.addLogMessage("Se ha creado un nuevo Manager: " + manager.getName() + " " + manager.getSurname());
		this.logMessages.add("Se ha creado un nuevo Manager: " + manager.getName() + " " + manager.getSurname());
	}

	public static Boss createBoss()
	{
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
