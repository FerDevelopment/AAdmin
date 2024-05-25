
package main;

import java.time.LocalDate;

public class Manager extends Person
{

	private static final long serialVersionUID = 4L;

	public Manager(String name, String surname, String email, String phone, String birth, String area)
	{
		super(name, surname, email, phone, birth, area);
	}

	public Manager(String name, String surname, String email, String phone, LocalDate birth, String area,
			String nickname, String eSC)
	{
		super(name, surname, email, phone, birth, area);
		this.nickname = nickname;
		this.eSC = Person.encrypt(eSC);
	}


}
