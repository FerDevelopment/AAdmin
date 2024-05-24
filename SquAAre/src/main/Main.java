
package main;

import java.util.ArrayList;

import com.comun.*;

public class Main {

	private static StaticData data;
	private static OutTextRun text;
	private static Boss actualBoss;
	private static Manager actualManager;
	private static Employee actualEmployee;

	public static void main(String[] args) {
		data = new StaticData();
		first();
		data.end();
	}

	private static void createEmployee(Person currentManager) {
		Employee newEmployee = Employee.newEmployee();

		if (newEmployee != null) {
			data.savedEmployee.add(newEmployee);
			System.out.println("\n*** Nuevo empleado creado exitosamente ***\n");
			data.addLogMessage("El manager " + currentManager.getName() + " " + currentManager.getSurname()
					+ " ha creado al empleado " + newEmployee.getName() + " " + newEmployee.getSurname());
		} else {
			System.out.println("\n*** Error al crear el nuevo empleado ***\n");
		}

	}

	private static void first() {
		System.out.println(StaticData.BARRA + " ¿Qué tipo de usuario eres? " + StaticData.BARRA);
		System.out.println("1. Boss");
		System.out.println("2. Manager");
		System.out.print("3. Employee");
		System.out.print("4. ComeBackHome\n-->");
		int userType = Entrada.entero();

		if (userType == 1234) {
			data.savedBoss.add(Boss.createBoss());

		} else {

			switch (userType) {
			case 1:
				startServer("boss");
				startBossSession();
				break;

			case 2:
				startServer("manager");
				startManagerSession();
				break;

			case 3:
				startServer("employee");
				startEmployeeSession();
				break;

			case 4:
				data.end();
				break;
			default:
				System.out.println("***Opción no válida***");
				first();
				break;
			}

		}

	}

	private static Person getPersonByName(ArrayList<? extends Person> saved, String name, String surname) {

		for (int i = 0; i < saved.size(); i++) {
			Person manager = (Person) saved.get(i);

			try {

				if (manager.getName().equalsIgnoreCase(name) && manager.getSurname().equalsIgnoreCase(surname)) {
					return manager;
				}

			} catch (Exception e) {
				System.out.println("No se enviaron manager");
			}

		}

		return null;
	}

	private static void managerMenu(Manager currentManager) {
		int option = 0;

		do {
			System.out.println(StaticData.BARRA + " Menú del Manager " + StaticData.BARRA);
			System.out.println("\n¿Qué desea hacer, " + currentManager.getName() + "?");
			System.out.println("1. Crear nuevo empleado");
			System.out.println("2. Modificar empleado existente");
			System.out.println("3. Ver informe de actividades");
			System.out.println("4. Cerrar sesión");

			option = Entrada.entero();

			switch (option) {
			case 1:
				createEmployee(currentManager);
				break;

			case 2:
				modifyEmployee(currentManager);
				break;

			case 3:
				currentManager.viewActivityReport();
				break;

			case 4:
				System.out.println("\nCerrando sesión del Manager...");
				data.end();
				return;

			default:
				System.out.println("Opción no válida");
				break;
			}

		} while (option != 4);

	}

	private static void modifyEmployee(Person subBoss) {
		System.out.println("Introduce el nombre del Employee que deseas modificar:");
		String name = Entrada.cadena();
		System.out.println("Introduce el apellido del Employee que deseas modificar:");
		String surname1 = Entrada.cadena();
		Employee employee = (Employee) getPersonByName(data.savedEmployee, name, surname1);

		if (employee != null) {
			Person.modifyEmployee(data, employee, subBoss);
		} else {
			System.out.println("Employee no encontrado.");
		}

	}

	private static void modifyManager() {
		System.out.println("Introduce el nombre del Manager que deseas modificar:");
		String name = Entrada.cadena();
		System.out.println("Introduce el apellido del Manager que deseas modificar:");
		String surname1 = Entrada.cadena();
		Manager manager = (Manager) getPersonByName(data.savedManager, name, surname1);

		if (manager != null) {
			System.out.println(
					"\nQué dato desea modificar?\n->1.Nombre y apellidos\n->2.Email\n->3.Telefono\n->4.Fecha de nacimiento\n->5.Area de trabajao\n->6.Nickname\n->7.Contraseña");
			Integer option = Entrada.entero();

			switch (option) {

			case 1: {
				System.out.print("Nombre: ");
				String newName = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 0, newName);
				System.out.print("Apellido: ");
				String surname = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 1, surname);
			}

			case 2: {
				System.out.print("Email: ");
				String email = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 2, email);
			}

