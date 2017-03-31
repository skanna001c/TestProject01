package com.comcast.template;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.commons.ComcastTest;
import com.comcast.pages.TemplatePage;

public class MinimalViableTest extends ComcastTest {
	protected String testcaseName;
	int retryCount = 0;
	boolean status = true;
	
	static Logger log = Logger.getLogger(MinimalViableTest.class.getName());
	
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
	public void minimalViableTestSample(){
		log.debug("in Test method");
		templatePage = new TemplatePage(frameworkContext);
	    Assert.assertTrue(templatePage.minimalViablelaunch());			
	}
	
}
