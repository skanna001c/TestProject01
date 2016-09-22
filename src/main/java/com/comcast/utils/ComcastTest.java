package com.comcast.utils;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.commons.CenturyApplication;
import com.comcast.logging.logtransactions.LoggerMain;
import com.comcast.logging.logtransactions.LoggerMainImpl;
import com.comcast.neto.alm.ALMTestInformation;
import com.comcast.neto.alm.ALMUpdaterClient;
import com.comcast.reporting.ReportSettings;
import com.comcast.reporting.ReportThemeFactory;
import com.comcast.reporting.ReportThemeFactory.Theme;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.reporting.Status;


/**
 * Base class for all Comcast Test
 *
 */
/**
 * @author hbolak01c
 *
 */
public class ComcastTest {
	private long startTime;
	private long startTestTime;
	private String testStatus;
	private long endTime;
	private static long timeTaken1;
	private static String watchedLog;
	
	public Method testName;	
	public SeleniumReport report;
	protected  SeleniumReport reportSummary;
	protected  ContactTabPageCM contactTab;
	protected  AddressTabPageCM addressTab;
	
	protected WebDriver browser;
	
	protected String testCaseName;
	
	public static String totalExecutionTime;
	public static int nTestsPassed=0;
	/**
	 * 
	 */
	public static int nTestsFailed=0;
	
	protected Hashtable<String,String> testStatusTable;
	protected IDataDump dataDump;
	protected   DataTable dataTable; // updated by harsh on 8/4
	// updated by harsh on 8/4 //made static by rijin 19/8/16
	protected  TestSettings settings; 
	protected  IUserDetails userDetails;
	private  CenturyApplication centuryApplication;
	private String userName;
	
	// updated by hbolak01c for performance transaction logging
	protected LoggerMain transactionLogger =  new LoggerMainImpl();
	
	// updated by hbolak01c to pass this context as a single source for all data
	protected FrameworkContext frameworkContext = new FrameworkContext();
	
	static Logger log = Logger.getLogger(ComcastTest.class);
	
	public IDataDump getDataDump(){
		return dataDump;
	}
	
	public void setDataDump(IDataDump dataDump){
		this.dataDump=dataDump;
	}
	
	public IUserDetails getUserDetail(){
		return userDetails;
	}
	
		// changed to before suite from beforeclass - Harsh 
    
    public void initializeSummaryReport()
    {

		String reportPath=ReportPath.getInstance().getReportPath();
		ReportSettings reportSettings = new ReportSettings(reportPath, "Execution Summary");
		reportSettings.generateExcelReports=false;
		reportSettings.generateHtmlReports=true;
		reportSettings.includeTestDataInReport=false;
		reportSettings.takeScreenshotFailedStep=true;
		reportSettings.takeScreenshotPassedStep=true;
		reportSummary = new SeleniumReport(reportSettings, ReportThemeFactory.getReportsTheme(Theme.TQM));
	
 	//Result Summary
		reportSummary.initializeReportTypes();
		reportSummary.initializeResultSummary();
		reportSummary.addResultSummaryHeading("Execution Summary");
		reportSummary.addResultSummarySubHeading("Component", "TestCaseName", "Time", "Status");
		//reportSummary.addResultSummarySubHeading(subHeading1, subHeading2, subHeading3, subHeading4);\
		
		
		//moved from before method and changed this to before suite
		
 	
    }
    
