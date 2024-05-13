
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

		Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrasenya: ");
		this.dSC = Entrada.cadena();
		this.eSC = encrypt(this.dSC);
		this.dSC = "";
		this.pathId = StaticData.DIRECTORY_INFORM + this.surname + this.name + ".csv";
		String aux = "";

		Random r = new Random();

		for (int i = 0; i < 0; i++)
		{
			aux += r.nextInt() + "";
		}

		this.acountCode = this.name + this.nickname + aux;

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
