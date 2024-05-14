// Modificación de la clase Employee

package main;

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
		Printer.print("\nAhora escriba su contraseña: ");
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

}