    @AfterClass
    public void endReportSummaryReport()
    {
    	System.out.println("After Class");
    	 int h = (int) ((timeTaken1 / 1000) / 3600);
	  		int m = (int) (((timeTaken1 / 1000) / 60) % 60);
	  		int s = (int) ((timeTaken1 / 1000) % 60);
	  		//String time=""+h+":hh "+m+":mm "+s+":ss";
	  		String totalExecutionTime=""+m+":mm "+s+":ss";
    	reportSummary.addResultSummaryFooter(totalExecutionTime,nTestsPassed, nTestsFailed);
    
    }
    
    
    // added a new beforeTest method - Harsh
    @BeforeTest
    public void beforeTestMain(ITestContext context){
    	
    	PropertyConfigurator.configure("log4j.properties");
    	log.info("inside before test");
    	testStatusTable = new Hashtable<String,String>();
    	initializeSummaryReport();
    	testCaseName = context.getCurrentXmlTest().getName();
    	testStatus = "Passed";
    	    	
		//if(settings==null) // added by harsh on 8/2/2016
			settings=new TestSettings();	
			ClearTempFilesndChngeProxy();
			userDetails = new UserDetails();
			userDetails.loadData();		
			 
			 
			 
			//if(browser==null)
			
			browser=getDriver(settings.getBrowser());
			//added by harsh on 9/9
			String browserVersion = (String) ((JavascriptExecutor) browser).executeScript("return navigator.userAgent;");
			settings.setProperty("browser_version", browserVersion);
		
    	initializeReport(testCaseName);
		startTestTime = System.currentTimeMillis();
		centuryApplication = new CenturyApplication(frameworkContext);
		System.out.println("******Test Case Name :" +testCaseName+ "******");
		
		//updated by harsh on 8/3/2016
		try{
				dataTable = new DataTable(testCaseName);
				dataDump = new DataDump(testCaseName,settings.getDumpLocation());
				
				if(settings.getPERerunStatus().equalsIgnoreCase("true")){				
					dataDump.loadData();
					/*dataDump.deleteValue("CSOLoggedIN");
					dataDump.deleteValue("CMLoggedIN");	*/
				}
			
		}catch(Exception e){				
			report.updateTestLog("VerfiyTestDataRow", "Test Data Row Not Present <Please check and add test data>",Status.FAIL);
			browser.quit();			
			System.out.println("Test Data Row not present. Please check");
		}	
		//#####################################################################
		
//	 beforeTestGetUserndURL();
    	
    }
	
