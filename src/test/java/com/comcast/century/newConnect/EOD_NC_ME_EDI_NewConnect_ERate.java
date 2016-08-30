package com.comcast.century.newConnect;

import java.awt.AWTException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.century.cso.pages.CompleteCoaxBuildTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildCoaxTaskPage;
import com.comcast.century.cso.pages.ConductCoaxSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyCoaxTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.PerfTransaction;

public class EOD_NC_ME_EDI_NewConnect_ERate extends NewConnectTest {
	
	private CustomerInfo customerInfo;
	private AccountInfo accountInfo;
	private ContactInfo contactInfo;
	private SiteInfo siteInfo;
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;	
	
	String SRID;
	String SurveyID;
	String Site1;
		
  /*@BeforeMethod
  public void beforeMethod() {
	  //check for rerun and the status of the method
	  if (userName==null)
	  {
		  userName="ordermanagementbat1";
	  } 
	  else userName=getDataDump().getValue("userName");
	  
	   centuryApplication = new CenturyApplication(browser, report);
		// CM nd CSO login -> added by rijin on 8/18/2016
		 if (getDataDump().getValue("CM_Status").equalsIgnoreCase("PASS")
				 	&& !(getDataDump().getValue("CSOLoggedIN").equalsIgnoreCase("PASS")))
		 {
			 centuryApplication.openCSOUrl(userName);
			 getDataDump().setValue("CSOLoggedIN","PASS");
		 }
		 else if (!(getDataDump().getValue("CMLoggedIN").equalsIgnoreCase("PASS")))
		 {
			 centuryApplication.openCMUrl(userName);
			 getDataDump().setValue("CMLoggedIN","PASS");
			 
		 }
  }*/
  
  @BeforeClass
  public void beforeClassTest() {
			  
	  	loadDataTest();
//Search for customer if rerun - added by harsh on 8/8/16
		
  } 
  
