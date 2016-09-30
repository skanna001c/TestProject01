package com.comcast.commons;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;

import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.LogInPage;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.IDataDump;
import com.comcast.utils.IUserDetails;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;

public class Application {
	private WebDriver browser;
	private SeleniumReport report;
	private String cm_url;
	private String cso_url;
	private String password;
	private String env;
	private Page page;
	private String domain;
	private TestSettings settings;
	private IUserDetails userDetails;
	private IDataDump dataDump;
    private FrameworkContext frameworkContext;

	public Application(Object browser, SeleniumReport report) {
		this.browser = (WebDriver) browser;		
		this.cm_url= settings.getApplicationCMURL();
		this.cso_url= settings.getApplicationCSOURL();		
		this.report = report;
		this.env = settings.getEnvironmentToTest();
	}
	
	public Application(FrameworkContext context){
		this.frameworkContext = context;
		this.browser = context.getDriver();	
		this.settings = context.getSettings();
		this.userDetails = context.getUserDetail();
		this.cm_url= settings.getApplicationCMURL();
		this.cso_url= settings.getApplicationCSOURL();		
		this.report = context.getReport();
		this.env = settings.getEnvironmentToTest();
		this.dataDump = context.getDataDump();
		
	}



	public void openCSOUrl(String userName, boolean alreadySameApp) {
		// TODO Auto-generated method stub
		System.out.println("Inside openCSOUrl");
		System.out.println("browser.getTitle()"+browser.getTitle()+"#"+browser.getTitle().length());
		try{
			if(!(browser.getTitle().equalsIgnoreCase("WebDriver")
					||browser.getTitle().length()==0))
				{
					(new LogInPage(frameworkContext)).Signout();
				}
				
			
			if(!alreadySameApp)
			{	
				browser.get(cso_url);
				browser.manage().window().maximize();
//				(new LogInPage(browser,report)).Signout();
			}
			this.password= userDetails.getPassword(userName);
			this.domain=  settings.getAPPDOMAIN();
			(new LogInPage(frameworkContext)).applicationLoginCSO(userName,this.password,this.domain);
			report.updateTestLog("Century CM Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "");
		
		}
	}

	public void openCSOUrl(String userName, boolean alreadySameApp, FrameworkContext context) {
		// TODO Auto-generated method stub
		System.out.println("Inside openCSOUrl");
		System.out.println("browser.getTitle()"+browser.getTitle()+"#"+browser.getTitle().length());
		try{
			if(!(browser.getTitle().equalsIgnoreCase("WebDriver")
					||browser.getTitle().length()==0))
				{
					(new LogInPage(context)).Signout();
				}
				
			
			if(!alreadySameApp)
			{	
				browser.get(cso_url);
				browser.manage().window().maximize();
//				(new LogInPage(browser,report)).Signout();
			}
			this.password= userDetails.getPassword(userName);
			this.domain=  settings.getAPPDOMAIN();
			(new LogInPage(context)).applicationLoginCSO(userName,this.password,this.domain);
			report.updateTestLog("Century CM Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "");
		
		}
	}


	public void openCMUrl(String userName, boolean alreadySameApp) {
		// TODO Auto-generated method stub		
		try{
			if(!(browser.getTitle().equalsIgnoreCase("WebDriver")
					||browser.getTitle().length()==0))
				{
					(new LogInPage(frameworkContext)).Signout();
				}
				
			if (!alreadySameApp)
			{
				browser.get(cm_url);				
				browser.manage().window().maximize();
				(new LogInPage(frameworkContext)).Signout();
			}
			this.password= userDetails.getPassword(userName);
			this.domain= settings.getAPPDOMAIN();
			(new LogInPage(frameworkContext)).applicationLoginCM(userName,this.password,this.domain);			
			report.updateTestLog("Century CSO Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}
	

	public void openCMUrl(String userName, boolean alreadySameApp, FrameworkContext context) {
		// TODO Auto-generated method stub		
		try{
			if(!(browser.getTitle().equalsIgnoreCase("WebDriver")
					||browser.getTitle().length()==0))
				{
					(new LogInPage(context)).Signout();
				}
				
			if (!alreadySameApp)
			{
				browser.get(cm_url);				
				browser.manage().window().maximize();
				(new LogInPage(context)).Signout();
			}
			this.password= userDetails.getPassword(userName);
			this.domain= settings.getAPPDOMAIN();
			(new LogInPage(context)).applicationLoginCM(userName,this.password,this.domain);			
			report.updateTestLog("Century CSO Application Launch", "Application has been launched", Status.SCREENSHOT);
		}catch(Exception Ex){
		report.reportFailEvent("Exception Caught", "Message is->"+Ex.getMessage());
		}
	}
	
	public void beforeMethodGetUserndURL(Method testName) {
		String newTestName;
		String userName;
		  //check for rerun and the status of the method
		if(userDetails.containsTestName(testName.getName().trim())) {
			userName=userDetails.getUserName(testName.getName());
		}
		else{
				newTestName=testName.getName().trim();
			if (newTestName.lastIndexOf("_")>0){	
				newTestName= newTestName.substring(0,newTestName.lastIndexOf("_")); 
					
			}
				userName=userDetails.getUserName(newTestName);
			}
		
		 
		 if (userName==null || userName=="")
		  {
			 	userName=dataDump.getValue("currentUser");
			 			 if (userName==null || userName=="")
			 			  {
			 				 userName=userDetails.getPassword("default");
			 			  }
			 			  
		  }
//####################################################################################
	if(testName.getName().trim().toLowerCase().startsWith("startcm"))
	{
		 openCMUrl(userName,
				 	false, frameworkContext);		 
		 	if(settings.getPERerunStatus().equalsIgnoreCase("true")){ //for cm package execution 
				(new HomePageCM(frameworkContext)).searchCustomer(dataDump.getValue("CustomerName_RT"));
			}
		 	
			 dataDump.setValue("CMLoggedIN","PASS");
			 dataDump.setValue("CSOLoggedIN","FAIL");
			 dataDump.setValue("currentUser", userName);
			 dataDump.setValue("CM_Status", "FAIL");
	}
//#######################################################################			 
		 
	else if (dataDump.getValue("CM_Status").equalsIgnoreCase("PASS")) //re run or 1st execution
			 {
				 if(!(dataDump.getValue("CSOLoggedIN").equalsIgnoreCase("PASS")))
					 {
						
						 openCSOUrl(userName,
								 false, frameworkContext);
						 if(settings.getPERerunStatus().equalsIgnoreCase("true")){ //for cm package execution 
							//	search for srid/customer need to be implemented
							 //(new HomePageCM(browser,report)).searchCustomer(dataDump.getValue("CustomerName_RT"));
							}
						 dataDump.setValue("CSOLoggedIN","PASS");
						 dataDump.setValue("CMLoggedIN","FAIL");
						 dataDump.setValue("currentUser", userName); 
					 
					 }
			 }
		 else if (!(dataDump.getValue("CMLoggedIN").equalsIgnoreCase("PASS")))
		 {	 openCMUrl(userName,
				 	false, frameworkContext);
		 
		 	if(settings.getPERerunStatus().equalsIgnoreCase("true")){ //for cm package execution 
				(new HomePageCM(frameworkContext)).searchCustomer(dataDump.getValue("CustomerName_RT"));
			}
		 	
			 dataDump.setValue("CMLoggedIN","PASS");
			 dataDump.setValue("CSOLoggedIN","FAIL");
			 dataDump.setValue("currentUser", userName);
		 }
		
//########################################################################################################################
  		 if((dataDump.getValue("CMLoggedIN").equalsIgnoreCase("PASS"))) 
		 {
			 if (!(dataDump.getValue("currentUser").equalsIgnoreCase(userName)))
				 {
					 openCMUrl(userName,
							 true, frameworkContext);
					 dataDump.setValue("CSOLoggedIN","FAIL");
					 dataDump.setValue("currentUser", userName);
				 }
		 }
		 
		 if((dataDump.getValue("CSOLoggedIN").equalsIgnoreCase("PASS"))) 
		 {
			 if (!(dataDump.getValue("currentUser").equalsIgnoreCase(userName)))
				 {
				 openCSOUrl(userName,
						true, frameworkContext);
					 dataDump.setValue("CMLoggedIN","FAIL");
					 dataDump.setValue("currentUser", userName);
				 }
		 }
		 
		 
		 //CSOLoggedIN CMLoggedIN
		 /* if(settings.getPERerunStatus().equalsIgnoreCase("true")){
			  	userName=userDetails.getValue(testName.getName());
		  }*/
		  
		  
		   //application = new application(frameworkContext);
			// CM nd CSO login -> added by rijin on 8/18/2016
			/* if ((dataDump.getValue("CM_Status").equalsIgnoreCase("PASS")
					 	&& !(dataDump.getValue("CSOLoggedIN").equalsIgnoreCase("PASS")))
					 ||(!(dataDump.getValue("currentUser").equalsIgnoreCase(userName))))
				 	
			 {	     application.openCSOUrl(userName);
					 dataDump.setValue("CSOLoggedIN","PASS");
					 dataDump.setValue("currentUser", userName);
			
			 }
			 
			 else if (!(dataDump.getValue("CMLoggedIN").equalsIgnoreCase("PASS"))
					 || (!(dataDump.getValue("currentUser").equalsIgnoreCase(userName))))
			 {
				 
					 application.openCMUrl(userName);
					 dataDump.setValue("CMLoggedIN","PASS");
					 dataDump.setValue("currentUser", userName);
				 
				 
			 }
			 */
				 
	}
	
	public void cleanDump(IDataDump dataDump){
		dataDump.deleteValue("CSOLoggedIN");
		dataDump.deleteValue("CMLoggedIN");	
	}
	
	
	}
