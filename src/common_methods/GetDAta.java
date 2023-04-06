package common_methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDAta {
	public static String TestDataPath;
	public static  ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		//step 1 open excel file by creating the object of fileinputstream
		TestDataPath = System.getProperty("user.dir");
		System.out.println(TestDataPath);
		FileInputStream fis = new FileInputStream(TestDataPath+"/TestData.xlsx");
		
		//step 2 create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		// step 3 access the desired sheet
		// step3.1 fetch the count of sheet available in th excel file
		int countOfSheet = workbook.getNumberOfSheets();
		
		//step 3.2 fetch the name of sheet and compare against desired sheet name
		for(int i=0; i<countOfSheet; i++)
		{
			String sheetname = workbook.getSheetName(i);
			if (sheetname.equalsIgnoreCase(testSheetName))
			{
		       // step 4 access the sheet and iterate through rows to fetch the column in which test cate name column is found
				XSSFSheet Sheet = workbook.getSheetAt(i);
				//Step 4.1 create iterator for rows 
			    Iterator<Row> Rows = Sheet.iterator();
			    Row firstRow = Rows.next();
			    //Step 4.2 create iterator for cells 
			    Iterator<Cell> Cells = firstRow.cellIterator();
			    int j=0;
			    int tc_column=0;
			    
			    //step 4.3 Read the cell values of row no1 to compare against the  test case name 
			    while(Cells.hasNext())
			    {
			    	Cell cellvalue = Cells.next();
			    	if (cellvalue.getStringCellValue().equalsIgnoreCase("test_tc"))
			    	{
			    		tc_column= j;
			    	}
			    	j++;
			    }
			    // Step 5 fetch the data for designated test case
			    while(Rows.hasNext())
			    {
			    	Row dataRow = Rows.next();
			    	if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
			    	{
			    		Iterator<Cell> dataCellvalue = dataRow.cellIterator();
			    		while(dataCellvalue.hasNext())
			    		{
			    			Cell dataOfCell = dataCellvalue.next();
			    			//Method 1 to extract Cell value by using try and catch method
			    			/*try 
			    			{
			    				String testdata =  dataOfCell.getStringCellValue();
			    				System.out.println(testdata);
			    			}
			    			catch (IllegalStateException e)
			    			{
			    				int inttestData = (int) dataOfCell.getNumericCellValue();
			    				System.out.println(inttestData);
			    			}*/
			    			//Method 2 to extract the cell  value by using if and else 
			    			/*CellType datatype = dataOfCell.getCellType();
				    		
				    		if (datatype.toString() == "NUMERIC")
				    		{
				    			int inttestData = (int) dataOfCell.getNumericCellValue();
				    			System.out.println(inttestData);
				    		}
				    		else if (datatype.toString() == "STRING")
				    		{
				    			String testData = dataOfCell.getStringCellValue();
				    			System.out.println(testData);
				    		}*/
			    			// Method 3 -- Extract The data by converting it into String
			    			
			    			//String testData = dataCellvalue.next().toString().replaceAll("\\.\\d+$", "");
			    			//System.out.println(testData);
			    			
			    			//Method 4-- Extract the data by using Dataformatter
			    			DataFormatter format =new DataFormatter();
			    			String testData=format.formatCellValue(dataCellvalue.next());
			    			System.out.println(testData);

			    			
			    		}
			    		
			    	}
			    }
			}
			
		}
	return arrayData;
	}

}
