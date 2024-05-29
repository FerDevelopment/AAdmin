package main;

import com.comun.Entrada;
import com.comun.Printer;

/**
 * Clase que representa a un jefe dentro de la organización.
 */
public class Boss extends Person {

    /** SerialVersionUID para la serialización. */
    private static final long serialVersionUID = 3L;

    /**
     * Constructor de la clase Boss.
     * 
     * @param name   Nombre del jefe.
     * @param surname Apellido del jefe.
     * @param email  Correo electrónico del jefe.
     * @param phone  Número de teléfono del jefe.
     * @param birth  Fecha de nacimiento del jefe.
     * @param area   Área de trabajo del jefe.
     */
    public Boss(String name, String surname, String email, String phone, String birth, String area) {
        super(name, surname, email, phone, birth, area);
    }

    /**
     * Constructor por defecto de la clase Boss.
     */
    public Boss() {
    }

    /**
     * Método para crear un nuevo gerente y agregarlo a la lista de gerentes.
     * 
     * @param data Objeto StaticData que contiene los datos de la aplicación.
     */
    public void createManager(StaticData data) {
        /* Se solicitan los datos del nuevo gerente */
        Person aux = Person.newPerson();
        Printer.print("\n\n" + StaticData.BARRA + "Datos de usuarios" + StaticData.BARRA + "\n\n");
        Printer.print("\nIntroduzca su NickName: ");
        String nickname = Entrada.cadena();
        Printer.print("\nAhora escriba su contraseña: ");
        String dSC = Entrada.cadena();
        /* Se encripta la contraseña */
        String eSC = Person.encrypt(dSC);
        dSC = "";

        /* Se crea el objeto Manager con los datos proporcionados */
        Manager manager = new Manager(aux.getName(), aux.getSurname(), aux.getEmail(), aux.getPhone(), aux.getBirth(),
                aux.getArea(), nickname, eSC);

        /* Se agrega el gerente a la lista de gerentes guardados */
        data.savedManager.add(manager);
        /* Se registra un mensaje de log indicando la creación del nuevo gerente */
        data.addLogMessage("Se ha creado un nuevo Manager: " + manager.getName() + " " + manager.getSurname());
        this.logMessages.add("Se ha creado un nuevo Manager: " + manager.getName() + " " + manager.getSurname());
    }

    /**
     * Método estático para crear un nuevo jefe.
     * 
     * @return Instancia de Boss creada.
     */
    public static Boss createBoss() {
        /* Se solicitan los datos personales del nuevo jefe */
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
        /* Se solicita y valida el nombre */
        name = getText(name, noNumPattern);
        Printer.print("Introduzca sus apellidos: ");
        /* Se solicita y valida los apellidos */
        surname = getText(surname, noNumPattern);
        Printer.print("Introduzca su email: ");
        /* Se solicita y valida el email */
        email = getText(email, emailPattern);
        Printer.print("Introduzca su número de teléfono: ");
        /* Se solicita y valida el número de teléfono */
        phone = getText(phone, phonePattern);
        Printer.print("Introduzca su fecha de nacimiento (aaaa-mm-dd): ");
        /* Se solicita y valida la fecha de nacimiento */
        birth = getText(birth, birthPattern);
        Printer.print("Introduzca su área de trabajo: ");
        /* Se solicita y valida el área de trabajo */
        area = getText(area, noNumPattern);

        /* Se solicita y establece el nombre de usuario y la contraseña */
        System.out.print("Nickname: ");
        String nickname = Entrada.cadena();
        System.out.print("Contraseña: ");
        String password = Entrada.cadena();

        /* Se crea una nueva instancia de Boss con los datos proporcionados */
        Boss newBoss = new Boss();
        newBoss.setName(name);
        newBoss.setSurname(surname);
        newBoss.setEmail(email);
        newBoss.setPhone(phone);
        newBoss.setBirth(birth);
        newBoss.setArea(area);
        newBoss.setNickname(nickname);
        /* Se encripta la contraseña */
        String eSC = Person.encrypt(password);
        newBoss.eSC = eSC;

        System.out.println("\n*** Nuevo Boss creado exitosamente ***\n");

        return newBoss;
    }

}
