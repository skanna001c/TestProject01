package com.comcast.template;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;
import com.comcast.pages.TemplatePage;

public class GoogleTest1 extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status = true;
	
	static Logger log = Logger.getLogger(GoogleTest1.class.getName());
	
	TemplatePage templatePage  ;

	@BeforeClass
	public void beforeClassMethod() {
		// loadData();
		log.debug("beforeTest called by @BeforeClass");
		testcaseName = frameworkContext.getTestCaseName();
	}

	@Test(priority = 100)
	public void google_T1() {
		log.debug("\n\nTest method - google_T1\n\n");
		log.debug("Start:");

		try {
			log.debug("New google page ....");
			templatePage = new TemplatePage(frameworkContext);
		    templatePage.launch();

		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			throw new RuntimeException(eMsg) ;			
		}
		log.debug("EndPass :\n\n\n");

	}
	

	@Test(priority = 200)
	public void google_T2() {
		log.debug("\n\nTest method - google_T2\n\n");
		log.debug("Start:");

		try {
			log.debug("Do google search");
			templatePage.googleSearch();

		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			throw new RuntimeException(eMsg) ;
		}
		log.debug("EndPass :\n\n\n");

	}

	

	@Test(priority = 300)
	public void google_T3() {
		log.debug("\n\nTest method - google_T3\n\n");
		log.debug("Start:");

		try {
			log.debug("Do google search  with error1");
			
			log.debug("New google page ....");
			templatePage = new TemplatePage(frameworkContext);
		    templatePage.launch();
			
			templatePage.googleSearchError();;

		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			throw new RuntimeException(eMsg) ;
		}
		log.debug("EndPass :\n\n\n");

	}
	
	public void loadData() {
		log.debug("loadData - do nothing");
		// accountInfo = AccountInfo.loadFromDatatable(dataTable);
		// Please load data from dataTable
	}

}
