package main;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.comun.Printer;

public class Product implements Comparable<Product>, Serializable
{
	private static final long serialVersionUID = 9L;
	private String name;
	private Double price;
	private Integer quantity;

	public Product()
	{

	}

	public Product(String nombre, double precio, int cantidad)
	{
		this.name = nombre;
		this.price = precio;
		this.quantity = cantidad;
	}

	public Product(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNombre(String nombre)
	{
		this.name = nombre;
	}

	public Double getPrecio()
	{
		return price;
	}

	public void setPrecio(Double precio)
	{
		this.price = precio;
	}

	public Integer getCantidad()
	{
		return quantity;
	}

	public void setCantidad(Integer cantidad)
	{
		this.quantity = cantidad;
	}

	@Override
	public int compareTo(Product o)
	{
		return this.getName().compareTo(o.getName());
	}

	@Override
	public String toString()
	{
		return "Nombre:" + name + "| Precio: " + price + "€| Cantidad: " + quantity;
	}

	public String toDocument()
	{
		return name + ";" + price + ";" + quantity;
	}

	public static void savedProduct(ArrayList<Product> savedProducts)
	{

		String[] columna =
		{ "Nombre", "Precio", "Cantidad" };
		File archivo = new File(StaticData.LOCALPRODUCTSEXCEL);
		if (!archivo.exists())
		{
			try
			{
				archivo.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		Workbook libro = new HSSFWorkbook();
		Sheet hoja = libro.createSheet("Notas");
		Row fila = hoja.createRow(0);
		Cell celda;
		CellStyle style = libro.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Ponemos la cabecera con un estilo */
		for (Integer i = 0; i < columna.length; i++)
		{
			celda = fila.createCell(i);
			celda.setCellStyle(style);
			celda.setCellValue(columna[i]);
		}

		for (Integer i = 0; i < savedProducts.size(); i++)
		{
			fila = hoja.createRow(i + 1);
			String aux = savedProducts.get(i).toDocument();
			StringTokenizer help = new StringTokenizer(aux, ";");
			Integer index = 0;

			while (help.hasMoreTokens())
			{
				celda = fila.createCell(index);
				celda.setCellValue(help.nextToken());
				index++;
			}

		}

		try
		{

			FileOutputStream salida = new FileOutputStream(archivo);
			libro.write(salida);
			salida.close();
			libro.close();

			Desktop.getDesktop().open(new File(StaticData.LOCALPRODUCTSEXCEL));

		} catch (Exception e)
		{
			Printer.print("\n\n***************Error de escritura***************\n\n");
			Printer.print("\n\n" + e);
		}

	}

}
