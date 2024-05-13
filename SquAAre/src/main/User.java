
package main;

import java.util.*;

import com.comun.*;

public class User extends Person implements Comparable<User>
{

	private static final long serialVersionUID = 2L;
	private String acountCode = "";
	private String pathId = "";
	public String nickname;

	public User()
	{

		super();

	}

	public static User newUser()
	{

		Person aux = Person.newPerson();
		Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrasenya: ");
		String dSC = Entrada.cadena();
		String eSC = encrypt(dSC);
		dSC = "";
		String pathId = StaticData.DIRECTORY_INFORM + aux.surname + aux.name + ".csv";
		String aux1 = "";

		Random r = new Random();

		for (int i = 0; i < 0; i++)
		{
			aux1 += r.nextInt() + "";
		}

		String acountCode = aux.name + nickname + aux1;
		return new User(aux, acountCode, pathId, nickname, eSC);
	}

	public User(Person data, String acountCode, String pathId, String nickname, String eSC)
	{
		this.name = data.name;
		this.surname = data.surname;
		this.email = data.email;
		this.phone = data.phone;
		this.acountCode = acountCode;
		this.pathId = pathId;
		this.nickname = nickname;
		super.eSC = eSC;
		super.dSC = "";

	}

	public static User newLoginUser()
	{

		Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		String nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrasenya: ");
		String dSC = Entrada.cadena();
		String eSC = encrypt(dSC);
		dSC = "";

		return new User(nickname, eSC);
	}

	public User(String nickname, String eSC)
	{

		this.nickname = nickname;
		this.dSC = "";
		this.eSC = eSC;
	}

	public User(String name, String surname, String email, String phone, String birth, String acountCode, String pathId,
			String nickname)
	{
		super(name, surname, email, phone, birth);
		this.acountCode = acountCode;
		this.pathId = pathId;
		this.nickname = nickname;
	}

	@Override
	public String toString()
	{
		return "User [acountCode=" + acountCode + ", pathId=" + pathId + ", nickname=" + nickname + ", toString()="
				+ super.toString() + "]";
	}

	@Override

	public int compareTo(User otherUser)
	{
		return this.nickname.compareTo(otherUser.nickname);
	}

	@Override
	public boolean equals(Object obj)
	{

		if (this == obj)
		{
			return true;
		}

		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}

		User otherUser = (User) obj;
		return Objects.equals(nickname, otherUser.nickname) && Objects.equals(eSC, otherUser.eSC);
	}

	public String getAcountCode()
	{
		return acountCode;
	}

	public String getPathId()
	{
		return pathId;
	}

}
