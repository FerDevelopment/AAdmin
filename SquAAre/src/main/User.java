
package main;

import java.util.*;

import com.comun.*;

public class User extends Person
{

	private static final long serialVersionUID = 2L;
	private String acountCode = "";
	private String pathId = "";
	public String nickname;


	public User()
	{

		super();

		Printer.print("\n\n" + StaticDate.BARRA + "Datos de usuarios" + StaticDate.BARRA + "\n\n");
		Printer.print("\nIntroduzca su NickName: ");
		nickname = Entrada.cadena();
		Printer.print("\nAhora escriba su contrasenya: ");
		this.dSC = Entrada.cadena();
		this.eSC = encrypt(this.dSC);
		this.dSC = "";
		this.pathId = StaticDate.DIRECTORY_INFORM + this.surname + this.name + ".csv";
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
		return "User [acountCode=" + acountCode + ", pathId=" + pathId + ", nickname=" + nickname
				+ ", toString()=" + super.toString() + "]";
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
