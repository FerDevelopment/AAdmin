// Modificación de la clase Employee

package main;

import java.awt.Desktop;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.comun.*;

public class Employee extends Person
{

	private static final long serialVersionUID = 2L;
	private String acountCode;
	private String pathId;
	private ArrayList<String> ventas = new ArrayList<>();

	public Employee(String name, String surname, String email, String phone, String birth, String area,
			String acountCode, String pathId, String nickname, String eSC)
	{
		super(name, surname, email, phone, birth, area);
		this.acountCode = acountCode;
		this.pathId = pathId;
		this.nickname = nickname;
		this.eSC = eSC;
		try
		{
			File file = new File(this.pathId);
			if (!file.exists())
			{
				file.createNewFile();
			}
		} catch (Exception e)
		{
			System.out.println("Archivo path no creado");
		}
	}

	public Employee(String name)
	{
		super();
		String pathId = StaticData.DIRECTORY_INFORM + name + name;
		String aux1 = "";

		Random r = new Random();

		for (int i = 0; i < 0; i++)
		{
			aux1 += r.nextInt() + "";
		}
		this.nickname = String.valueOf(name.charAt(0)) +String.valueOf(name.charAt(1))+ String.valueOf(name.charAt(name.length() - 2))+ String.valueOf(name.charAt(name.length() - 1));
		String acountCode = name + nickname + aux1;
		this.pathId = pathId + StaticData.EXTEN;
		this.acountCode = acountCode;

		this.eSC = Person.encrypt(name);

		try
		{
			createDocument();
		} catch (Exception e)
		{
			System.out.println("Archivo path no creado");
		}
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
		return new Employee(aux.getName(), aux.getSurname(), aux.getEmail(), aux.getPhone(), aux.getBirth().toString(),
				aux.getArea(), acountCode, pathId, nickname, eSC);
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
		return "Employee [acountCode=" + acountCode + ", pathId=" + pathId + ", nickname=" + nickname + ", toString()="
				+ super.toString() + "]";
	}

	public void modifyProduct(ArrayList<Product> products)
	{
		Printer.print(products);
		System.out.print("\n\nIntroduzca el nombre del producto a modificar:\n-->");
		String name = Entrada.cadena();
		Product product = new Product(name);
		Integer a = Collections.binarySearch(products, product);
		if (a >= 0)
		{
			Product mod = products.get(a);
			String newValue = "";
			String oldValue = "";
			Integer option = 1;
			System.out.print("Que quiere modificar?");
			System.out.print("\n0.Salir");
			System.out.print("\n1.Nombre");
			System.out.print("\n2.Precio");
			System.out.println("\n3.Cantidad");
			while (option != 0)
			{
				option = Entrada.entero();
				switch (option)
				{
					case 0:
					{
						System.out.println("Chaoo");
						break;
					}
					case 1:
					{
						System.out.print("\nIntroduzca el nombre\n-->");
						newValue = Entrada.cadena();
						oldValue = mod.getName();
						mod.setNombre(newValue);
						this.logMessages.add("Nombre cambiado de " + oldValue + " a " + newValue);
						break;
					}
					case 2:
					{
						System.out.print("\nIntroduzca el Precio\n-->");
						Double New = Entrada.real();
						oldValue = mod.getPrecio().toString();
						mod.setPrecio(New);
						this.logMessages.add("Precio cambiado de " + oldValue + " a " + New);

						break;
					}
					case 3:
					{
						System.out.print("\nIntroduzca la Cantidad\n-->");
						Integer New = Entrada.entero();
						oldValue = mod.getCantidad().toString();
						mod.setCantidad(New);
						this.logMessages.add("Cantidad cambiada de " + oldValue + " a " + New);

						break;
					}

					default:
						System.out.println("Opción no valida");
				}
			}

		}
		else
		{
			System.out.println("Producto no encontrado");
		}
	}

	public void sendProducts(ArrayList<Product> productos)
	{
		System.out.println("Productos disponibls:\n");
		viewProduct(productos);
		String name = "";
		while (!name.equalsIgnoreCase("listo"))
		{
			System.out.println("\nIntroduzca el producto a enviar (cuando termine introduzca \"listo\")");
			name = Entrada.cadena();
			if (!name.equalsIgnoreCase("listo"))
			{
				Product aux = new Product(name);
				Integer loc = Collections.binarySearch(productos, aux);
				if (loc >= 0)
				{
					aux = productos.get(loc);
					System.out.print("Cuanto quiere? (Disponible: " + aux.getCantidad() + ")\n-->");
					Integer cant = Entrada.entero();
					if (cant > aux.getCantidad())
					{
						System.out.println("Cantidad no dispobible");
					}
					else
					{
						Double priceSell = cant * aux.getPrecio();
						aux.setCantidad(aux.getCantidad() - cant);
						this.ventas.add(LocalDate.now().toString() + ";" + LocalTime.now().toString() + ";"
								+ "Usted vendió " + cant + " " + aux.getName() + " por un precio de " + priceSell);
						System.out.println("***Vendido***");
					}

				}
				else
				{
					System.out.println("\n***Producto no encontrado***\n");
				}
			}
		}
	}

