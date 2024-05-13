
package main;

public class Creator extends Person implements Comparable<Creator>
{

	private static final long serialVersionUID = 3L;
	private String creatorCode = "";
	private String dmca = "";

	public Creator()
	{
		super();

	}

	public String cosas()
	{
		return creatorCode + dmca;
	}

	@Override

	public int compareTo(Creator otherUser)
	{
		return this.name.compareTo(otherUser.name);
	}

}
