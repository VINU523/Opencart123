package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException 
	{
		
		String path=".\\testdata\\testLogin.xlsx";
		
		ExcelUtility el=new ExcelUtility(path);
		int totalrows=el.rowcount("Sheet1");
		int totalcolms=el.cellcount("Sheet1", totalrows);
		
		String login[][]=new String[totalrows][totalcolms];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcolms;j++)
			{
				login[i-1][j]=el.getcellData("Sheet1", i, j);
			}
		}
		return login;
		
		
		
	}
}
