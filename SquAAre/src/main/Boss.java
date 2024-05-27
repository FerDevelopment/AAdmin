
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
		String name = "";
		String surname = "";
		String email = "";
		String phone = "";
		String birth = "";
		String area = "";
		String emailPattern = ".*@.*[.][A-Za-z]{1,4}";
		String noNumPattern = "^[\\D]+$";
		String phonePattern = "^[0-9]{9}$";
		String birthPattern = "^[0-9]{4}-[0-9]{2}-[0-9]{2}";

		Printer.print("\n\n" + StaticData.BARRA + "Datos personales" + StaticData.BARRA + "\n\n");
		Printer.print("Introduzca su nombre: ");
		name = getText(name, noNumPattern);
		Printer.print("Introduzca sus apellidos: ");
		surname = getText(surname, noNumPattern);
		Printer.print("Introduzca su email: ");
		email = getText(email, emailPattern);
		Printer.print("Introduzca su número de teléfono: ");
		phone = getText(phone, phonePattern);
		Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
		birth = getText(birth, birthPattern);
		Printer.print("Introduzca su área de trabajo: ");
		area = getText(area, noNumPattern);

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