			case 3: {
				System.out.print("Teléfono: ");
				String phone = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 3, phone);
			}

			case 4: {
				System.out.print("Fecha de nacimiento (aaaa-mm-dd): ");
				String birth = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 4, birth);
			}

			case 5: {
				System.out.print("Área de trabajo: ");
				String area = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 5, area);
			}

			case 6: {
				System.out.println("Nickname: ");
				String nickname = Entrada.cadena();
				actualBoss.modifyManager(data, manager, 3, nickname);
			}

			case 7: {
				System.out.println("Contrasenya: ");
				String contrasenya = Entrada.cadena();
				contrasenya = Person.encrypt(contrasenya);
				actualBoss.modifyManager(data, manager, 7, contrasenya);
			}

			}

			System.out.println("Manager modificado correctamente.");
		}

	}

	private static void startBossSession() {
		actualBoss = (Boss) Person.login(data.savedBoss);
		int option;

		do {
			System.out.println(StaticData.BARRA + " Boss " + StaticData.BARRA);
			System.out.println("\n¿Qué deseas hacer?");
			System.out.println("1. Crear Manager");
			System.out.println("2. Crear Employee");
			System.out.println("3. Modificar Manager");
			System.out.println("4. Modificar Employee");
			System.out.println("5. Ver informe de actividad");
			System.out.println("6. Salir");
			option = Entrada.entero();

			switch (option) {
			case 1:
				actualBoss.createManager(data);
				break;

			case 2:
				createEmployee(actualBoss);
				break;

			case 3:
				modifyManager();
				break;

			case 4:
				modifyEmployee(actualBoss);
				break;

			case 5:
				actualBoss.viewActivityReport();
				break;

			case 6:

				first();
				break;

			default:
				System.out.println("Opción no válida");
				break;
			}

		} while (option != 6);

	}

	private static void startEmployeeSession() {
		actualEmployee = (Employee) Person.login(data.savedEmployee);
		if(actualEmployee!=null) {
			
	    int option = 0;
	    do {
	        System.out.println("\n\n" + StaticData.BARRA + " Menú de Empleado " + StaticData.BARRA + "\n\n");
	        System.out.println("1. Realizar un reporte personalizado");
	        System.out.println("2. Realizar un reporte de ventas");
	        System.out.println("3. Enviar productos");
	        System.out.println("4. Ver la cantidad de productos");
	        System.out.println("5. Modificar algún producto y su cantidad");
	        System.out.println("6. Cerrar sesión");

	        option = Entrada.entero();

	        switch (option) {
	            case 1:
	            	actualEmployee.createPersonalizedReport();
	                break;
	            case 2:
	            	actualEmployee.createSalesReport();
	                break;
	            case 3:
	            	actualEmployee.sendProducts(data.savedProducts);
	                break;
	            case 4:
	            	actualEmployee.viewProductQuantities();
	                break;
	            case 5:
	            	actualEmployee.modifyProduct(data.savedProducts);
	                break;
	            case 6:
	                System.out.println("\nCerrando sesión...\n");
	                break;
	            default:
	                System.out.println("\nOpción no válida. Intente de nuevo.\n");
	                break;
	        }
	    } while (option != 6);
	}
	}

	private static void startManagerSession() {

		int option = 0;

		do {
			System.out.println(StaticData.BARRA + " Manager " + StaticData.BARRA);
			System.out.println("\n¿Qué desea hacer?");
			System.out.println("1. Iniciar sesión");
			System.out.println("2. Volver al menú de inicio");

			option = Entrada.entero();

			switch (option) {
			case 1:
				actualManager = Manager.loginManager(data.savedManager);
				if (actualManager != null) {
					managerMenu(actualManager);
				}
				break;

			case 2:
				first();
				break;

			default:
				System.out.println("Opción no válida");
				break;
			}

		} while (option < 1 || option > 2);

	}

	private static void startServer(String option) {
		text = new OutTextRun();
		Thread hilo1 = new Thread(text);
		StaticData.cls();

		hilo1.start();

		Thread hilo2 = new Thread(new Runnable() {

			public void run() {
				StaticData.cls();

				if (option.equals("boss")) {
					data.getBoss();
					data.getManagers();
					data.getEmployee();
				} else if (option.equals("manager")) {
					data.getManagers();
					data.getEmployee();
				} else if (option.equals("employee")) {
					data.getEmployee();
					data.getProducts();
				}

			}

		});

		hilo2.start();

		try {
			hilo2.join();
		} catch (Exception e) {
			System.out.println("XD malito");
		}

		text.stop();

		try {
			hilo1.join();
			StaticData.cls();
			data.pause(2);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nSevidor iniciado");

		try {
			data.pause(2);
		} catch (Exception e) {
			System.out.println("Pasusa malita");
		}

		StaticData.cls();
	}

}
