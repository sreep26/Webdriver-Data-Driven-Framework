package Lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.ast.NewExpression;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xpath.FoundIndex;

public class Excel {
	
	private InputStream dataFile;
	private HSSFWorkbook workBook;
	
	public Excel(String filename) {
		
		System.out.println(filename);
		try {
			dataFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\Data\\"+filename);
			workBook  = new HSSFWorkbook(dataFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/***
	 * 
	 * @param testcase
	 * @return Object[][]
	 */
	public Object[][] getMultipleRows(String testcase) {

		String testSheetName = getTestSheetName(testcase);
		if(testSheetName.equalsIgnoreCase("TestSheet Not Found")){
			return null;
		}
		HSSFSheet testSheet = workBook.getSheet(testSheetName);
		String cellData;
		int totalRows = testSheet.getPhysicalNumberOfRows(); // Total rows in sheet.
		int totalCols = testSheet.getRow(0).getLastCellNum();

		int startIndex 			= 	0;
		int endIndex			=	0;
		boolean foundStartIndex = 	false; 
		
		for(int row=1;	row<totalRows; row++){
			if(foundStartIndex == false && testcase.equalsIgnoreCase(testSheet.getRow(row).getCell(0).toString())) {
				startIndex = row;
				foundStartIndex = true;
			}
			
			if(foundStartIndex == true && !testcase.equalsIgnoreCase(testSheet.getRow(row).getCell(0).toString())){
				endIndex = row;
				break;
			}
			
			endIndex = totalRows; //if bottom reached.
		}

		totalRows	=	endIndex-startIndex;  // total rows contains same scenario 
		String[][] testData = new String[totalRows][totalCols - 1];
		
		int index = 0 ; 
		 for(int row=startIndex; row<endIndex ; row++) {
			 for (int col = 1; col < totalCols; col++) {
				if (testSheet.getRow(row).getCell(col) == null) {
					cellData = "";
				} else {

					cellData = testSheet.getRow(row).getCell(col).toString();
				}

				testData[index][col - 1] = cellData;
			
			}

				index++;
		 }
		return testData;
	}

	/**
	 * 
	 * @param testcase
	 * @return
	 */
	public HashMap<String, String> getRow(String testcase) {
		HashMap<String,String> testData = new HashMap<String,String>();
		String testSheetName = getTestSheetName(testcase);
		HSSFSheet testSheet = workBook.getSheet(testSheetName);
		String cellData;
		int totalRows = testSheet.getPhysicalNumberOfRows();

		// Row starts from 1 as first row is header
		for (int row = 1; row < totalRows; row++) {
			System.out.println(testSheet.getRow(row).getCell(0).toString());
			if (testcase.equalsIgnoreCase(testSheet.getRow(row).getCell(0).toString())) {
		
				int totalCells = testSheet.getRow(row).getLastCellNum();
				for(int cell=1; cell<totalCells; cell++){
					//First column shows the scenario name.
					if(testSheet.getRow(row).getCell(cell) == null ){ 
						cellData = "";
					}else{
						cellData = testSheet.getRow(row).getCell(cell).toString();
					}
					String celllName = testSheet.getRow(0).getCell(cell).toString();
					testData.put(celllName, cellData);
				}
				break;
			}
		}

		return testData;
	}	

	public String getTestSheetName(String testcase) {

		HSSFSheet overviewSheet = workBook.getSheet("overview"); // first sheet
		int totalRows = overviewSheet.getPhysicalNumberOfRows();

		// Row starts from 1 as first row is header
		for (int row = 1; row < totalRows; row++) {
			System.out.println(overviewSheet.getRow(row).getCell(1).toString());
			if (testcase.equalsIgnoreCase(overviewSheet.getRow(row).getCell(1)
					.toString())) {
				return overviewSheet.getRow(row).getCell(0).toString();
			}
		}
		return "TestSheet Not Found";
	}
	
	public static void main(String[] args) {
		Excel excel = new Excel("smokeTest.xls");
		HashMap<String, String> testData = excel.getRow("negativeLogin");
		
		//Object[][]data  = excel.getMultipleRows("negativeLogin");
		//Object[][]data  = excel.getMultipleRows("validLogin");
		System.out.println(testData);
	}


}
