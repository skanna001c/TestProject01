package com.comcast.template;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;
import com.comcast.pages.TemplatePage;

public class TemplateTest1 extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status = true;
	
	static Logger log = Logger.getLogger(TemplateTest1.class.getName());
	
	TemplatePage templatePage;

	@BeforeClass
	public void beforeClassMethod() {
		log.debug("beforeTest called by @beforeClassMethod");
	}

	/**
	 * All test methods should make calls to specific page methods
	 * and should also catch and handle exceptions for non recoverable errors.
	 * 
	 * Set priority in intervals of 100s to enable execution flow modification.
	 * Eg: priority 100 will run before priority 200.
	 */
	@Test(priority = 100)
	public void sampleTestMethod1() {
		log.debug("\n\nTest method - sampleTestMethod1\n\n");

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
		log.debug("Test method - sampleTestMethod1: Complete \n\n\n");
	}
	
	@Test(priority = 200)
	public void sampleTestMethod2() {
		log.debug("\n\nTest method - sampleTestMethod2\n\n");

		try {
			log.debug("Do google search");
			templatePage.googleSearch();

		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			throw new RuntimeException(eMsg) ;
		}
		log.debug("Test method - sampleTestMethod2: Complete \n\n\n");
	}

	@Test(priority = 300)
	public void sampleTestMethod3() {
		log.debug("\n\nTest method - sampleTestMethod3\n\n");

		try {
			log.debug("Do google search  with error1");			
			log.debug("New google page ....");
			templatePage = new TemplatePage(frameworkContext);
		    templatePage.launch();			
			templatePage.googleSearchError();
			
		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			throw new RuntimeException(eMsg) ;
		}
		log.debug("Test method - sampleTestMethod3: Complete \n\n\n");
	}
	
}
