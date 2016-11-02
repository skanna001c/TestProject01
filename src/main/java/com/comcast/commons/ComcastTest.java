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

	Application application;
	static Logger log = Logger.getLogger(ComcastTest.class);

	@BeforeClass
	public void beforeClassMethod() {
	}

	@BeforeTest
	public void beforeTestApplication(ITestContext context) {
		log.debug("@BeforeTest beforeTestApplication()");
		application = new Application(frameworkContext);
	}

	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		log.debug("@BeforeMethod - setupDataApplication -  In ComcastTest method :" + testName.getName());
		// If login is required only once during test. Implement below method
		// application.beforeMethodGetUserndURL(testName);
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
