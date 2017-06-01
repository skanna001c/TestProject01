package com.comcast.template;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;
import com.comcast.pages.TemplatePage;

public class TemplateTest extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status = true;
	
	static Logger log = Logger.getLogger(TemplateTest.class.getName());
	
	TemplatePage templatePage;

	@BeforeClass
	public void beforeClassMethod() {
		log.debug("beforeTest called by @beforeClassMethod");
	}

	/**
	 * All test methods should make calls to specific page methods
	 * and should also catch and handle exceptions for non recoverable errors.
	 *
	 * Following standards must be followed while writing the test methods:
	 * 
	 * 1. Should catch any runtime Exceptions and subsequently throw them for unrecoverable error with appropriate message(Eg. eMsg)
	 * 2. Typical test method should not exceed 20 lines. Use page methods 
	 * 3. Use appropriate log.trace method to log contextual information in conditionals and iterations
	 * 4. Set priority in intervals of 100s to enable execution flow modification.
	 *    Eg: priority 100 will run before priority 200.
	 * 5. Any common startUp code should be in @beforeMethod/@beforeTest 
	 * 6. Any common clean up code should be in @afterMethod/@afterTest 
	 * 
	 */
	@Test(priority = 100)
	public void sampleTestMethod1() {
		log.debug("\n\nTest method - sampleTestMethod1\n\n");

		try {
			templatePage = new TemplatePage(frameworkContext);
		    templatePage.launch();
		} catch (Exception e) {
			String eMsg = "Test Failed --- " + e.getMessage() ;
			log.error(eMsg);
			log.debug("EndFail :\n\n\n");
			// Recoverable error. Continuing test execution. Else uncomment below
			//throw new RuntimeException(eMsg);
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
	
	@Test(priority = 400)
	public void minimalViableTest(){
		log.debug("in Test method");
		templatePage = new TemplatePage(frameworkContext);
	    templatePage.minimalViablelaunch();			

	}
	
}
