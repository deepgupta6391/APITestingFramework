package com.w2a.APITestingFramework.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import com.w2a.APITestingFramework.setUp.BaseTest;



public class DataUtil extends BaseTest{
	
	
	@DataProvider(name="data")
	public Object[][] getData(Method m) {


		int rows=excel.getRowCount(Constants.DATA_SHEET);
		
		System.out.println("Total number of rows are: "+rows);
		
		String testName=m.getName();
		
		//Find the test case start row
		int testCaseRowNum=1;
		
		for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++) {
			
			String testCaseName=excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
			if(testCaseName.equalsIgnoreCase(testName))
				break;			
		}
		System.out.println("Test case starts from row num: "+testCaseRowNum);
		
		//checking total rows in test case
		
		int dataStartRowNum=testCaseRowNum+2;
		int testRows=0;
		while(!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")) {
			testRows++;
		}		
		System.out.println("Total Rows of data are: "+testRows);
		
		//checking total cols in test case
		
		int colStartColNum=testCaseRowNum+1;
		int testCols=0;
		while(!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")) {
			testCols++;
		}
		System.out.println("Total cols are "+testCols);
		
		
		//printing data
		
		Object[][] data=new Object[testRows][1];
		int i=0;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+testRows);rNum++) {
			
			Hashtable<String,String> table=new Hashtable<String, String>();
			
			for(int cNum=0;cNum<testCols;cNum++) {
				
				//[rNum-dataStartRowNum][cNum]=excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String testData=excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String colName=excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
				
				table.put(colName,testData);
			}
			
			data[i][0]=table;
			i++;
		}
	

		return data;

	}

}
