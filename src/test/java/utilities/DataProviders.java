package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")    
	public String[][] getdata() throws IOException{
		
		// we can pass excel file path to excel utility file
		String path = ".//testdata//Opencart_LoginData.xlsx";
		
		ExcelUtility xutils = new ExcelUtility(path);
		
		int totalrow = xutils.getrowcount("Sheet1");
		int totalcell = xutils.getcellcount("Sheet1", 1);
		
		// create variable of string 2-dimentional array to receive data
		
		String[][] logindata = new String[totalrow][totalcell];
		
		//write for loop to receive data
		
		for(int i=1; i<=totalrow; i++) {
			
			for(int j=0; j<totalcell; j++) {
				
				logindata[i-1][j] = xutils.getcelldata("Sheet1", i, j);
			}
		}
		
		return logindata;
		
		
	}
	
	
}
