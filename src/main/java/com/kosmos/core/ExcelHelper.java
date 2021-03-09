package com.kosmos.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kosmos.core.PropertyReader;

public class ExcelHelper extends FileHandler {
	private PropertyReader configFile = null;
	private String fileName, sheetName;
	private HashMap<String, Integer> colHMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> rowHMap = new HashMap<String, Integer>();
	private Workbook excelWorkBook = null;
	private Sheet excelSheet = null;
	private String excelFileName = null;
	private String excelSheetName = null;
	private String testDataDir = null;
	ArrayList<String> cellValues = new ArrayList<String>();

	public ExcelHelper(Object obj) {
		configFile = new PropertyReader("config.properties");
		testDataDir = configFile.getPropertyValue("TEST_DATA_DIR");
		excelFileName = obj.getClass().getSimpleName() + ".xlsx";
		excelSheetName = configFile.getPropertyValue("SHEET_NAME");
		this.fileName = excelFileName;
		this.sheetName = excelSheetName;
		this.excelPreProcessor();
	}

	public void excelPreProcessor(){

		// Create an object of File class to open xlsx file
		File excelFile = createFile(testDataDir + File.separator + fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class
			try {
				excelWorkBook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			try {
				excelWorkBook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Read sheet inside the workbook by its name
		excelSheet = excelWorkBook.getSheet(sheetName);

		// Find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum() + 1;

		Row row = excelSheet.getRow(0);
		for (int i = 1; i < row.getLastCellNum(); i++) {
			String firstRowCellValue = row.getCell(i).getStringCellValue();
			colHMap.put(firstRowCellValue, i);
		}

		for (int i = 1; i < rowCount; i++) {
			Row tempRow = excelSheet.getRow(i);
			String firstColCellValue = tempRow.getCell(0).getStringCellValue();
			rowHMap.put(firstColCellValue, i);
		}
	}

	public String getTestDataValue(String testCaseName, String colName) {
		// return corresponding cell value
		Row row = excelSheet.getRow(rowHMap.get(testCaseName));
		String testDataValue = row.getCell(colHMap.get(colName)).getStringCellValue();
		return testDataValue;
	}

	public int getTestDataIntegerValue(String testCaseName, String colName) {
		// return corresponding cell value
		Row row = excelSheet.getRow(rowHMap.get(testCaseName));
		Cell cell = row.getCell(colHMap.get(colName));
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		int testDataValue = (int) cell.getNumericCellValue();
		return testDataValue;
	}

	@Override
	public File createFile(String filePath) {
		// Create Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(configFile.getPropertyValue("SHEET_NAME"));
		Row row = sheet.createRow(0);
		Cell a0 = row.createCell(0);
		Cell a1 = row.createCell(1);
		a0.setCellValue("TEST_CASE_NAME");
		a1.setCellValue("DATA_1");
		// Create file system using specific name
		FileOutputStream out = null;
		File file = new File(formFilePath(filePath));

		if (!file.exists()) {
			try {
				out = new FileOutputStream(file);
				try {
					workbook.write(out);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(
					"Empty Excel file with the name of the test class has been created. Please enter the test data as per requirement to execute the related test scripts");
		} else
			System.out.println("File already exists.");

		return file;
	}

//	public static void main(String[] args) throws Exception {
//		ExcelHelper obj = new ExcelHelper();
//		System.out.println(obj.readExcel());
//	}
}