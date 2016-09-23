package com.comcast.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


/**
 * Datatable class for fetching value from the excel sheet
 *
 */
public class DataTable {
	protected Hashtable<String, String> testData;
	private String testName;
	
	//private String PE_RERUN;
	//private TestSettings settings;
	
	public DataTable(String testName) {
		this.testName=testName;
		loadTestData();
		
	}
	
	//added by harsh on 8/3/2016
	/*public DataTable(String testName, String rerun){
		this.testName = testName;
		this.PE_RERUN = rerun;
		 if(rerun=="false"){
			 loadTestData();
			 System.out.println("Data loaded from Data sheet file as this is a fresh run");
		 }else{
			 loadDumpData();
			 System.out.println("Data loaded from dump properties file as this is a rerun");
		 }
		
	}
	
	//added by harsh on 8/3/2016
	@SuppressWarnings("unchecked")
	private void loadDumpData(){
		DataDump dataDump = new DataDump();
		testData = dataDump.loadData(testName);
	}
*/

	private void loadTestData() {
		HSSFWorkbook hssfWorkbook = getWorkBook(testName);	
		HSSFSheet dataSheet = getSheet(hssfWorkbook);
		ArrayList<String> columNames= getColumnNames(dataSheet);
		
		Row testDataRow= getTestDataRowForTestCase(dataSheet);
		if(testDataRow==null){
			throw new RuntimeException("Unable to find testdata row for " + testName);
		}
		testData=prepareTestDataRowHashTable(columNames,testDataRow);
	}
	
	/**
	 * Get value from the data sheet
	 * @param key column name in the data sheet
	 * @return 
	 */
	public String getValue(String key){
	if (testData.containsKey(key))
		{
		 return testData.get(key);
		}
	else return "";
	}
	
	//added by harsh on 8/3/2016
	public Hashtable<String,String> getDataTable(){
		return testData;
	}

	private HSSFSheet getSheet(HSSFWorkbook hssfWorkbook) {
		
		TestSettings testSetting= new TestSettings();
		String sheetName=testSetting.getSheetName();
		System.out.println(sheetName);
		//HSSFSheet sheet = hssfWorkbook.getSheetAt(0); 
		HSSFSheet sheet = hssfWorkbook.getSheet(sheetName); 	
		return sheet;
	}

	private HSSFWorkbook getWorkBook(String testName) {
		//TestSettings testSetting= new TestSettings();
		//String path=TestUtils.getRelativePath() + File.separator + FrameworkConstants.DATA_FOLDER + File.separator + testSetting.getEnvironment()+ File.separator +FrameworkConstants.TEST_DATA_SHEET_NAME;
		String path=TestUtils.getRelativePath() + "//src//test//resources" + File.separator +FrameworkConstants.TEST_DATA_SHEET_NAME;
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
			fileInputStream.close();
			return hssfWorkbook;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private Hashtable<String, String> prepareTestDataRowHashTable(
			ArrayList<String> columNames, Row testDataRow) {
		Hashtable<String, String> testDataRowHashTable= new Hashtable<String,String>();		
		for (Cell cell : testDataRow) {
			if(columNames.size()>cell.getColumnIndex()){
			String columnName=columNames.get(cell.getColumnIndex());
			String columnValue=cell.getStringCellValue();			
			testDataRowHashTable.put(columnName, columnValue.trim());
			
			}else{
				break;
			}
		}
		return testDataRowHashTable;
	}

	private ArrayList<String> getColumnNames(Sheet testDataSheet) {
		ArrayList<String> columnNameList= new ArrayList<String>();
		Row row = testDataSheet.getRow(0);
		for (Cell cell : row) {
			columnNameList.add(cell.getStringCellValue());
		}
		return columnNameList;
	}

		
	private Row getTestDataRowForTestCase(Sheet testDataSheet) {
		for (Row row : testDataSheet) {
			if (IsRequiredTestCaseRow(row,this.testName)) {
				return row;
			} 
		}		
		return null;
	}

	private boolean IsRequiredTestCaseRow(Row row, String testCaseName) {
		Cell testCaseIdCell= row.getCell(0);
		String testCaseId=testCaseIdCell.getStringCellValue();
		if (testCaseId.trim().equals(testCaseName)) {
			return true;
		}
		return false;
	}
	
}