    // updated the logic to check if datatable is null, then instantiate it
	@BeforeMethod
	public synchronized void setupData(Method testName) {
		System.out.println("In method :"+testName.getName());
		//System.out.println("inside comcast test before method: "+ testName.getName());
		startTime = System.currentTimeMillis();
		if(settings==null)
			settings=new TestSettings();
		if(browser==null)
			browser=getDriver(settings.getBrowser());
		
		// added by harsh on 8/2/2016
		if(dataTable == null){
			
			testCaseName=null;
			if(testName.getName().contains("[")){
				testCaseName = testName.getName().substring(0,testName.getName().indexOf('['));
			}else{
				testCaseName = testName.getName();
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
		
		beforeMethodGetUserndURL(testName);
		
		/*if(settings.getPERerunStatus().equalsIgnoreCase("true")){

				if(dataTable.getValue(testName.getName()+"_status").equalsIgnoreCase("pass")){
					Test test = testName.getAnnotation(Test.class);
					//method.getTestMethod().setInvocationCount(0);
					throw new SkipException("Test method: "+testName.getName()+" already passed and not required to be rerun");
				}
			
		}*/
		
	    		
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
		report.addTestLogSubHeading("Browser:", settings.getBrowser(), "ENV : "+settings.getEnvironmentToTest(), "URL :"+settings.getApplicationCMURL());
		
		report.addTestLogTableHeadings();
		//report.addTestLogSection("Detailed Steps");
		
	}

	
	@AfterMethod
	public synchronized void tearDown(ITestResult result){
		
		String methodStatus;
		endTime=System.currentTimeMillis();
		Long timeTaken=endTime-startTime;
		int h = (int) ((timeTaken / 1000) / 3600);
		int m = (int) (((timeTaken / 1000) / 60) % 60);
		int s = (int) ((timeTaken / 1000) % 60);
		String time=""+h+":hh "+m+":mm "+s+":ss";
		report.addTestLogFooter(time);
		
		// update teststatustable
		if(result.getStatus()==1){
			methodStatus = "PASS";
		}else{
			methodStatus = "FAIL";
			testStatus = "Failed";
		}
		
		System.out.println("The transaction with name: " + result.getMethod().getMethodName() + " took :" + time);
		
		dataDump.setValue(result.getMethod().getMethodName() + "_status", methodStatus);
		if(methodStatus.equalsIgnoreCase("fail")){
			dataDump.deleteValue("CSOLoggedIN");
			dataDump.deleteValue("CMLoggedIN");			
			//dataDump.
			try {
				//dataDump.dumpData(dataTable.getDataTable());				
				dataDump.dumpData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new SkipException("Test method: "+result.getMethod().getMethodName()+" failed. Quitting the test as other methods depend on it");

		}
	}
	
	//added by harsh on 8/3/2016
	@AfterTest(alwaysRun=true)
	public synchronized void afterTestMain(){
		if(settings.getUpdateALM().equalsIgnoreCase("true"))
			almRestUpdateStatus(); // added by harsh on 8/30/2016
		try {
			dataDump.deleteValue("CSOLoggedIN");
			dataDump.deleteValue("CMLoggedIN");
			dataDump.dumpData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*browser.close();
		browser.quit();*/
		
		
	}
	
	/**
	 * Get the browser object specified in the property
	 * @param browserName
	 * @return
	 */
	protected WebDriver getDriver(String browserName) {
		
		String gridflag = settings.getGRIDstatus();
		String gridip = settings.getGridIP();
		 final ThreadLocal<WebDriver> ThreadDriver =
		         new  ThreadLocal<WebDriver>();
		 WebDriver driver= ThreadDriver.get();
		
		
		

//		if (browserName.equalsIgnoreCase("firefox")) {	
//			driver= new FirefoxDriver();
//		}
		if (browserName.equalsIgnoreCase("firefox")) {
			
			if(gridflag.equals("false"))
			{	
				
/*				ProfilesIni profilesIni = new ProfilesIni();

				FirefoxProfile profile = profilesIni.getProfile("default");

//				profile.setAcceptUntrustedCertificates(true);

//				profile.setAssumeUntrustedCertificateIssuer(false);

				profile.setEnableNativeEvents(true);


				driver =ThreadGuard.protect( new FirefoxDriver(profile));
				ThreadDriver.set(driver); */
				
				// updated by hbolak01c on 9/15/2016 for FFv48+ support
				System.setProperty("webdriver.gecko.driver", 
						TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				//driver = ThreadGuard.protect(new MarionetteDriver(capabilities));
				driver = ThreadGuard.protect(new FirefoxDriver(capabilities));
				ThreadDriver.set(driver);

				
			}
			else{
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				try {
					driver =ThreadGuard.protect( new RemoteWebDriver(new URL("http://"+gridip+":4444/wd/hub"),capabilities));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				ThreadDriver.set(driver); 
			}
			driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		}
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", 
					TestUtils.getRelativePath()+"/src/main/resources/BrowserSpecificDrivers/chromedriver.exe");
	
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
							driver = ThreadGuard.protect(new RemoteWebDriver(new URL("http://"+gridip+":4444/wd/hub"), capabilities));
							//driver = new RemoteWebDriver(new URL("http://"+gridip+":4444/wd/hub"), capabilities);
							 ThreadDriver.set(driver);
						 	} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
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
						
						 driver = ThreadGuard.protect(new RemoteWebDriver(new URL("http://"+gridip+":4444/wd/hub"), capabilities));
						 ThreadDriver.set(driver);
					 	} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
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
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
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
			Method method = clazz.getMethod(testName.getName());
			System.out.println(testName.getName());

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
	
	public void beforeMethodGetUserndURL(Method testName) {
		  //check for rerun and the status of the method
		 userName=userDetails.getPassword(testName.getName());
		 
		 if (userName==null || userName=="")
		  {
			 	userName=getDataDump().getValue("currentUser");
			 			 if (userName==null || userName=="")
			 			  {
			 				 userName=userDetails.getPassword("default");
			 			  }
			 			  
		  }
		  
		 
		 if (getDataDump().getValue("CM_Status").equalsIgnoreCase("PASS")) //re run or 1st execution
			 {
				 if(!(getDataDump().getValue("CSOLoggedIN").equalsIgnoreCase("PASS")))
					 {
						
						 centuryApplication.openCSOUrl(userName,
								 false, frameworkContext);
						 if(settings.getPERerunStatus().equalsIgnoreCase("true")){ //for cm package execution 
							//	search for srid/customer need to be implemented
							 //(new HomePageCM(browser,report)).searchCustomer(getDataDump().getValue("CustomerName_RT"));
							}
						 getDataDump().setValue("CSOLoggedIN","PASS");
						 getDataDump().setValue("CMLoggedIN","FAIL");
						 getDataDump().setValue("currentUser", userName); 
					 
					 }
			 }
		 else if (!(getDataDump().getValue("CMLoggedIN").equalsIgnoreCase("PASS")))
		 {	 centuryApplication.openCMUrl(userName,
				 	false, frameworkContext);
		 
		 	if(settings.getPERerunStatus().equalsIgnoreCase("true")){ //for cm package execution 
				(new HomePageCM(frameworkContext)).searchCustomer(getDataDump().getValue("CustomerName_RT"));
			}
		 	
			 getDataDump().setValue("CMLoggedIN","PASS");
			 getDataDump().setValue("CSOLoggedIN","FAIL");
			 getDataDump().setValue("currentUser", userName);
		 }
		
		 
  		 if((getDataDump().getValue("CMLoggedIN").equalsIgnoreCase("PASS"))) 
		 {
			 if (!(getDataDump().getValue("currentUser").equalsIgnoreCase(userName)))
				 {
					 centuryApplication.openCMUrl(userName,
							 true, frameworkContext);
					 getDataDump().setValue("CSOLoggedIN","FAIL");
					 getDataDump().setValue("currentUser", userName);
				 }
		 }
		 
		 if((getDataDump().getValue("CSOLoggedIN").equalsIgnoreCase("PASS"))) 
		 {
			 if (!(getDataDump().getValue("currentUser").equalsIgnoreCase(userName)))
				 {
				 centuryApplication.openCSOUrl(userName,
						true, frameworkContext);
					 getDataDump().setValue("CMLoggedIN","FAIL");
					 getDataDump().setValue("currentUser", userName);
				 }
		 }
		 
		 
		 //CSOLoggedIN CMLoggedIN
		 /* if(settings.getPERerunStatus().equalsIgnoreCase("true")){
			  	userName=userDetails.getValue(testName.getName());
		  }*/
		  
		  
		   //centuryApplication = new CenturyApplication(frameworkContext);
			// CM nd CSO login -> added by rijin on 8/18/2016
			/* if ((getDataDump().getValue("CM_Status").equalsIgnoreCase("PASS")
					 	&& !(getDataDump().getValue("CSOLoggedIN").equalsIgnoreCase("PASS")))
					 ||(!(getDataDump().getValue("currentUser").equalsIgnoreCase(userName))))
				 	
			 {	     centuryApplication.openCSOUrl(userName);
					 getDataDump().setValue("CSOLoggedIN","PASS");
					 getDataDump().setValue("currentUser", userName);
			
			 }
			 
			 else if (!(getDataDump().getValue("CMLoggedIN").equalsIgnoreCase("PASS"))
					 || (!(getDataDump().getValue("currentUser").equalsIgnoreCase(userName))))
			 {
				 
					 centuryApplication.openCMUrl(userName);
					 getDataDump().setValue("CMLoggedIN","PASS");
					 getDataDump().setValue("currentUser", userName);
				 
				 
			 }
			 */
				 
	}
			 
public void ClearTempFilesndChngeProxy()  {
	String tmp;
	//String[] comcast={"cmd.exe", "/C", "Start", TestUtils.getRelativePath() + "/src/main/resources/ClearTmpFilesNdChngeComcastProxy.bat"};
	String comcast=TestUtils.getRelativePath() + "/src/main/resources/ClearTmpFilesNdChngeComcastProxy.bat";
	//String[] cognizant={"cmd.exe", "/C", "Start", TestUtils.getRelativePath() + "/src/main/resources/ClearTmpFilesNdChngeCognizantProxy.bat"};
	String cognizant=TestUtils.getRelativePath() + "/src/main/resources/ClearTmpFilesNdChngeCognizantProxy.bat";
	
	if(!settings.getGRIDstatus().equalsIgnoreCase("true"))
	{	tmp=settings.getClearTmpFilesNdChngeProxy();
	
		if(tmp.equalsIgnoreCase("comcast"))
	
		{
			
			executeScript(comcast);
		}		
		else if (tmp.equalsIgnoreCase("cognizant")) {
			
			executeScript(cognizant);
		}
	
	}
}


public static void executeScript(String script) {
    try {
        ProcessBuilder pb = new ProcessBuilder(script);
        Process p = pb.start(); // Start the process.
        p.waitFor(); // Wait for the process to finish.
        System.out.println("Script executed successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * MethodName:almRestUpdateStatus
 * Description: updates the status of the test in ALM with the corresponding execution test status
 * 
 */
public void almRestUpdateStatus(){
	
	System.out.println("Inside ALM Rest Update");
	  String reportPath=ReportPath.getInstance().getReportPath();
	  //String testCaseQCName=testName.getName();
	  createResultFile(reportPath,testStatus);
	  System.out.println(testStatus);
	  createZipFileOfReport(reportPath,testCaseName);	   
	  
	  endTime=System.currentTimeMillis();
	  Long timeTaken=endTime-startTestTime;
	  timeTaken1 = timeTaken1 + timeTaken;
	    int h = (int) ((timeTaken / 1000) / 3600);
		int m = (int) (((timeTaken / 1000) / 60) % 60);
		int s = (int) ((timeTaken / 1000) % 60);
		String time=""+m+":mm "+s+":ss";
		
		
	  	timeTaken = timeTaken/1000; //Converting to seconds
	    String duration = timeTaken.toString();
	    //++nTestsFailed;	 	

	  String testSetID=settings.getTestSetID();
	  if (!(testSetID == null || testSetID ==""))
	  {
		  	ALMTestInformation ALMInfo= new ALMTestInformation();
		  	ALMUpdaterClient ALMUpdate= new ALMUpdaterClient();
	  		String testID;
			try {
			    testSetID=settings.getTestSetID();
				//String testCaseNameToSend = "\""+testCaseName+"\"";
		    	testID = ALMInfo.GetTestID(testCaseName);
		    	ALMUpdate.createTestRUN(testSetID,testCaseName, testID, testStatus, duration);
		    
		    	String component = ALMInfo.getTestComponent(testID);
		  		reportSummary.updateResultSummary(component, testCaseName, time, testStatus);
					
		  	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
	  
	
	
}

	
	/**
	 * Name: FrameworkContext
	 * This reference holds all framework data that can be passed around
	 * @author hbolak01c
	 *
	 */
	public class FrameworkContext{
		
		public DataTable getDataTable(){
			return dataTable;
		}
		
		public IDataDump getDataDump(){
			return dataDump;
		}
		
		public void setDataDump(IDataDump dump){
			dataDump = dump;
		}
		
		public TestSettings getSettings(){
			return settings;
		}
		
		public WebDriver getDriver(){
			return browser;
		}
		
		public SeleniumReport getReport(){
			return report;
		}
		
		public IUserDetails getUserDetail(){
			return userDetails;
		}
		
		public String getTestCaseName(){
			return testCaseName;
		}
		
		public LoggerMain getTransactionLogger(){
			return transactionLogger;
		}
		
		public ContactTabPageCM getContactTabPageCM(){
			return contactTab;
		}
		
		public AddressTabPageCM getAddressTabPageCM(){
			return addressTab;
		}
		
		
	}
	 
}
