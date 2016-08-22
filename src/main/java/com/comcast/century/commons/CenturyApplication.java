package com.comcast.century.commons;

import org.openqa.selenium.WebDriver;

import com.comcast.century.cm.pages.LogInPage;
import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;
import com.comcast.utils.UserDetails;

public class CenturyApplication {
	private WebDriver browser;
	private SeleniumReport report;
	private String cm_url;
	private String cso_url;
	private String password;
	private String env;
	private Page page;
	private String domain;
	TestSettings settings=new TestSettings();
	UserDetails userDetails=new  UserDetails();


	public CenturyApplication(Object browser, SeleniumReport report) {
		this.browser = (WebDriver) browser;		
		this.cm_url= settings.getApplicationCMURL();
		this.cso_url= settings.getApplicationCSOURL();		
		this.report = report;
		this.env = settings.getEnvironmentToTest();
	}



	public void openCSOUrl(String userName, boolean alreadySameApp) {
		// TODO Auto-generated method stub
		System.out.println("Inside openCSOUrl");
		try{
			
			(new LogInPage(browser,report)).Signout(); //need to changed the log
			if(!alreadySameApp)
			{	
				browser.get(cso_url);
				browser.manage().window().maximize();
			}
			this.password= userDetails.getPassword(userName);
			this.domain=  settings.getAPPDOMAIN();
			(new LogInPage(browser,report)).applicationLoginCSO(userName,this.password,this.domain);
			report.updateTestLog("Century CM Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}



	public void openCMUrl(String userName, boolean alreadySameApp) {
		// TODO Auto-generated method stub
		System.out.println("Inside openCMUrl");
		try{
			(new LogInPage(browser,report)).Signout();
			if (!alreadySameApp)
			{
				browser.get(cm_url);
				browser.manage().window().maximize();
			}
			this.password= userDetails.getPassword(userName);
			this.domain= settings.getAPPDOMAIN();
			(new LogInPage(browser,report)).applicationLoginCM(userName,this.password,this.domain);			
			report.updateTestLog("Century CSO Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}
	
	}

/* @PerfTransaction(name="Login")
	public void openUrl(LoginDetails loginInfo) throws IOException {
		String strBrowser;
				if(env.equals("UAT")){
					strBrowser = (new settings()).getBrowser();			
					if(strBrowser.contains("ie")){
							 openUATapplicationBasicURL(loginInfo);
					}
					else{
						 openUATapplication(loginInfo);
					}
				}
					else if(env.equals("QA")){
						strBrowser = (new settings()).getBrowser();			
						if(strBrowser.contains("ie")){
								 openUATapplicationBasicURL(loginInfo);
						}	
						else{
							 openUATapplication(loginInfo);
						}
					}
					
	
						//openUATapplicationBasicURL(loginInfo);
						
				}
				
	
				
    public void openUrl(String user) throws IOException {
    	
		String strBrowser;
				if(env.equals("UAT")){
					strBrowser = (new settings()).getBrowser();			
					if(strBrowser.contains("ie")){
							 openUATapplicationBasicURL(user);
					}
					else{
						 openUATapplication(user);
					}
				}
					else if(env.equals("QA")){
						strBrowser = (new settings()).getBrowser();			
						if(strBrowser.contains("ie")){
								 openUATapplicationBasicURL(user);
						}	
						else{
							 openUATapplication(user);
						}
					}
					
	
						//openUATapplicationBasicURL(loginInfo);
						
				}
				
	
				else if(env.equals("DEV"))
				{
						openUATapplicationBasicURL(loginInfo);
						return new HomePage(browser,report);
				}
				
				else if(env.equals("PROD"))
				{
						openPRODapplicationBasicURL(loginInfo);
						return new HomePage(browser,report);
				}
				else 
				{
						//openQAapplicationBasicURL(loginInfo);
						openQAApplication(loginInfo);
						return new HomePage(browser,report);
				}
	
	
	public HomePage openRelevantApplication(LoginDetails loginInfo) throws IOException {
		 return new HomePage(browser, report);
	}
	

    public void openUATapplication(String user) {
		// TODO Auto-generated method stub
		
	}

	public void openUATapplicationBasicURL(String user) {
		// TODO Auto-generated method stub
		
	}

	public void openCMURL(LoginDetails loginInfo) {
		try{			
			browser.get(cm_url);
			browser.manage().window().maximize();
			(new LogInPage(browser,report)).applicationLogin(loginInfo);
			report.updateTestLog("Century CM Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}
	
	public void openCSOURL(LoginDetails loginInfo) {
		try{			
			browser.get(cso_url);
			browser.manage().window().maximize();
			(new LogInPage(browser,report)).applicationLogin(loginInfo);
			report.updateTestLog("Century CSO Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}
	public HomePage openPRODapplicationBasicURL(LoginDetails loginInfo) {
		try{
			String strUserName = loginInfo.userID;
			strUserName = strUserName.replace("\\", "%5C");
			String strPassword = loginInfo.password;
			strPassword = strPassword.replace("@", "%40");
			strPassword = strPassword.replace("+", "%2B");
			String strAuthUrl = strUserName +  ":" + strPassword + "@einstein360.";
			String[] arrurl = url.split("einstein360.");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1];
			System.out.println(authUrl);			
			browser.get(authUrl);
			//handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			report.updateTestLog("Einstein Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		return new HomePage(browser,report);
	}
	
	*//**
     *  Method to handle basic authentication for QA
     * @author     : Vishwas Patil (vvinay00c)
    ***//*
	public HomePage openQAapplicationBasicURL(LoginDetails loginInfo) {
		try{
			String strUserName = loginInfo.userID;
			String strPassword = loginInfo.password;
			strPassword = strPassword.replace("@", "%40");
			strPassword = strPassword.replace("+", "%2B");
			String strAuthUrl = strUserName +  ":" + strPassword + "@mcs";
			String[] arrurl = url.split("mcs");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1]; 			
			browser.get(authUrl);
			System.out.println(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			//browser.findElement(By.id("btnActivate")).click();
			sleep(12000);
			browser.manage().window().maximize();
			//browser.get(authUrl);
			report.updateTestLog("ApplicationLaunch", "Application has been successfully launched", Status.SCREENSHOT);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		return new HomePage(browser,report);
	}
	
	//@PerfTransaction(name="Login")
	public void openUATapplication(LoginDetails loginInfo) {
			browser.get(cm_url);
			browser.manage().window().maximize();
			(new LogInPage(browser,report)).applicationLogin(loginInfo);
	}
	
	
	*//**
     *  Method to handle basic authentication for QA
     * @author     : Vishwas Patil (vvinay00c)
    ***//*
	public HomePage openDEVapplicationBasicURL(LoginDetails loginInfo) {
		try{
			String strUserName = loginInfo.userID;
			String strPassword = loginInfo.password;
			strPassword = strPassword.replace("@", "%40");
			strPassword = strPassword.replace("+", "%2B");
			String strAuthUrl = strUserName +  ":" + strPassword + "@mcs";
			String[] arrurl = url.split("mcs");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1]; 			
			browser.get(authUrl);
			System.out.println(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			browser.get(cm_url);
			report.updateTestLog("ApplicationLaunch", "Application has been successfully launched", Status.SCREENSHOT);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		return new HomePage(browser,report);
	}
	*//**
     *  Method to handle basic authentication in CI Login
     * @author     : Vishwas Patil (vvinay00c)
    ***//*
	
	public HomePage openRQAapplicationBasicURL(LoginDetails loginInfo) {
		
			try{
			String strUserName = loginInfo.userID;
			String strPassword = loginInfo.password;
			String strAuthUrl = strUserName +  ":" + strPassword + "@mcw";
			String[] arrurl = url.split("mcw");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1]; 			
			browser.get(authUrl);
			System.out.println(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			
			sleep(20000); // To enable Resiliency QA to wait for page Load.
			
			browser.manage().window().maximize();
			//browser.get(cm_url);
			if(isAlertPresent()){				
				String alertText = closeAlertAndReturnText();
				System.out.print("\nAlert was present here."+alertText);
			}
			report.updateTestLog("Open Resiliency QA Application", "Application has been successfully launched", Status.DONE);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		
		return new HomePage(browser,report);
		
	}
	
	public LogInPage openApplication() {
		try {
			browser.get(cm_url);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			report.updateTestLog("Open Application", "Application has been successfully launched", Status.DONE);
			return new LogInPage(browser,report);
		} catch (UnreachableBrowserException bx) {
			sleep(10000);
			try{
				report.updateTestLog("Open Application", "Application has been successfully launched", Status.DONE);
				return new LogInPage(browser,report);
			}catch(RuntimeException ex){
				browser.quit();
				report.updateTestLog("Open Application", ex.getMessage(), Status.FAIL);
				throw ex;
			}
		}
		catch (RuntimeException ex) {
			report.updateTestLog("Open Application", ex.getMessage(), Status.FAIL);
			throw ex;
		}
	}

	


	public HomePage openQAApplication(LoginDetails loginInfo) {
		try {			
			String[] filepath=new String[] { TestUtils.getRelativePath()+"\\src\\main\\resources\\AutoIt\\QALoginAll1.exe",settings.getBrowser(),loginInfo.userID,loginInfo.password};
			try {
				Runtime.getRuntime().exec(filepath);

			} catch (IOException e) {
				System.out.println("Unable to close");
			}

			browser.get(cm_url);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			sleep(20000);//{to enable for QA url running with AUTO IT
			report.reportDoneEvent("Enter UN and PWD", "UserName is->"+loginInfo.userID +"  Password is->"+loginInfo.password.replaceAll(".", "*"));
			browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			browser.manage().window().maximize();
			if(browser.getTitle().contains("Einstein"))
			{
				report.updateTestLog("Open QA Application","Application has been successfully launched", Status.DONE);
				report.reportPassEvent("ApplicationLaunch", "Application launched successfully");
			}else report.reportFailEvent("ApplicationLaunch", "Application not launched successfully");
			
			return new HomePage(browser,report);
		} catch (RuntimeException ex) {
			report.updateTestLog("Open Application", ex.getMessage(), Status.FAIL);
			throw ex;
		}
	}

	public HomePage openQAApplication(String userName) {
		try {


			String filepath=TestUtils.getRelativePath()+"\\src\\main\\resources\\AutoIt\\"+userName+".exe";
			try {
				Runtime.getRuntime().exec(filepath);

			} catch (IOException e) {
				System.out.println("Unable to close");;
			}

			browser.get(cm_url);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			sleep(20000);//{to enable for QA url running with AUTO IT

			browser.manage().window().maximize();
			report.updateTestLog("Open QA Application", "Application has been successfully launched", Status.DONE);
			return new HomePage(browser,report);
		} catch (RuntimeException ex) {
			report.updateTestLog("Open Application", ex.getMessage(), Status.FAIL);
			throw ex;
		}
	}

	public HomePage openKMApplication(LoginDetails loginInfo) throws IOException {

		if(env.equals("UAT")){
			//openApplication().applicationLoginKM(loginInfo);
			openUATapplication(loginInfo);
			return new HomePage(browser,report);
		}else if(env.equals("SOAK")){
			openKM_SOAKApplication(loginInfo);
			return new HomePage(browser,report);
		}else if((env.equals("PROD"))){
			openProdApplicationBVT(loginInfo);
			return new HomePage(browser,report);
		}else{
			openQAApplication(loginInfo);
			return new HomePage(browser,report);
		}
	}
	
	
	public HomePage openKM_SOAKApplication(LoginDetails loginInfo) {
		try{
			String strUserName = loginInfo.userID;
			strUserName = strUserName.replace("\\", "%5C");
			String strPassword = loginInfo.password;
			strPassword = strPassword.replace("#", "%23");
			String strAuthUrl = strUserName +  ":" + strPassword + "@";
			String[] arrurl = url.split("http://");
			arrurl[0] = "http://"+ strAuthUrl + arrurl[1];
//			String authUrl = arrurl[0] + arrurl[1]; 	
			String authUrl = arrurl[0];
			browser.get(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			report.updateTestLog("Open SOAK Application", "Application has been successfully launched", Status.DONE);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		return new HomePage(browser,report);
	}
	
	
	
	public HomePage openProdApplicationBVT(LoginDetails loginInfo){
		try{
			String strUserName = loginInfo.userID;
			strUserName = strUserName.replace("\\", "%5C");
			String strPassword = loginInfo.password;
			strPassword = strPassword.replace("@", "%40");
			String strAuthUrl = strUserName +  ":" + strPassword + "@einstein.";
			String[] arrurl = url.split("einstein.");
			arrurl[0] = arrurl[0] + strAuthUrl;
			String authUrl = arrurl[0] + arrurl[1]; 			
			browser.get(authUrl);
			handleIECertificateErrorAndSafariCertificatePopup(browser);
			browser.manage().window().maximize();
			report.updateTestLog("Open SOAK Application", "Application has been successfully launched", Status.DONE);
		}catch(Exception Ex){
			report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
		return new HomePage(browser,report);
	}
	
	
	public synchronized void close() throws IOException, Exception {
		  String testSetID=settings.getTestSetID();
		  try{
			
			System.out.println("********Quitting Browser********");
			Thread.sleep(2000);
		//	  if ((testSetID == null || testSetID ==""))         
	    //	  {
	    		  	this.browser.quit();
	    //	  }	  
			System.out.println("********Browser was Quit********");
					
		}catch(UnreachableBrowserException ue){
			System.out.println("Exception caught");
			
		}finally
		{
		//   if ((testSetID == null || testSetID ==""))
		//	{ 
					sleep(2500);
					if(isProcessRunging("iexplore.exe"))
					{
						killProcess("iexplore.exe");
						System.out.println("killing iexplore");
					}
					if(isProcessRunging("IEDriverServer.exe"))
					{
						killProcess("IEDriverServer.exe");
						System.out.println("killing iedriverserver.exe");
					}
					if(isProcessRunging("IEDriverServer_32.exe"))
					{
						killProcess("IEDriverServer_32.exe");
						System.out.println("killing IEDriverServer_32.exe");
					}
					if(isProcessRunging("WerFault.exe"))
					{
						killProcess("WerFault.exe");
						System.out.println("killing iedriverserver.exe");
					}
					if(isProcessRunging("chrome.exe"))
					{
						killProcess("chrome.exe");
						System.out.println("killing chrome.exe");
					}
					if(isProcessRunging("chromedriver.exe"))
					{
						killProcess("chromedriver.exe");
						System.out.println("killing chromedriver.exe");
					}
					String filepath1=TestUtils.getRelativePath()+"\\src\\main\\resources\\AutoIt\\clear.bat";
					if(isProcessRunging("iexplore.exe"))
					{
						killProcess("iexplore.exe");
						System.out.println("killing iexplore");
					}
					try {
						Runtime.getRuntime().exec(filepath1);
						sleep(2000);
					} catch (IOException e) {
						System.out.println("Unable to close");;
					}	
		//	}
		}

	}
	
	public void cleanup() throws IOException, Exception {
	
			sleep(2500);
			this.deleteAllCookies();
			System.out.println("********Starting Cleanup********");
			if(isProcessRunging("iexplore.exe"))
			{
				killProcess("iexplore.exe");
				System.out.println("killing iexplore");
			}
			
			
			if(isProcessRunging("IEDriverServer.exe"))
			{
				killProcess("IEDriverServer.exe");
				System.out.println("killing iedriverserver.exe");
			}
			
			if(isProcessRunging("IEDriverServer_32.exe"))
			{
				killProcess("IEDriverServer_32.exe");
				System.out.println("killing IEDriverServer_32.exe");
			}
			if(isProcessRunging("WerFault.exe"))
			{
				killProcess("WerFault.exe");
				System.out.println("killing iedriverserver.exe");
			}
			if(isProcessRunging("chrome.exe"))
			{
				killProcess("chrome.exe");
				System.out.println("killing chrome.exe");
			}
			if(isProcessRunging("chromedriver.exe"))
			{
				killProcess("chromedriver.exe");
				System.out.println("killing chromedriver.exe");
			}
			if(isProcessRunging("cmd.exe"))
			{
				killProcess("cmd.exe");
				System.out.println("killing cmd.exe");
		}	
			System.out.println("********Cleanup Complete********");
			
			String filepath1=TestUtils.getRelativePath()+"\\src\\main\\resources\\AutoIt\\clear.bat";
			
			try {
				Runtime.getRuntime().exec(filepath1);
				sleep(2000);
			} catch (IOException e) {
				System.out.println("Unable to close");;
			}			
		

	}
	
	
	
	
	
	

	
	public void closeNavigationPopUp() throws IOException, Exception {
		String filepath=TestUtils.getRelativePath()+"\\src\\main\\resources\\AutoIt\\closeNavigationPop.exe";
		try {
			Runtime.getRuntime().exec(filepath);

		} catch (IOException e) {
			System.out.println("Unable to close");;
		}

	}



	public void renavigateToApplicationUrl() {
		browser.get(cm_url);
		sleep(5000);
	}

	*//**
	 * 
	 *//*
	private synchronized void deleteAllCookies() {
		try{
			this.browser.manage().deleteAllCookies();
		}
		catch(SessionNotFoundException ex){
			System.out.println(ex.getMessage());
		}
	}

	*//**	
	 * Handle Certification error while opening HTTPS website
	 * @param browser
	 *//*
	public void handleIECertificateErrorAndSafariCertificatePopup(WebDriver browser) {
		try{
			//if(page.isElementPresent(By.id("overridelink"))){
		    if(true){	
				String browserName = settings.getBrowser();
				String url= browser.getCurrentUrl();
				if(browserName.equalsIgnoreCase("iexplore") && url.contains("http")){
					browser.navigate().to("javascript:document.getElementById('overridelink').click()");
				}		
			}}
		catch(RuntimeException ex){
		}
	}

	*//**
	 * Method to wait for Session timeout after closing the browser 
	 *//*
	protected void waitForApplicationSessionTimeOutAfterClosingBrowser(){
		try{
			sleep(18000);
			report.reportDoneEvent("waitForApplicationSessionTimeOutAfterClosingBrowser", "step successfull");
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * method to make a thread sleep for customized time in milliseconds
	 * @param milliseconds
	 *//*
	protected void sleep(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	

	*//**
	 * Delete current session cookies
	 *
	 *//*	     
	protected void deleteCurrentSessionCookies() {
		browser.manage().deleteAllCookies();  
		report.reportPassEvent("deleteSessionCookiesAndCloseApplication", "Closed the current session");
	} 


	public String closeAlertAndReturnText(){
		String alertMessage=null;
		WebDriverWait wait = new WebDriverWait(browser, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = browser.switchTo().alert();
		alertMessage=alert.getText();
		alert.accept();
		return alertMessage;
	}

	*//***
	 * Method to check if alert is present
	 * 
	 * @param : 
	 * @return :
	 * @author : Shravanth PAV(250787) Modified By :
	 ***//*
	public boolean isAlertPresent(){
		try{
			browser.switchTo().alert();
			return true;
		}//try
		catch(Exception e){
			return false;
		}//catch
	}
	
	
	

	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /IM ";

	public static boolean isProcessRunging(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			//System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;

	}

	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);

	}

	public void openCSOUrl(String userName) {
		String strBrowser;
		if(env.equals("UAT")){
			strBrowser = (new settings()).getBrowser();		
			openUATapplication(userName);
			if(strBrowser.contains("ie")){
					 openUATapplicationCSOURL(userName);
					 openUATapplication(user);
			}
			else{
				 openUATapplication(userName);
			}
		}
			else if(env.equals("QA")){
				strBrowser = (new settings()).getBrowser();			
				if(strBrowser.contains("ie")){
						 openUATapplicationCSOURL(userName);
				}	
				else{
					 openUATapplication(userName);
				}
			}
			

				//openUATapplicationBasicURL(loginInfo);
				
		}

	public void openCMUrl(String userName) {
		// TODO Auto-generated method stub
		
	}
		
	}*/

