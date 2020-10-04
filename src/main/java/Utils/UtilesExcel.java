/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Axel Alza
 */
import Modelo.Persona.Empleado;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class UtilesExcel {

    public static void readExcel() throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("C:/Users/Axel Alza/Desktop/data.xls"));
        HSSFSheet sheet = wb.getSheetAt(0);

        // Obtengo la cantidad de filas ocupadas 
        // de la planilla
        int rows = sheet.getLastRowNum();

        // Recorro las filas, comienzo por la 1
        // porque en la 0 estan los titulos
        for (int i = 1; i < rows; ++i) {

            // Creo una Fila para guardar el 
            // contenido obtenido
            HSSFRow row = sheet.getRow(i);

            // Creo celdas para asignar los
            // valores las celdas en la fila de la 0 a la 2
            HSSFCell productCell = row.getCell(0);
            HSSFCell priceCell = row.getCell(1);
            HSSFCell linkCell = row.getCell(2);

            // Asigno los valores de cada celda a variables
            String product = productCell.getStringCellValue();
            String price = priceCell.getStringCellValue();
            String link = linkCell.getStringCellValue();

            // Imprimo los valores
            System.out.printf("%s, %s, %s%n", product, price, link);
        }
    }

    public static void readCsv() throws Exception {

        // Levanto el archivo y guardo su contenido en
        // un CSVReader
        try ( CSVReader reader = new CSVReader(new FileReader("C:/Users/Axel Alza/Desktop/data.csv"))) {

            // Obtengo todas las lineas del CSV y las
            // guardo en un Array de String
            List<String[]> lines = reader.readAll();

            System.out.println();

            // Recorro todas las lineas del Array
            lines.forEach(d -> {

                // Por cada linea accedo a las posiciones
                // correspondientes y guardo el valor de cada
                // posicion en una variable
                String idempleado = d[0];
                String sueldoMens = d[1];
                String ced = d[2];

                // Imprimo 
                System.out.printf("%s, %s, %s%n", idempleado, sueldoMens, ced);
            });
        }
    }

    public static void writeExcel(ArrayList<Empleado> DATA) throws Exception {

        // Creo una planilla de trabajo
        HSSFWorkbook workbook = new HSSFWorkbook();

        // Dentro de la planilla, creo una hoja
        HSSFSheet sheet = workbook.createSheet();

        // A la hoja recien creada(0) le asigno
        // el nombre "Hoja excel"
        workbook.setSheetName(0, "Hoja excel");

        // Creo un array de String
        // para guardar los titulos o encabezados
        String[] headers = new String[]{
            "Id_Empleado",
            "Sueldo Mensual",
            "Cedula"
        };

        // Creo un estilo de encabezado para ir seteando valores
        // y despues asignarlo a los titulos
        CellStyle headerStyle = workbook.createCellStyle();

        // Creo una fuente
        HSSFFont font = workbook.createFont();
        // establezco que va a ser Negrita
        font.setBold(true);
        // Se la asigno al estilo creado
        headerStyle.setFont(font);

        // Creo un estilo para una celda
        CellStyle style = workbook.createCellStyle();
        // Establezco el Color del fondo de la celda
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        // Establezco el tipo de color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Creo la fila de titulos o encabezados en la fila 0
        HSSFRow headerRow = sheet.createRow(0);
        
        // Recorro el array de String con los titulos
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            // Voy creando celdas para cada uno
            HSSFCell cell = headerRow.createCell(i);
            // Les asigno el estilo creado previamente
            cell.setCellStyle(headerStyle);
            // Asigno el valor
            cell.setCellValue(header);
        }

        // Recorro el objeto DATA obtenido por parametro
        // DATA es un Array de Objetos del cual conozco 
        // la cantidad de columnas. La cantidad de registros
        // la obtengo con DATA.size()
        for (int i = 0; i < DATA.size(); ++i) {
            HSSFRow dataRow = sheet.createRow(i + 1);
            Empleado e = DATA.get(i);
            String idempleado = String.valueOf(e.getId_empleado());
            int sueldoMens = e.getSueldoMens();
            String ced = String.valueOf(e.getCedula());
            dataRow.createCell(0).setCellValue(idempleado);
            dataRow.createCell(1).setCellValue(sueldoMens);
            dataRow.createCell(2).setCellValue(ced);
        }

        HSSFRow dataRow = sheet.createRow(1 + DATA.size());
        HSSFCell total = dataRow.createCell(1);
        total.setCellType(CellType.NUMERIC);
        total.setCellStyle(style);
        total.setCellFormula(String.format("SUM(B2:B%d)", 1 + DATA.size()));

        try ( FileOutputStream file = new FileOutputStream("C:/Users/Axel Alza/Desktop/data.xls")) {
            workbook.write(file);
        }
    }

    public static void writeCsv(ArrayList<Empleado> DATA ) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter("C:/Users/Axel Alza/Desktop/data.csv"));
        System.out.println();
        DATA.forEach(e -> {
            String idempleado = String.valueOf(e.getId_empleado());
            String sueldoMens = String.valueOf(e.getSueldoMens());
            String ced = String.valueOf(e.getCedula());
            String[] line = new String[]{idempleado,sueldoMens,ced};
            writer.writeNext(line);
        });

        writer.close();
    }

}