  @Test(priority=500)
  @PerfTransaction(name="SelectServiceEDIOnly")
  public void selectService() throws InterruptedException{
	  (new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		if((new ServiceTabPageCM(browser, report)).SelectPricePlan()){
			if((new ServiceTabPageCM(browser, report)).EDI()){				
					if((new ServiceTabPageCM(browser, report)).ClickOnContinueButton()){
						(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();					
					}else Assert.fail("Click continue button failed");				
			}else Assert.fail(" Select EDI plan failed");
		}else Assert.fail(" Select service plan failed");
	  
  }
  
  @Test(priority=600)
  @PerfTransaction(name="ProcessServiceEDIOnly")
  public void processService() throws InterruptedException{
	  	if(getDataDump().getValue("ProcessServiceEDIOnly_status").equalsIgnoreCase("FAIL"))
	  	{
	  		selectService();
	  	}
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		getDataDump().setValue("SRID_RT", SRID);
		if((new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1)){
			if((new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo)){				
					if((new ProcessTabPageCM(browser, report)).ClickOnContinueButton()){
					}else Assert.fail("Click on continue button failed");				
			}else Assert.fail("EVC configuration failed");
		}else Assert.fail("UNI configuration failed");
  }	  
  
  
  @Test(priority=700)
  @PerfTransaction(name="submitOrderErate")
  public void submitOrder() throws InterruptedException, AWTException{
	 if(getDataDump().getValue("submitOrder_status").equalsIgnoreCase("FAIL"))
	  	{
		selectService();
	  		processService();
	  	}
	   
		//if((new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo)){
			if((new OrderSummaryTabCMPage(browser, report)).enterOrderDetails(orderSummaryInfo)){
				if((new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo)){
					if((new OrderSummaryTabCMPage(browser, report)).Attachments(orderSummaryInfo)){
						if((new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton()){						
							getDataDump().setValue("CM_Status","PASS");						
						}else Assert.fail("Order submission failed");
					}else Assert.fail("Uploading the Tax Excemption form failed");
				}else Assert.fail("Entering NRC values failed");
			}else Assert.fail("Entering order detrails failed");
		}//else Assert.fail("Assigning label failed");	
	
   
  
  @Test(priority=801)
  @PerfTransaction(name="Conduct_Site_Survey_Coax")
  public void Conduct_Site_SurveyCoax() throws InterruptedException {	
	  if (getDataDump().getValue("Conduct_Site_Survey_Coax_status").equalsIgnoreCase("fail"))
	  {
		  CSOSearchForOrderInSO();
	  }
	  (new SiteLevelTasks(browser, report)).ConductSiteSurveyCoax();
	  (new ConductSiteSurveyCoaxTaskPage(browser, report)).ConductSiteSurveyCoax(siteLevelTaskInfo);
  }	
  
  @Test(priority=100)
  @PerfTransaction(name="Obtain_Site_Agreement")
  public void Obtain_Site_Agreement(Method method) throws InterruptedException {
	  (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
	  (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
	  (new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
	  (new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
  }	
  
  @Test(priority=110)
  @PerfTransaction(name="Conduct_Coax_Survey")
  public void Conduct_Coax_Survey() throws InterruptedException, AWTException {
	  (new SiteLevelTasks(browser, report)).ConductCoaxSurvey();
	 (new ConductCoaxSurveyTaskPage(browser, report)).ConductCoaxSurvey(siteLevelTaskInfo);
		
  }	
  
  @Test(priority=120)
  @PerfTransaction(name="Build_House_Account")
  public void Build_House_Account() throws InterruptedException, AWTException {
	  (new SiteLevelTasks(browser, report)).BuildHouseAccount();
	 (new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		
  }	
  
  @Test(priority=130)
  @PerfTransaction(name="Obtains_Coax_Permits")
  public void Obtains_Coax_Permits() throws InterruptedException, AWTException {
	  (new SiteLevelTasks(browser, report)).ObtainCoaxPermits();
	  (new ObtainCoaxPermitsTaskPage(browser, report)).ObtainCoaxPermits();
		
  }
  
  @Test(priority=140)
  @PerfTransaction(name="Complete_Site_Build_Coax")
  public void Complete_Site_Build_Coax() throws InterruptedException, AWTException {
	  (new SiteLevelTasks(browser, report)).CompleteSiteBuildCoax();
	  (new CompleteSiteBuildCoaxTaskPage(browser, report)).ClickCompleteButton();
		
  }
  
  @Test(priority=150)
  @PerfTransaction(name="Complete_Coax_Build")
  public void Complete_Coax_Build() throws InterruptedException, AWTException {
	  (new SiteLevelTasks(browser, report)).CompleteCoaxBuild();
	  (new CompleteCoaxBuildTaskPage(browser, report)).ClickCompleteButton();
		
  }
  
  
    
/*	(new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
	(new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
	(new SiteLevelTasks(browser, report)).BuildHouseAccount();
	(new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
	(new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
	(new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
	(new SiteLevelTasks(browser, report)).CompleteSiteBuild();
	(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
	(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
	(new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
	(new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
	(new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
	(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
	(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
	(new ServiceLevelTasks(browser, report)).ContactCustomer();
	(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
	(new ServiceLevelTasks(browser, report)).UpdateDesign();
	(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
	(new SiteLevelTasks(browser, report)).ClickBackButton();
	(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
	serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
	(new ServiceLevelTasks(browser, report)).BULBA();
	(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
	(new ServiceLevelTasks(browser, report)).ShipCPE();
	(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
	(new ServiceLevelTasks(browser, report)).CAE();
	(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
	(new ServiceLevelTasks(browser, report)).ADI();
	(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
	(new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
	new GenerateCoreConfigsTaskPage(browser, report).ClickCompleteButton();
	(new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
	(new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
	(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
	(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
	(new ServiceLevelTasks(browser, report)).InstallCPE();
	(new InstallCPETaskPage(browser, report)).InstallCPE();
	(new ServiceLevelTasks(browser, report)).SetCriticalDates();
	(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
	(new ServiceLevelTasks(browser, report)).DayofConfigs();
	(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
	(new ServiceLevelTasks(browser, report)).ActivateService();
	(new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
	(new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
	(new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
	(new ServiceLevelTasks(browser, report)).CCAT();
	(new CCATTaskPage(browser, report)).ClickCompleteButton();
	(new ServiceLevelTasks(browser, report)).StartBilling();
	(new ServiceLevelTasks(browser, report)).ClickBackButton();*/
  
  
  public void CSOSearchForOrderInSO()
  {
	  (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
	  (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
	  
  }
  
  @AfterMethod
  public void afterMethod() {
	  //set the status of the method
  }



  @AfterTest
  public void afterTest() {
  }

  public void loadDataTest(){
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
	//	loginInfo = LoginDetails.loadFromDatatable(dataTable);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
	}
  
	
}
