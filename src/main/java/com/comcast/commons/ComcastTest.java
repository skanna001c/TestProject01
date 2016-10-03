package com.comcast.commons;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.utils.ComcastTestMain;


/**
 * Base class for all Comcast Test
 *
 */
/**
 * @author hbolak01c
 *
 */
public class ComcastTest extends ComcastTestMain  {
	
	Logger log = Logger.getLogger(ComcastTest.class);
    Application application;
    // added a new beforeTest method - Harsh
    @BeforeTest
    public void beforeTestMainComcast(ITestContext context){
		application = new Application(frameworkContext);
    	log.info("inside before test");
    	
    	
    }
	
    // updated the logic to check if datatable is null, then instantiate it
	@BeforeMethod
	public synchronized void setupDataApplication(Method testName) {
		System.out.println("In ComcastTest method :"+testName.getName());
	//	if(application == null){
	//	log.info(message);
	//	application = new Application(frameworkContext);
	//	}
		application.beforeMethodGetUserndURL(testName);
	    		
	}
	
	@AfterMethod
	public synchronized void tearDownApplication(ITestResult result){
		
		String methodStatus;
				
		// update teststatustable
		if(result.getStatus()==1){
			methodStatus = "PASS";
		}else{
			methodStatus = "FAIL";
		}
		
	//	System.out.println("The transaction with name: " + result.getMethod().getMethodName() + " took :" + time);
		
		dataDump.setValue(result.getMethod().getMethodName() + "_status", methodStatus);
		if(methodStatus.equalsIgnoreCase("fail")){
			dataDump.deleteValue("CSOLoggedIN");
			dataDump.deleteValue("CMLoggedIN");	
			application.cleanDump(dataDump); // added by harsh to move century logic out of here -9/28
			//dataDump.
			
//			throw new SkipException("Test method: "+result.getMethod().getMethodName()+" failed. Quitting the test as other methods depend on it");

		}
	}
	
	@AfterTest(alwaysRun=true)
	public synchronized void afterTestMainApplication(){
		if(settings.getUpdateALM().equalsIgnoreCase("true"))
			almRestUpdateStatus(); // added by harsh on 8/30/2016
			dataDump.deleteValue("CSOLoggedIN");
			dataDump.deleteValue("CMLoggedIN");	
			application.cleanDump(dataDump); // added by harsh to move century logic out of here -9/28
		//	dataDump.dumpData();
			/*browser.close();
		browser.quit();*/
		
		
	}

	 
}
