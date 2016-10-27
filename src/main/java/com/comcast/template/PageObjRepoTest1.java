package com.comcast.template;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;
import com.comcast.pages.GooglePage;

public class PageObjRepoTest1 extends ComcastTest {
	
	protected String testcaseName;
	int retryCount = 0;
	boolean status=true;
	
	static Logger log = Logger.getLogger(PageObjRepoTest1.class.getName());
	
	@BeforeClass
	public void beforeTest() {
		loadData();
		testcaseName = frameworkContext.getTestCaseName();
	}
	
	
	@Test(priority = 100)
	public void test1() {
		
		GooglePage  gp = new GooglePage(frameworkContext);
		
	}
		
	
	public void loadData() {
		//accountInfo = AccountInfo.loadFromDatatable(dataTable);
		// Please load data from dataTable
	}

}

