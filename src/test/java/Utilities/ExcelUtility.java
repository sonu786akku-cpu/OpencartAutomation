package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook Workbook;
	public XSSFSheet Sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		Workbook = new XSSFWorkbook(fi);
		Sheet = Workbook.getSheet(sheetName);

		int rowcount = Sheet.getLastRowNum() + 1; // FIXED

		Workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		Workbook = new XSSFWorkbook(fi);
		Sheet = Workbook.getSheet(sheetName);

		row = Sheet.getRow(rownum);
		if (row == null) {
			Workbook.close();
			fi.close();
			return 0; // FIXED (avoid NPE)
		}

		int cellCount = row.getLastCellNum(); // safe now

		Workbook.close();
		fi.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		Workbook = new XSSFWorkbook(fi);
		Sheet = Workbook.getSheet(sheetName);

		row = Sheet.getRow(rownum);
		if (row == null) {
			Workbook.close();
			fi.close();
			return ""; // FIXED
		}

		cell = row.getCell(colnum);
		if (cell == null) {
			Workbook.close();
			fi.close();
			return ""; // FIXED
		}

		String data;

		switch (cell.getCellType()) {
		case STRING:
			data = cell.getStringCellValue();
			break;
		case NUMERIC:
			data = String.valueOf(cell.getNumericCellValue());
			break;
		case BOOLEAN:
			data = String.valueOf(cell.getBooleanCellValue());
			break;
		case BLANK:
			data = "";
			break;
		default:
			data = "";
		}

		Workbook.close();
		fi.close();
		return data;
	}
}

