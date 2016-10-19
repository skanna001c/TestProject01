package com.comcast.template;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.pages.GooglePage;

public class GoogleTest1 extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status = true;

	@BeforeClass
	public void beforeTest() {
		// loadData();
		log.debug("beforeTest called by @BeforeClass");
		testcaseName = frameworkContext.getTestCaseName();
	}

	@Test(priority = 500)
	public void googleT1() {
		log.debug("Test method - googleT1");

		try {
			log.debug("New google page ....");
			GooglePage ggPage1 = new GooglePage(frameworkContext);
			log.debug("Do google search");
			ggPage1.google_search();

		} catch (Exception e) {
			log.error("Test failed ---- " + e.getMessage());
			e.printStackTrace();

		}

	}

	public void loadData() {
		log.debug("loadData - do nothing");
		// accountInfo = AccountInfo.loadFromDatatable(dataTable);
		// Please load data from dataTable
	}

}
