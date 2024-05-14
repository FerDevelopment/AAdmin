package main;

import java.util.ArrayList;

public class Manager extends Person implements Comparable<Manager> {

    private static final long serialVersionUID = 4L;

    public Manager(String name, String surname, String email, String phone, String birth, String area) {
        super(name, surname, email, phone, birth, area);
    }

    public void createEmployee(StaticData data, String name, String surname, String email, String phone, String birth, String area) {
        Employee employee = new Employee(name, surname, email, phone, birth, area);
        data.addEmployee(employee);
        data.addLogMessage("Se ha creado un nuevo Employee: " + name);
    }

    public void modifyEmployee(StaticData data, Employee employee, String name, String surname, String email, String phone, String birth, String area) {
        employee.setName(name);
        employee.setSurname(surname);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setBirth(birth);
        employee.setArea(area);
        data.addLogMessage("Se ha modificado el Employee: " + name);
    }

    @Override
    public int compareTo(Manager otherUser) {
        return this.getName().compareTo(otherUser.getName());
    }
}
