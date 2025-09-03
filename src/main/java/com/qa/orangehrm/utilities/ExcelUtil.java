package com.qa.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {
	public static final String TEST_DATA_SHEET_PATH= "./src/test/resources/testdata/"; 
	public static Workbook workbook;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String fileName, String sheetName) {
		Object[][] data=null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH+fileName+".xlsx");
			workbook=WorkbookFactory.create(ip);
			sheet=workbook.getSheet(sheetName);
			data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int row=0; row<sheet.getLastRowNum();row++) {
				for(int col=0;col<sheet.getRow(0).getLastCellNum();col++) {
					data[row][col]=sheet.getRow(row+1).getCell(col).toString();
				}
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return data;
	}

}
