package rms;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadClass {
	XSSFWorkbook work_book;
	XSSFSheet sheet;

	public ReadClass(String locationofExcelFile) {
		try {
			File s = new File(locationofExcelFile);
			FileInputStream stream = new FileInputStream(s);
			work_book = new XSSFWorkbook(stream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getRowCount(int sheetIndex) {
		int row = work_book.getSheetAt(sheetIndex).getLastRowNum();
		row = row + 1;
		return row;
	}

	public String getData(int sheetNumber, int row, int column) {
		sheet = work_book.getSheetAt(sheetNumber);
		String data = null;
		sheet.getRow(row).getCell(column).setCellType(CellType.STRING);
		data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
}
