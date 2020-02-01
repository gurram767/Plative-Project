package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataProvider {
	
	static XSSFWorkbook workbook;
	static FileInputStream file;
	static XSSFSheet sheet;
	static XSSFCell cell;
	 
	public static Object[][] excelReader(String Execlfilepath, String sheetName) throws Exception{
		
		try {
			
			 file = new FileInputStream(new File(Execlfilepath));
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			
		 workbook = new XSSFWorkbook(file);
		 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet =workbook.getSheet(sheetName);
		System.out.println("sheet name" +sheet);
		
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++){			
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
				//data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				DataFormatter formatter = new DataFormatter();
				data[i][j] = formatter.formatCellValue(sheet.getRow(i+1).getCell(j));
				System.out.println(data[i][j]);
			}
		}
		return data;
	}
	
	public static void excelUpdate(String Execlfilepath, String sheetName) throws Exception{
		try {
			
			 file = new FileInputStream(new File(Execlfilepath));
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			
		 workbook = new XSSFWorkbook(file);
		 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet =workbook.getSheet(sheetName);
		System.out.println("sheet name" +sheet);
		cell=null;
		
		 cell = sheet.getRow(2).getCell(1);  
		 
		 cell.setCellValue("12345678"); 
		file.close();
		FileOutputStream output_file =new FileOutputStream(new File(Execlfilepath));
		workbook.write(output_file);
		output_file.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		TestDataProvider.excelReader(ConfigReader.getValue("ExcelPath"),"CreateSingleReservation");
	}
	
	}
		

