package Utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class ExcelUtils {
    private static Workbook workbook;

    // Load Excel file
    public static void loadExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading Excel file: " + filePath, e);
        }
    }

    // Get cell data (String)
    public static String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row rowObj = sheet.getRow(row);
        Cell cell = rowObj.getCell(col);

        if (cell == null) {
            return "";
        }

        cell.setCellType(CellType.STRING); // force everything as String
        return cell.getStringCellValue();
    }

    // Close workbook
    public static void closeExcel() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
