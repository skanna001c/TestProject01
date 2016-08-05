package com.comcast.utils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.util.concurrent.TimeUnit;

import com.comcast.reporting.*;
import com.comcast.reporting.ReportThemeFactory.Theme;
import com.comcast.neto.alm.ALMTestInformation;
import com.comcast.neto.alm.ALMUpdaterClient;

/**
 * Base class for all Comcast Test
 *
 */
public class ComcastTest_bkup extends TestWatcher{
	private long startTime;
	private long endTime;
	private static long timeTaken1;
	private static String watchedLog;
	@Rule
	public TestName testName= new TestName();
	protected DataTable dataTable;
	public static SeleniumReport report;
	protected static SeleniumReport reportSummary;
	
	protected static WebDriver browser;
	protected static TestSettings settings;
	protected String testCaseName;
	
	public static String totalExecutionTime;
	public static int nTestsPassed=0;
	public static int nTestsFailed=0;

	
	 @Rule
	 
	 /***
	     * Added Rule to Rerun failed tests till threshold FailRunRetryCount
	     * If the test is passed before the threshold run count,remaining runs will be disregarded
	 ***/
	  public JUnitRetry retry = new JUnitRetry();
	 
	 @Rule
	  public TestWatcher watchman= new TestWatcher() {
		 
	      @Override
	      protected synchronized void failed(Throwable e, Description description) {
	    	  String reportPath=ReportPath.getInstance().getReportPath();
	    	  String testCaseQCName=testName.getMethodName();
	    	  createResultFile(reportPath,"Failed");
	    	  System.out.println("Test Case Failed");
	    	  createZipFileOfReport(reportPath,testCaseQCName);	   
	    	  
	    	  endTime=System.currentTimeMillis();
	    	  Long timeTaken=endTime-startTime-20;//-20 is to compensate for things other than test script execution
	    	  timeTaken1 = timeTaken1 + timeTaken;
	    	    int h = (int) ((timeTaken / 1000) / 3600);
		  		int m = (int) (((timeTaken / 1000) / 60) % 60);
		  		int s = (int) ((timeTaken / 1000) % 60);
		  		String time=""+m+":mm "+s+":ss";
		  		
		  		
			  	timeTaken = timeTaken/1000; //Converting to seconds
			    String duration = timeTaken.toString();
			    	 	
		  
	    	  String testSetID=settings.getTestSetID();
	    	  if (!(testSetID == null || testSetID ==""))
	    	  {
	    		  	ALMTestInformation ALMInfo= new ALMTestInformation();
		    		ALMUpdaterClient ALMUpdate= new ALMUpdaterClient();
			  		String testID;
					try {
					    testSetID=settings.getTestSetID();
					    if(!testCaseQCName.equals("testMethod"))
					    {
					    	testID = ALMInfo.GetTestID(testCaseQCName);
					    	
					    	
					    	testID = ALMInfo.GetTestID(testCaseQCName);
					    	ALMUpdate.createTestRUN(testSetID,testCaseQCName, testID, "Failed", duration);
					    
					    	String component = ALMInfo.getTestComponent(testID);
					  		reportSummary.updateResultSummary(component, testCaseQCName, time, "Failed");
							
					    }
				  	
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	  }else reportSummary.updateResultSummary("", testCaseQCName, time, "Failed");
	    	  
	      	}
	      

	      @Override
	      protected synchronized void succeeded(Description description) {
	    	  String reportPath=ReportPath.getInstance().getReportPath();
	    	  String testCaseQCName=testName.getMethodName();
	    	  //System.out.println("Before zipping");
	    	  createResultFile(reportPath,"Passed");
	    	  System.out.println("Test Case Passed");
	    	  createZipFileOfReport(reportPath,testCaseQCName);
	    	  
	    	  endTime=System.currentTimeMillis();
	    	  Long timeTaken=endTime-startTime-20;//-20 is to compensate for things other than test script execution
	    	  timeTaken1 = timeTaken1 + timeTaken;
	    	    int h = (int) ((timeTaken / 1000) / 3600);
		  		int m = (int) (((timeTaken / 1000) / 60) % 60);
		  		int s = (int) ((timeTaken / 1000) % 60);
		  		//String time=""+h+":hh "+m+":mm "+s+":ss";
		  		String time=""+m+":mm "+s+":ss";
		  		
			  	timeTaken = timeTaken/1000; //Converting to seconds
			    String duration = timeTaken.toString();
			     
	    	  String testSetID=settings.getTestSetID();
	    	  if (!(testSetID == null || testSetID ==""))
	      		{	
			    		
	    		  	ALMTestInformation ALMInfo= new ALMTestInformation();
		    		ALMUpdaterClient ALMUpdate= new ALMUpdaterClient();
			  		
	    		  	String testID;
						try {
						     testSetID=settings.getTestSetID();
						     if(!testCaseQCName.equals("testMethod"))
							    {
						    	 	testID = ALMInfo.GetTestID(testCaseQCName);
							    	
							    	testID = ALMInfo.GetTestID(testCaseQCName);
							    	ALMUpdate.createTestRUN(testSetID,testCaseQCName, testID, "Passed", duration);
							    
							    	String component = ALMInfo.getTestComponent(testID);
							  		reportSummary.updateResultSummary(component, testCaseQCName, time, "Passed");
									
							    }
					  	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    		  
	      		}else reportSummary.updateResultSummary("", testCaseQCName, time, "Passed");  
	      
	      }
	     };
	
    @BeforeClass
    public static void initializeSummaryReport()
    {
		String reportPath=ReportPath.getInstance().getReportPath();
		ReportSettings reportSettings = new ReportSettings(reportPath, "Einstein 360 Execution Summary");
		reportSettings.generateExcelReports=false;
		reportSettings.generateHtmlReports=true;
		reportSettings.includeTestDataInReport=false;
		reportSettings.takeScreenshotFailedStep=true;
		reportSettings.takeScreenshotPassedStep=true;
		reportSummary = new SeleniumReport(reportSettings, ReportThemeFactory.getReportsTheme(Theme.TQM));
	
 	//Result Summary
		reportSummary.initializeReportTypes();
		reportSummary.initializeResultSummary();
		reportSummary.addResultSummaryHeading("Einstein 360 Execution Summary");
		reportSummary.addResultSummarySubHeading("Component", "TestCaseName", "Time", "Status");
		//reportSummary.addResultSummarySubHeading(subHeading1, subHeading2, subHeading3, subHeading4);\
		settings=new TestSettings();
		browser=getDriver(settings.getBrowser());
		
 	
    }
    
    @AfterClass
    public static void endReportSummaryReport()
    {
    	System.out.println("After Class");
    	int h = (int) ((timeTaken1 / 1000) / 3600);
  		int m = (int) (((timeTaken1 / 1000) / 60) % 60);
  		int s = (int) ((timeTaken1 / 1000) % 60);
  		//String time=""+h+":hh "+m+":mm "+s+":ss";
  		String totalExecutionTime=""+m+":mm "+s+":ss";
  		reportSummary.addResultSummaryFooter(totalExecutionTime,nTestsPassed, nTestsFailed);
    	//reportSummary.addResultSummaryFooter(totalExecutionTime,nTestsPassed,  nTestsFailed);
    
    }
    
	
	@BeforeMethod
	public synchronized void setupData() {
		
		
		//settings=new TestSettings();
		//browser=getDriver(settings.getBrowser());
			
	    testCaseName=null;
		if(testName.getMethodName().contains("[")){
			testCaseName = testName.getMethodName().substring(0,testName.getMethodName().indexOf('['));
		}else{
			testCaseName = testName.getMethodName();
		}
		initializeReport(testCaseName);
		startTime = System.currentTimeMillis();
		System.out.println("******Method Name :" +testCaseName+ "******");
		
		try{
			dataTable = new DataTable(testCaseName);	
		}catch(Exception e){				
			report.updateTestLog("VerfiyTestDataRow", "Test Data Row Not Present <Please check and add test data>",Status.FAIL);
			browser.quit();			
			System.out.println("Test Data Row not present. Please check");
		}				
	}

		
	/**
	 * Initialize Test Report
	 * @param testCaseName
	 */
	protected synchronized void initializeReport(String testCaseName) {
		String reportPath=ReportPath.getInstance().getReportPath();
		ReportSettings reportSettings = new ReportSettings(reportPath, testCaseName);
		reportSettings.generateExcelReports=false;
		reportSettings.generateHtmlReports=true;
		reportSettings.includeTestDataInReport=false;
		reportSettings.takeScreenshotFailedStep=true;
		reportSettings.takeScreenshotPassedStep=true;
		report = new SeleniumReport(reportSettings, ReportThemeFactory.getReportsTheme(Theme.TQM));
		report.setDriver(browser);
		report.initializeReportTypes();
		report.initializeTestLog();
		report.addTestLogHeading(testCaseName);
		//report.addTestLogSubHeading("Browser:", settings.getBrowser(), "ENV : "+settings.getEnvironmentToTest()+"   URL :", settings.getApplicationURL());
		report.addTestLogSubHeading("Browser:", settings.getBrowser(), "ENV : "+settings.getEnvironmentToTest(), "URL :"+settings.getApplicationURL());
		
		report.addTestLogTableHeadings();
		//report.addTestLogSection("Detailed Steps");
		
	}

	
	@AfterMethod
	public synchronized void tearDown(){
		endTime=System.currentTimeMillis();
		Long timeTaken=endTime-startTime-20;//-20 is to compensate for things other than test script execution
		int h = (int) ((timeTaken / 1000) / 3600);
		int m = (int) (((timeTaken / 1000) / 60) % 60);
		int s = (int) ((timeTaken / 1000) % 60);
		String time=""+h+":hh "+m+":mm "+s+":ss";
		report.addTestLogFooter(time);
	}
	
	/**
	 * Get the browser object specified in the property
	 * @param browserName
	 * @return
	 */
	protected static WebDriver getDriver(String browserName) {
		 final ThreadLocal<WebDriver> ThreadDriver =
		         new  ThreadLocal<WebDriver>();
		 WebDriver driver= ThreadDriver.get();
		
		
		

//		if (browserName.equalsIgnoreCase("firefox")) {	
//			driver= new FirefoxDriver();
//		}
		if (browserName.equalsIgnoreCase("firefox")) {
			
			ProfilesIni profilesIni = new ProfilesIni();

			FirefoxProfile profile = profilesIni.getProfile("default");

//			profile.setAcceptUntrustedCertificates(true);

//			profile.setAssumeUntrustedCertificateIssuer(false);

			profile.setEnableNativeEvents(true);


			driver =ThreadGuard.protect( new FirefoxDriver(profile));
			ThreadDriver.set(driver); 

		}
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", 
					TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/chromedriver.exe");
	
					
					String gridflag = settings.getGRIDstatus();
					if(gridflag.equals("false"))
					{	
						//Chrome SetUp to allow popups
						ChromeOptions options = new ChromeOptions();
						options.addArguments("disable-popup-blocking");
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
						//
						driver=ThreadGuard.protect(new ChromeDriver(capabilities));
						ThreadDriver.set(driver); 
						
					}
					else 
					{
						
						try {
							
							DesiredCapabilities capabilities =DesiredCapabilities.chrome();
							capabilities.setBrowserName("chrome");
							driver = ThreadGuard.protect(new RemoteWebDriver(new URL("http://24.40.25.193:4444/wd/hub"), capabilities));
							 ThreadDriver.set(driver);
						 	} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
		}
		if(browserName.equalsIgnoreCase("iexplore") || browserName.equalsIgnoreCase("ie"))
		{
				
				boolean is64bit = false;
				if (System.getProperty("os.name").contains("Windows")) {
				    is64bit = (System.getenv("ProgramFiles(x86)") != null);
				} else {
				    is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
				}
				
				if(is64bit){
				System.setProperty("webdriver.ie.driver",
						TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/IEDriverServer.exe");
				 
				}else{
					System.setProperty("webdriver.ie.driver",
							TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/IEDriverServer_32.exe");
				}
				
			
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
				/*capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");*/
				capabilities.setCapability("ACCEPT_SSL_CERTS", true);
			
				
				String gridflag = settings.getGRIDstatus();
				if(gridflag.equals("false"))
				{	
					
					driver=ThreadGuard.protect(new InternetExplorerDriver(capabilities));
					ThreadDriver.set(driver);
					
				}
				else 
				{
					
					try {
						
						 capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
						 capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
						
						 driver = ThreadGuard.protect(new RemoteWebDriver(new URL("http://24.40.25.193:4444/wd/hub"), capabilities));
						 ThreadDriver.set(driver);
					 	} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		
		}
		if(browserName.equalsIgnoreCase("safari")){
			/*dCaps = new DesiredCapabilities();
			dCaps.setJavascriptEnabled(true);
			dCaps.setCapability("takesScreenshot", false);

			driver = new PhantomJSDriver();*/
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--ignore-ssl-errors=yes" });
			capabilities.setCapability("browserType", "phantomjs");
			//capabilities.s
			driver=ThreadGuard.protect(new PhantomJSDriver());
			ThreadDriver.set(driver); 
			driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		}
		if (browserName.equalsIgnoreCase("phantom")){
			// updated by hbolak01c to test headless browser
			
			System.setProperty("phantomjs.binary.path", 
					TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/phantomjs.exe");
			
			
			DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
			//capabilities.setCapability(PhantomJSDriverService., TestUtils.getRelativePath()+"/BrowserSpecificDrivers/phantomjs.exe");
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--ignore-ssl-errors=yes" });
			capabilities.setCapability("browserType", "phantomjs");
			//capabilities.s
			driver=ThreadGuard.protect(new PhantomJSDriver());
			ThreadDriver.set(driver); 
			driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
			
			
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	public void reinitializeDriverForReport() {
    	browser=getDriver(settings.getBrowser()); 
    	report.setDriver(browser);  
    }
	
	private List<String> getQCTestCaseCoverage() {
		List<String> coveredTestCases = new ArrayList<String>();

		try {
			Class clazz = Class.forName(this.getClass().getName());
			Method method = clazz.getMethod(testName.getMethodName());
			System.out.println(testName.getMethodName());

			if (method.isAnnotationPresent(QCTestCases.class)) {
				System.out.println(method.getAnnotations());
				QCTestCases qcTestCases = method.getAnnotation(QCTestCases.class);
				System.out.println(qcTestCases);
				for (String testCase : qcTestCases.covered()) {
					coveredTestCases.add(testCase);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		} catch (NoSuchMethodException e) {
			e.printStackTrace(System.err);
		}

		return coveredTestCases;
	}
	
	public void createZipFileOfReport(String reportPath,String testCaseQCName){		
		File dir = new File(reportPath);

		try {			
			List<File> files = (List<File>) FileUtils.listFiles(
					dir, TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);
			byte[] b;

			FileOutputStream fout = new FileOutputStream(reportPath+"\\"
					+ testCaseQCName + ".zip");
			ZipOutputStream zout = new ZipOutputStream(
					new BufferedOutputStream(fout));

			for (int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().contains(testCaseQCName)){
				b = new byte[1024];
				FileInputStream fin = new FileInputStream(
						files.get(i));
				zout.putNextEntry(new ZipEntry(files.get(i)
						.getName()));
				int length;
				while (((length = fin.read(b, 0, 1024))) > 0) {
					zout.write(b, 0, length);
				}
				zout.closeEntry();
				fin.close();	
			}
				
			}
			zout.close();

		} catch (Exception e) {
			System.out.println("Exception caught");
		}
		/*String fileName = new File(reportPath + testCaseQCName
				+ ".zip").getName();
		String folderName = new File(reportPath + testCaseQCName
				+ ".zip").getParent();*/
	}
	public void createResultFile(String reportPath,String runStatus){
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter(reportPath+"\\result.txt"));
		    writer.write(runStatus);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
	public void createViewPageErrorFile(String reportPath,String log){
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter(reportPath+"\\clearView.html"));
			if(!log.equalsIgnoreCase(""))
		    {
		    	writer.write(log);	
		    }
			else
			{
				writer.write("No Page Errors were present");	
			}

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}
	
	public String getMemoryUsedByApplication(String strAppl) throws IOException{
		Process p = Runtime.getRuntime().exec("tasklist");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains(strAppl)) {
				break;
			}
		}
		return line;
	}
	
	 
}
