package com.comcast.template;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;

public class SampleTest extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status=true;
	

	@BeforeClass
	public void beforeTest() {
		loadData();
		testcaseName = frameworkContext.getTestCaseName();
	}
	
	
	@Test(priority = 500)
	public void sampleTest() {
		
	}
		
	
	public void loadData() {
		//accountInfo = AccountInfo.loadFromDatatable(dataTable);
		// Please load data from dataTable
	}

}