	public void createPersonalizedReport()
	{

		System.out.println("Creando un reporte personalizado...\n-->");
		String aux = Entrada.cadena();
		this.logMessages.add("Personalizado " + aux);

	}

	public void createSalesReport()
	{

		System.out.println("Reporte guardado: \n\n");
		if (this.ventas.size() >= 1)
		{
			for (String sms : this.ventas)
			{
				System.out.println(sms);
			}
		}
		else
		{
			System.out.println("Usted no ha realizado ninguna venta. Casi cerca del despido, espabile");
		}

	}

	public void viewProduct(ArrayList<Product> productos)
	{
		// Implementar la lógica para ver la cantidad de productos
		System.out.println("Viendo la cantidad de productos...");
		Printer.print(productos);
		this.logMessages.add("Empleado " + nickname + " vio la cantidad de productos.");
		// Lógica específica para ver las cantidades de los productos
	}

	public void createDocument()
	{
		File archivo = new File(this.pathId);
		String[] columna =
		{ "Fecha", "Hora", "Mensaje" };
		Workbook libro;
		Sheet hoja;
		try
		{
			libro = new XSSFWorkbook();
			hoja = libro.createSheet("Notas");

			// Crear la cabecera
			Row fila = hoja.createRow(0);
			Cell celda;
			CellStyle style = libro.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			for (int i = 0; i < columna.length; i++)
			{
				celda = fila.createCell(i);
				celda.setCellStyle(style);
				celda.setCellValue(columna[i]);
			}
			FileOutputStream salida = new FileOutputStream(archivo);
			libro.write(salida);
			salida.close();
			libro.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
	}

	public void saveDocument()
	{

		File archivo = new File(this.pathId);

		Workbook libro;
		Sheet hoja;
		int ultimaFila = 0;

		if (!archivo.exists())
		{
			createDocument();
		}

		try
		{
			InputStream entrada = new FileInputStream(archivo);
			libro = new XSSFWorkbook(entrada);
			hoja = libro.getSheet("Notas");

			// Obtener la última fila existente
			ultimaFila = hoja.getPhysicalNumberOfRows();
			entrada.close();
		} catch (IOException e)
		{
			e.printStackTrace();
			return;
		}

		// Agregar nuevas filas al final del archivo
		for (int i = 0; i < this.logMessages.size(); i++)
		{
			Row fila = hoja.createRow(ultimaFila + i + 1);
			String aux = logMessages.get(i).salidaDocumento();
			StringTokenizer help = new StringTokenizer(aux, ";");
			int index = 0;

			while (help.hasMoreTokens())
			{
				Cell celda = fila.createCell(index);
				celda.setCellValue(help.nextToken());
				index++;
			}
		}

		// Guardar los cambios en el archivo
		try
		{
			FileOutputStream salida = new FileOutputStream(archivo);
			libro.write(salida);
			salida.close();
			libro.close();

			// Abre el archivo generado en el sistema predeterminado
			if (Desktop.isDesktopSupported())
			{
				Desktop.getDesktop().open(archivo);
			}
			else
			{
				System.out.println("El sistema no admite la apertura automática de archivos.");
			}
		} catch (Exception e)
		{
			System.out.println("\n\n***************Error de escritura***************\n\n");
			System.out.println("\n\n" + e);
		}

		saveSalesReport();
	}

	private void saveSalesReport()
	{
		String[] columna =
		{ "Fecha", "Hora", "Mensaje" };
		File archivo = new File(StaticData.DIRECTORY_PRODUCTINFORM + this.nickname + StaticData.EXTEN);

		Workbook libro;
		Sheet hoja;
		int ultimaFila = 0;

		if (!archivo.exists())
		{
			try
			{
				archivo.createNewFile();
				libro = new HSSFWorkbook();
				hoja = libro.createSheet("Notas");

				// Crear la cabecera
				Row fila = hoja.createRow(0);
				Cell celda;
				CellStyle style = libro.createCellStyle();
				style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

				for (int i = 0; i < columna.length; i++)
				{
					celda = fila.createCell(i);
					celda.setCellStyle(style);
					celda.setCellValue(columna[i]);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
		else
		{
			try
			{
				FileInputStream entrada = new FileInputStream(archivo);
				libro = new HSSFWorkbook(entrada);
				hoja = libro.getSheet("Notas");

				// Obtener la última fila existente
				ultimaFila = hoja.getLastRowNum();
				entrada.close();
			} catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}

		// Agregar nuevas filas al final del archivo
		for (int i = 0; i < this.ventas.size(); i++)
		{
			Row fila = hoja.createRow(ultimaFila + i + 1);
			String aux = ventas.get(i);
			StringTokenizer help = new StringTokenizer(aux, ";");
			int index = 0;

			while (help.hasMoreTokens())
			{
				Cell celda = fila.createCell(index);
				celda.setCellValue(help.nextToken().toString());
				index++;
			}
		}

		// Guardar los cambios en el archivo
		try
		{
			FileOutputStream salida = new FileOutputStream(archivo);
			libro.write(salida);
			salida.close();
			libro.close();

			Desktop.getDesktop().open(new File(this.pathId));
		} catch (Exception e)
		{
			System.out.println("\n\n***************Error de escritura***************\n\n");
			System.out.println("\n\n" + e);
		}

	}
}
