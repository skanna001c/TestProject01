package com.comcast.century.newConnect;

import java.awt.AWTException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cso.pages.ADITaskPage;
import com.comcast.century.cso.pages.ActivateServiceTaskPage;
import com.comcast.century.cso.pages.BULBATaskPage;
import com.comcast.century.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.century.cso.pages.CAETaskPage;
import com.comcast.century.cso.pages.CCATTaskPage;
import com.comcast.century.cso.pages.CompleteCoaxBuildTaskPage;
import com.comcast.century.cso.pages.CompleteFiberPlantBuildTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildCoaxTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildTaskPage;
import com.comcast.century.cso.pages.CompleteWavelengthReservationTaskPage;
import com.comcast.century.cso.pages.ConductCoaxSurveyTaskPage;
import com.comcast.century.cso.pages.ConductFiberPlantSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyCoaxTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.CreateOrderBillingPackageTaskPage;
import com.comcast.century.cso.pages.DaysOfConfigsTaskPage;
import com.comcast.century.cso.pages.EqFeeFlowTasks;
import com.comcast.century.cso.pages.EqFeeStartBillingTaskPage;
import com.comcast.century.cso.pages.GenerateCPEConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCoreConfigsTaskPage;
import com.comcast.century.cso.pages.InstallCPETaskPage;
import com.comcast.century.cso.pages.InstallCPE_CoaxTaskPage;
import com.comcast.century.cso.pages.LoadCoreConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceInstallationTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainFiberPlantPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.ShipCPETaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.UpdateDesignTaskPage;
import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class NewConnectTest extends ComcastTest {
	
	private CustomerInfo customerInfo;
	private AccountInfo accountInfo;
	private ContactInfo contactInfo;
	private SiteInfo siteInfo;
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;
	private ServiceLevelTaskInfo serviceLevelTaskInfo;
	
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
  public void beforeTest() {
			  
	  	loadDataNewConnect();
//Search for customer if rerun - added by harsh on 8/8/16
		
  }
  
  @Test(priority=100)
  @PerfTransaction(name="CreateCustomer")
  public void createCustomer(){
	    String customerName;
		try {
			customerName = (new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
			getDataDump().setValue("CustomerName_RT", customerName);
		//	getDataDump().setValue("userName","custpmauto");
			//getDataDump().getValue("CM_Status")
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
  
  @Test(priority=200)
  @PerfTransaction(name="CreateServiceAccount")
  public void createServiceAccount() throws InterruptedException{
	  if((new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo)){
		  if((new AccountTabPageCM(browser, report)).clickOnAddContact()){
		  if((new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo)){
			  if((new ContactTabPageCM(browser, report)).ClickOnBackBtn()){
			  }else Assert.fail("Click on back button failed");
		  }else Assert.fail("Create account primary contact failed");
		  }else Assert.fail("Click on add contact failed");
	  }else Assert.fail("Create service account failed");
  }
  
  @Test(priority=300)
  @PerfTransaction(name="CreateBillingAccount")
  public void createBillingAccount() throws InterruptedException{			
	  if((new AccountTabPageCM(browser, report)).CreateBillingAccount(accountInfo)){
		  if((new AccountTabPageCM(browser, report)).clickOnAddContact()){
		  if((new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo)){
			  if((new ContactTabPageCM(browser, report)).ClickOnBackBtn()){
			  }else Assert.fail("Click on back button failed");
		  }else Assert.fail("Create billing contact failed");
		  }else Assert.fail("Click on add contact failed");
	  }else Assert.fail("Create billing account failed");
  }
  
  @Test(priority=400)
  @PerfTransaction(name="CreateAddress")
  public void createAddress() throws InterruptedException {
		if((new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo)){
			Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
			if((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)){
			}else Assert.fail("Create site technical Contact failed");
		}else Assert.fail("click on address tab failed");		
  }
  
  @Test(priority=500)
  @PerfTransaction(name="SelectService")
  public void selectService() throws InterruptedException{
	  (new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		if((new ServiceTabPageCM(browser, report)).SelectPricePlan()){
			if((new ServiceTabPageCM(browser, report)).EDI()){
				if((new ServiceTabPageCM(browser, report)).EquipmentFee()){
					if((new ServiceTabPageCM(browser, report)).ClickOnContinueButton()){
						(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();					
					}else Assert.fail("Click continue button failed");
				}else Assert.fail("Equipment fee failed");
			}else Assert.fail(" Select EDI plan failed");
		}else Assert.fail(" Select service plan failed");
	  
  }
  
  @Test(priority=501)
  @PerfTransaction(name="SelectServiceEDIOnly")
  public void selectServiceEDIOnly() throws InterruptedException{
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
  @PerfTransaction(name="ProcessService")
  public void processService() throws InterruptedException{
	  	if(getDataDump().getValue("processService_status").equalsIgnoreCase("FAIL"))
	  	{
	  		selectService();
	  	}
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		getDataDump().setValue("SRID_RT", SRID);
		if((new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1)){
			if((new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo)){
				if((new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo)){
					if((new ProcessTabPageCM(browser, report)).ClickOnContinueButton()){
					}else Assert.fail("Click on continue button failed");
				}else Assert.fail("Equipment fee configuration failed");
			}else Assert.fail("EVC configuration failed");
		}else Assert.fail("UNI configuration failed");
  }	  
  
  @Test(priority=601)
  @PerfTransaction(name="ProcessServiceEDIOnly")
  public void processServiceEDIOnly() throws InterruptedException{
	  	if(getDataDump().getValue("ProcessServiceEDIOnly_status").equalsIgnoreCase("FAIL"))
	  	{
	  		selectServiceEDIOnly();
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
  public void submitOrder() throws InterruptedException, AWTException{
	 if(getDataDump().getValue("submitOrder_status").equalsIgnoreCase("FAIL"))
	  	{
	  		selectService();
	  		processService();
	  	}
	   
		if((new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo)){
			if((new OrderSummaryTabCMPage(browser, report)).enterOrderDetails(orderSummaryInfo)){
				if((new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo)){
					if((new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton()){
						
						getDataDump().setValue("CM_Status","PASS");
						
				}else Assert.fail("Order submission failed");
				}else Assert.fail("Entering NRC values failed");
			}else Assert.fail("Entering order detrails failed");
		}else Assert.fail("Assigning label failed");	
	
  }
  
  @Test(priority=701)
  @PerfTransaction(name="submitOrderErate")
  public void submitOrderErate() throws InterruptedException, AWTException{
	 if(getDataDump().getValue("submitOrder_status").equalsIgnoreCase("FAIL"))
	  	{
		    selectServiceEDIOnly();
	  		processServiceEDIOnly();
	  	}
		if((new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo)){
			if((new OrderSummaryTabCMPage(browser, report)).enterOrderDetails(orderSummaryInfo)){
				if((new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo)){
					if((new OrderSummaryTabCMPage(browser, report)).Attachments(orderSummaryInfo)){
						if((new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton()){
							getDataDump().setValue("CM_Status","PASS");
						}else Assert.fail("Order submission failed");
					}else Assert.fail("Uploading the Tax Excemption form failed");
				}else Assert.fail("Entering NRC values failed");
			}else Assert.fail("Entering order details failed");
		}else Assert.fail("Assigning label failed");
  }

  @Test(priority=800)
  public void StartCSO() {
	//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	 (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
	 (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
	 
  }	 
  
  @Test(priority=900)
  public void Conduct_Site_Survey() throws InterruptedException {	
	  if (getDataDump().getValue("Conduct_Site_Survey_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).ConductSiteSurvey();
	  (new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
  }	

  @Test(priority=901)
  @PerfTransaction(name="Conduct_Site_Survey_Coax")
  public void Conduct_Site_Survey_Coax() throws InterruptedException {
	  if (getDataDump().getValue("Conduct_Site_Survey_Coax_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).ConductSiteSurveyCoax();
	  (new ConductSiteSurveyCoaxTaskPage(browser, report)).ConductSiteSurveyCoax(siteLevelTaskInfo);
  }

  @Test(priority=1000)
  public void Obtain_Site_Agreement(Method method) throws InterruptedException {
	  //CSOSearchForOrderInSO();
	  StartCSO();
	  (new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
	  (new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
  }	
  
  @Test(priority=1100)
  public void Conduct_Fiber_Plant_Survey() throws InterruptedException, AWTException {
	  
	  StartCSO();
	  (new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
	 (new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
		
  }	
  
  @Test(priority=1101)
  @PerfTransaction(name="Conduct_Coax_Survey")
  public void Conduct_Coax_Survey() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Conduct_Coax_Survey_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	 (new SiteLevelTasks(browser, report)).ConductCoaxSurvey();
	 (new ConductCoaxSurveyTaskPage(browser, report)).ConductCoaxSurvey(siteLevelTaskInfo);
  }
 
  @Test(priority=1200)
  public void Build_House_Account() throws InterruptedException, AWTException {
	  StartCSO();
	  (new SiteLevelTasks(browser, report)).BuildHouseAccount();
	  (new BuildHouseAccountTaskPage(browser, report)).BuildHouseAccount(siteLevelTaskInfo);
		
  }	

  @Test(priority=1201)
  @PerfTransaction(name="Obtain_Coax_Permit")
  public void Obtain_Coax_Permit() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Obtain_Coax_Permit_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).ObtainCoaxPermits();
	  (new ObtainCoaxPermitsTaskPage(browser, report)).ObtainCoaxPermits();
  }

  
  @Test(priority=1300)
  public void Complete_Wavelength_Reservation() throws InterruptedException, AWTException {
	  StartCSO();
	  (new SiteLevelTasks(browser, report)).CompleteWavelengthReservation();
	  (new CompleteWavelengthReservationTaskPage(browser, report)).CompleteWavelengthReservation(siteLevelTaskInfo);
		
  }	
	
  @Test(priority=1400)
  public void Complete_Site_Build() throws InterruptedException, AWTException {
	 
	  if (getDataDump().getValue("Complete_Site_Build_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	   (new SiteLevelTasks(browser, report)).CompleteSiteBuild();
		(new CompleteSiteBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteSiteBuildTaskPage(browser, report)).closePopup();
		
  }	
  @Test(priority=1401)
  @PerfTransaction(name="Complete_Site_Build_Coax")
  public void Complete_Site_Build_Coax() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Complete_Site_Build_Coax_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).CompleteSiteBuildCoax();
	  (new CompleteSiteBuildCoaxTaskPage(browser, report)).ClickCompleteButton();
	  (new CompleteSiteBuildCoaxTaskPage(browser, report)).closePopup();
  }
  
  @Test(priority=1500)
  public void Obtain_Fiber_Plant_Permit() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Obtain_Fiber_Plant_Permit_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).ObtainFiberPlantPermits();
	  (new ObtainFiberPlantPermitsTaskPage(browser, report)).ObtainFiberPlantPermits();
		
  }	
    
  @Test(priority=1600)
  public void Complete_Fiber_Plant_Build() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Complete_Fiber_Plant_Build_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	    (new SiteLevelTasks(browser, report)).CompleteFiberPlantBuild();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).ClickCompleteButton();
		(new CompleteFiberPlantBuildTaskPage(browser, report)).closePopup();
		
  }
  
  @Test(priority=1601)
  @PerfTransaction(name="Complete_Coax_Build")
  public void Complete_Coax_Build() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Complete_Coax_Build_status").equalsIgnoreCase("fail"))
	  {
		  StartCSO();
	  }
	  (new SiteLevelTasks(browser, report)).CompleteCoaxBuild();
	  (new CompleteCoaxBuildTaskPage(browser, report)).ClickCompleteButton();
	  (new CompleteCoaxBuildTaskPage(browser, report)).closePopup();
  }
  
  @Test(priority=1700)
  public void Contact_Customer() throws InterruptedException, AWTException {
	    StartCSO();	  
	    (new ServiceLevelTasks(browser, report)).ContactCustomer();
		(new ContactCustomerTaskPage(browser, report)).ContactCustomer();
		
  }	
    	
  @Test(priority=1800)
  public void Update_Design() throws InterruptedException, AWTException {
	  	StartCSO();
	    (new ServiceLevelTasks(browser, report)).UpdateDesign();
		(new UpdateDesignTaskPage(browser, report)).UpdateDesign();
		(new SiteLevelTasks(browser, report)).ClickBackButton();
  }	
    	
//######################################################
  
  public void EDIFlow() throws InterruptedException {
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
		 (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		 (new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
		 
	  }
  //#####################
	
  @Test(priority=1900)
  public void Build_Update_Local_Biller_Account() throws InterruptedException {
	  	 EDIFlow();
	    (new ServiceLevelTasks(browser, report)).BULBA();
		(new BULBATaskPage(browser, report)).BULBA(serviceLevelTaskInfo);
		 
	  }	 
	
  @Test(priority=2000)
  public void Ship_CPE() throws InterruptedException {
	  	EDIFlow();
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	  	(new ServiceLevelTasks(browser, report)).ShipCPE();
		(new ShipCPETaskPage(browser, report)).ShipCPE(serviceLevelTaskInfo);
		 
	  }	 
  
  @Test(priority=2100)
  public void Create_Account_and_Equipment() throws InterruptedException {
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
	  	EDIFlow();
	  	(new ServiceLevelTasks(browser, report)).CAE();
		(new CAETaskPage(browser, report)).CAE(serviceLevelTaskInfo);
		 
	  }	 
  
  @Test(priority=2200)
  public void Assign_Design_Info() throws InterruptedException {
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	  if (getDataDump().getValue("Assign_Design_Info_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
	  	(new ServiceLevelTasks(browser, report)).ADI();
		(new ADITaskPage(browser, report)).ADI(serviceLevelTaskInfo);
		 
	  }	 
  
  @Test(priority=2300)
  public void Generate_Core_Config() throws InterruptedException {
	  if (getDataDump().getValue("Generate_Core_Config_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	  (new ServiceLevelTasks(browser, report)).GenerateCoreConfigs();
	  (new GenerateCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
		 
	  }	 
  
  @Test(priority=2400)
  public void Generate_CPE_Config() throws InterruptedException {
	  if (getDataDump().getValue("Generate_CPE_Config_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	  (new ServiceLevelTasks(browser, report)).GenerateCPEConfigs();
	  (new GenerateCPEConfigsTaskPage(browser, report)).ClickCompleteButton();
	  }	 
  
  @Test(priority=2500)
  public void Load_Core_Config() throws InterruptedException {
		
	  	EDIFlow();
	  	(new ServiceLevelTasks(browser, report)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(browser, report)).ClickCompleteButton();
	  }	 
  
  @Test(priority=2600)
  public void Install_CPE() throws InterruptedException {
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);
	  	EDIFlow();
	  	(new ServiceLevelTasks(browser, report)).InstallCPE();
		(new InstallCPETaskPage(browser, report)).InstallCPE();
	  }	

  @Test(priority=2601)
  @PerfTransaction(name="Install_CPE_Coax")
  public void Install_CPE_Coax() throws InterruptedException, AWTException {
	  if (getDataDump().getValue("Install_CPE_Coax_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
	  (new ServiceLevelTasks(browser, report)).InstallCPE();
	  (new InstallCPE_CoaxTaskPage(browser, report)).InstallCPE();
  }
  
  @Test(priority=2700)
  public void Set_Critical_Dates() throws InterruptedException {
	  EDIFlow();
	  (new ServiceLevelTasks(browser, report)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(browser, report)).SetCriticalDates();
	  }	
  
  @Test(priority=2800)
  public void Day_of_Configs() throws InterruptedException {
	  	EDIFlow();  
	  	(new ServiceLevelTasks(browser, report)).DayofConfigs();
		(new DaysOfConfigsTaskPage(browser, report)).ClickCompleteButton();
	  }	
  
 @Test(priority=2900)
  public void Activate_Service() throws InterruptedException {
	 if (getDataDump().getValue("Activate_Service_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }  
	  (new ServiceLevelTasks(browser, report)).ActivateService();
	  (new ActivateServiceTaskPage(browser, report)).ActivateService(serviceLevelTaskInfo);
 }
  
 @Test(priority=3000)
 public void Notify_Customer_of_Service_Installation() throws InterruptedException {
	 if (getDataDump().getValue("Notify_Customer_of_Service_Installation_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }  	  
	 (new ServiceLevelTasks(browser, report)).NotifyCustomerofServiceInstallation();
	 (new NotifyCustomerofServiceInstallationTaskPage(browser, report)).NotifyCustomerofServiceInstallation();
}
 
@Test(priority=3100)
 public void Complete_Customer_Acceptance_Testing(Method method) throws InterruptedException {
	System.out.println("method: " + method);
	if (getDataDump().getValue("Complete_Customer_Acceptance_Testing_status").equalsIgnoreCase("fail")
			||getDataDump().getValue("Complete_Customer_Acceptance_Testing_status").equalsIgnoreCase(""))
	  {
		  EDIFlow();
	  } 	  
	(new ServiceLevelTasks(browser, report)).CCAT();
	(new CCATTaskPage(browser, report)).ClickCompleteButton();
}

@Test(priority=3200)
@PerfTransaction(name="Create_Order_Billing_Package")
public void Create_Order_Billing_Package() throws AWTException, InterruptedException {
	  if (getDataDump().getValue("Create_Order_Billing_Package_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
		(new ServiceLevelTasks(browser, report)).CreateOrderBillingPackage();
		(new CreateOrderBillingPackageTaskPage(browser, report)).CreateOrderBillingPackage();
  }

@Test(priority=3300)
public void Start_Billing() throws InterruptedException {
	EDIFlow();	  
	(new ServiceLevelTasks(browser, report)).StartBilling();
	(new ServiceLevelTasks(browser, report)).ClickBackButton();
	(new ServiceLevelTasks(browser, report)).ClickBackButton();
}

@Test(priority=3400)
public void EquipmentFeeFlow() throws InterruptedException {
	if (getDataDump().getValue("EquipmentFeeFlow_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
	(new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
	(new WorkOrderTabPageCSO(browser, report)).ClickEquipmentFeeFlow();
}

@Test(priority=3500)
public void EqFeeStartBilling() throws InterruptedException {
	if (getDataDump().getValue("EqFeeStartBilling_status").equalsIgnoreCase("fail"))
	  {
		  EDIFlow();
	  }	  
	(new EqFeeFlowTasks(browser, report)).EqFeeStartBilling();
	(new EqFeeStartBillingTaskPage(browser, report)).EqFeeStartBilling();
	(new EqFeeFlowTasks(browser, report)).ClickBackButton();
}
/*
	(new WorkOrderTabPageCSO(browser, report)).ClickEDIFlow();
	
		
		
		
	
	*/	
	
	
	
	
	
	
	
  
  

  
  @AfterMethod
  public void afterMethod() {
	  //set the status of the method
  }



  @AfterTest
  public void afterTest() {
  }

  public void loadDataNewConnect(){
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
	//	loginInfo = LoginDetails.loadFromDatatable(dataTable);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
	}
  
	
}
