package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	String path;
	
	//here we create one constructor where we pass path of excel file when object is created
	public ExcelUtility(String path) {
		
		this.path = path;
	}
	
	// Now we write methods
	//get row count
	
	public int getrowcount(String sheetname) throws IOException {
		
		// so, first we have to open our excel file in read mode
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);    //open this file in new workbook
		sheet = workbook.getSheet(sheetname);  //get our sheet here
		int rowcount = sheet.getLastRowNum();   // we get last row number 
		
		workbook.close();
		fis.close();
		return rowcount;		
		
	}
	
	public int getcellcount(String sheetname, int rownum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);   // here we get row for counting cell number
		int cellcount = row.getLastCellNum();    // here we get last cell number
		
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getcelldata(String sheetname, int rownum, int cellnum) throws IOException {
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
//		String data = cell.toString();   //we can get data using tostring method and also using DataFormatter 
		
		DataFormatter formatter = new DataFormatter();   //it is a class from apache poi
		String data;
		
		try {
		data = formatter.formatCellValue(cell);  // return formatted cell value of a cell as a string
		}
		catch(Exception e) {
			
			data = "";     // if we cannot get any data in cell then it returns empty
		}
		
		workbook.close();
		fis.close();
		return data;
	}
	
	public void setcelldata(String sheetname, int rownum, int cellnum, String data) throws IOException {
		
		// check the file exist or not
		File xlfile = new File(path);
		if(!xlfile.exists()) {
			
			workbook = new XSSFWorkbook();  //create new workbook file
			fos = new FileOutputStream(path);  //open file in write mode
			workbook.write(fos);
		}
		
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		
		//check sheet is exists or not
		if(workbook.getSheetIndex(sheetname)==-1)  //if sheet not exist then create new sheet
			workbook.createSheet(sheetname);		
		
		sheet = workbook.getSheet(sheetname);
		
		//check row exists or not. if not then create new row
		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		
		row = sheet.getRow(rownum);
		
		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		workbook.write(fos);
		
		workbook.close();
		fis.close();
		fos.close();
	}
	
}
