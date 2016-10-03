package com.comcast.commons;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.comcast.utils.ComcastTestMain;
import com.comcast.utils.GridInfo;
import com.comcast.utils.TestUtils;


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
