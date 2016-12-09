package com.comcast.commons;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.utils.ComcastTestMain;

/**
 * Base class for all Comcast Test
 * @author hbolak01c
 *
 */
public class ComcastTest extends ComcastTestMain {

	static Logger log = Logger.getLogger(ComcastTest.class);

	@BeforeClass
	public void beforeClassMethod() {
	}

	@BeforeTest
	public void beforeTestApplication(ITestContext context) {
		log.debug("@BeforeTest beforeTestApplication()");
		Application application = new Application(frameworkContext);
		application.applicationLogin();
	}

	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		log.debug("@BeforeMethod - setupDataApplication -  In ComcastTest method :" + testName.getName());		
	}

	@AfterMethod
	public synchronized void endMethodApplication(ITestResult result) {
		log.debug("@AfterMethod beforeTestApplication()");
	}

	@AfterTest(alwaysRun = true)
	public synchronized void afterTestApplication() {
		log.debug("@AfterTest afterTestApplication()");
		// Implement below method, if browser needs to closed after test
		//browser.quit();
	}
}
