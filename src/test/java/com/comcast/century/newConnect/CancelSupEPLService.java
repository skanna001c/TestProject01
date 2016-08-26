package com.comcast.century.newConnect;

import java.awt.AWTException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cso.pages.ConductFiberPlantSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
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
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class CancelSupEPLService extends ComcastTest {

	private CustomerInfo customerInfo;
	private AccountInfo accountInfo;
	private ContactInfo contactInfo;
	private SiteInfo siteInfo;
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;
	
	String SRID;
	String Site1;
	String Site2;
	
	
	 @BeforeTest
	  public void beforeTest() {
				  
		  	loadData();
			
	  }
	 
	 @Test(priority=1)
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
	  
	  @Test(priority=2)
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
	  
	  @Test(priority=3)
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
	  
	  @Test(priority=4)
	  @PerfTransaction(name="CreateAddress")
	  public void createAddress() throws InterruptedException {
			if((new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo)){
				Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
				if((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)){
					if((new ContactTabPageCM(browser, report)).ClickOnBackBtn()){							
					} else Assert.fail("Click on back button failed");
				}else Assert.fail("Create site technical Contact failed");
			}else Assert.fail("click on address tab failed");		
	  }
	  
	  @Test(priority=5)
	  @PerfTransaction(name="CreateAddress2")
	  public void createAddress2() throws InterruptedException {
			if((new AddressTabPageCM(browser, report)).CreateNewAddress()){
				Site2 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
				if((new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo)){
				}else Assert.fail("Create site technical Contact failed");
			}else Assert.fail("click on create new address failed");		
	  }
	  
	  @Test(priority=6)
	  @PerfTransaction(name="SelectService")
	  public void selectService() throws InterruptedException{
		  (new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
			if((new ServiceTabPageCM(browser, report)).SelectPricePlan()){
				if((new ServiceTabPageCM(browser, report)).EPL()){
					if((new ServiceTabPageCM(browser, report)).EquipmentFeeOnly()){
						if((new ServiceTabPageCM(browser, report)).ClickOnContinueButton()){
							(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();					
						}else Assert.fail("Click continue button failed");
					}else Assert.fail("Select Equipment fee failed");
				}else Assert.fail(" Select EPL plan failed");
			}else Assert.fail(" Select service plan failed");
	  }
		  
	  
	  
	  @Test(priority=7)
	  @PerfTransaction(name="ProcessService")
	  public void processService() throws InterruptedException{
		  	if(getDataDump().getValue("processService_status").equalsIgnoreCase("FAIL"))
		  	{
		  		selectService();
		  	}
			SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
			getDataDump().setValue("SRID_RT", SRID);
			if((new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1)){
				if((new ProcessTabPageCM(browser,report)).UNI2Configuration(processInfo, Site2)){
				   if((new ProcessTabPageCM(browser, report)).EVCConfiguration_EPL(processInfo)){
					  if((new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo)){
						    if((new ProcessTabPageCM(browser, report)).ClickOnContinueButton()){
						 }else Assert.fail("Click on continue button failed");
					}else Assert.fail("Equipment fee configuration failed");
				}else Assert.fail("EVC configuration failed");
			}else Assert.fail("UNI2 configuration failed");
		}else Assert.fail("UNI configuration failed");
	  }	  
	  
	  
	  @Test(priority=8)    
	  public void submitOrder() throws InterruptedException{
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
	  
	  @Test(priority=9)
	  public void StartCSO() {
		//(new OrderSummaryTabCMPage(browser, report)).NavigateToCSO(orderSummaryInfo);  
		 (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		 (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		 
	  }	 
	  
	  @Test(priority=10)
	  public void Conduct_Site_Survey() throws InterruptedException {	
		  if (getDataDump().getValue("Conduct_Site_Survey_status").equalsIgnoreCase("fail"))
		  {
			  CSOSearchForOrderInSO();
		  }
		  (new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		  (new ConductSiteSurveyTaskPage(browser, report)).ConductSiteSurvey(siteLevelTaskInfo);
	  }	
	  
	  @Test(priority=11)
	  public void Obtain_Site_Agreement(Method method) throws InterruptedException {
		  (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(getDataDump().getValue("SRID_RT"));
		  (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		  (new SiteLevelTasks(browser, report)).ObtainSiteAgreement();
		  (new ObtainSiteAgreementTaskPage(browser, report)).ObtainSiteAgreement(siteLevelTaskInfo);
	  }	
	  
	  @Test(priority=12)
	  public void Conduct_Fiber_Plant_Survey() throws InterruptedException, AWTException {
		  (new SiteLevelTasks(browser, report)).ConductFiberPlantSurvey();
		 (new ConductFiberPlantSurveyTaskPage(browser, report)).ConductFiberPlantSurvey(siteLevelTaskInfo);
			
	  }	
	  
	  public void CSOSearchForOrderInSO()
	  {
		  (new WorkOrderTabPageCSO(browser, report)).SearchForOrderInSO(SRID);
		  (new WorkOrderTabPageCSO(browser, report)).ClickFirstSiteFlow();
		  
	  }
	
	 
	 
	 @AfterTest
	  public void afterTest() {
	  }

	  public void loadData(){
			accountInfo = AccountInfo.loadFromDatatable(dataTable);
			siteInfo = SiteInfo.loadFromDatatable(dataTable);
			customerInfo = CustomerInfo.loadFromDatatable(dataTable);
			contactInfo = ContactInfo.loadFromDatatable(dataTable);
			processInfo = ProcessInfo.loadFromDatatable(dataTable);
			orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
			siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		}
}
