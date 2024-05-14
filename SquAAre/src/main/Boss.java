
package main;

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




	public void createManager(StaticData data, String name, String surname, String email, String phone,
			String birth, String area)
	{
		Manager manager = new Manager(name, surname, email, phone, birth, area);
		data.savedManager.add(manager);
		data.addLogMessage("Se ha creado un nuevo Manager: " + manager.getName());
	}




	public void createEmployee(StaticData data, String name, String surname, String email, String phone,
			String birth, String area)
	{
		Employee employee = new Employee(name, surname, email, phone, birth, area);
		data.savedEmployee.add(employee);
		data.addLogMessage("Se ha creado un nuevo Employee: " + employee.getName());
	}




	public void modifyManager(StaticData data, Manager manager, String name, String surname, String email,
			String phone, String birth, String area)
	{
		manager.setName(name);
		manager.setSurname(surname);
		manager.setEmail(email);
		manager.setPhone(phone);
		manager.setBirth(birth);
		manager.setArea(area);
		data.addLogMessage("Se ha modificado el Manager: " + manager.getName());
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

}
