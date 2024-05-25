
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PruebaHojaCalculo {

static public void main(String[] args) {

HSSFWorkbook workbook = new HSSFWorkbook();
HSSFSheet sheet = workbook.createSheet("Reporte de productos");

Row fila = sheet.createRow(0);
File archivo = new File("ejemplo.xls");
Cell celda;

String[] titulos = { "id", "Cantidad", "Consumo", "Precio compra", "Precio Venta" };
Double[] datos = { 1.0, 10.0, 45.0, 14.50, 30.50 };

int i;

// Creamos el encabezado

for (i = 0; i < titulos.length; i++) {
      celda = fila.createCell(i);
      celda.setCellValue(titulos[i]);
}

// Nueva fila

fila = sheet.createRow(1);

for (i = 0; i < datos.length; i++) {
      celda = fila.createCell(i);
      celda.setCellValue(datos[i]);
}

// Escribimos el archivo
try {
      FileOutputStream out = new FileOutputStream(archivo);
      workbook.write(out);
      out.close();

      System.out.println("Archivo creado exitosamente!");

} catch (IOException e) {
      System.out.println("Error de escritura");
e.printStackTrace();
}
}
}