package com.comcast.century.newConnect;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
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
import com.comcast.century.commons.CenturyApplication;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.LoginDetails;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class NewConnectTest extends ComcastTest {
	
	private static CenturyApplication centuryApplication;
	private CustomerInfo customerInfo;
	private AccountInfo accountInfo;
	private ContactInfo contactInfo;
	private SiteInfo siteInfo;
	private ProcessInfo processInfo;
	private OrderSummaryInfo orderSummaryInfo;
	private SiteLevelTaskInfo siteLevelTaskInfo;
	private LoginDetails loginInfo;
	private ServiceLevelTaskInfo serviceLevelTaskInfo;
	String SRID;
	String SurveyID;
	String Site1;
	

	
  @BeforeMethod
  public void beforeMethod() {
	  //check for rerun and the status of the method
	  
  }
  
  @BeforeTest
  @PerfTransaction(name="Login")
  public void beforeTest() {
	  	loadData();
		//LoginDetails loginInfo = LoginDetails.loadFromDatatable(dataTable);
		centuryApplication = new CenturyApplication(browser, report);
		try {
			centuryApplication.openUrl(loginInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
	
  @Test(priority=4)
  @PerfTransaction(name="createAddress")
  public void createAddress() throws InterruptedException {
	  //siteInfo = SiteInfo.loadFromDatatable(dataTable);
		(new AddressTabPageCM(browser, report)).ClickAddressTab(siteInfo);
		Site1 = (new AddressTabPageCM(browser, report)).EnterSiteDetailsValid(siteInfo);
		(new ContactTabPageCM(browser, report)).CreateSiteTechnicalContact(contactInfo);
		
  }
  
  @Test(priority=1)
  @PerfTransaction(name="createCustomer")
  public void createCustomer(){
	  
		//customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		try {
			(new CustomerTabPageCM(browser, report)).createCustomer(customerInfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  
  }
  
  @Test(priority=2)
  @PerfTransaction(name="createBillingAccount")
  public void createBillingAccount(){
	  
		//accountInfo = AccountInfo.loadFromDatatable(dataTable);
		//contactInfo = ContactInfo.loadFromDatatable(dataTable);

		try {
			(new AccountTabPageCM(browser, report)).CreateBiilingAccount(accountInfo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			(new ContactTabPageCM(browser, report)).CreateBillingContact(contactInfo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		(new ContactTabPageCM(browser, report)).ClickOnBackBtn();
	  
  }
  
  @Test(priority=3)
  @PerfTransaction(name="CreateServiceAccount")
  public void createServiceAccount() throws InterruptedException{
	  (new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo);
	  (new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo);
	  (new ContactTabPageCM(browser, report)).ClickOnBackBtn();
  }
  
  @Test(priority=5)
  @PerfTransaction(name="selectService")
  public void selectService() throws InterruptedException{
	  (new ServiceTabPageCM(browser, report)).ClickOnServiceTab();
		(new ServiceTabPageCM(browser, report)).SelectPricePlan();
		(new ServiceTabPageCM(browser, report)).EDI();
		(new ServiceTabPageCM(browser, report)).EquipmentFee();
		(new ServiceTabPageCM(browser, report)).ClickOnContinueButton();
		(new FeatureTabPageCM(browser, report)).ClickOnContinueButton();
	  
	  
  }
  
  @Test(priority=6)
  @PerfTransaction(name="processService")
  public void processService() throws InterruptedException{
	  // processInfo = ProcessInfo.loadFromDatatable(dataTable);
		SRID = (new ProcessTabPageCM(browser, report)).ProcessConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).UNIConfiguration(processInfo, Site1);
		(new ProcessTabPageCM(browser, report)).EVCConfiguration_EDI(processInfo);
		(new ProcessTabPageCM(browser, report)).EqFeeConfiguration(processInfo);
		(new ProcessTabPageCM(browser, report)).ClickOnContinueButton();

	  
  }
  
  @Test(priority=7)
  @PerfTransaction(name="submitOrder")
  public void submitOrder() throws InterruptedException{
	  
		//orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		//(new OrderSummaryTabCMPage(browser, report)).assignLabel(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).SubmitOrder(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).mrcNrc_Value(orderSummaryInfo);
		(new OrderSummaryTabCMPage(browser, report)).ClickSubmitOrderButton();
	  
	  
  }


  @AfterMethod
  public void afterMethod() {
	  //set the status of the method
  }



  @AfterTest
  public void afterTest() {
  }

  public void loadData(){
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		loginInfo = LoginDetails.loadFromDatatable(dataTable);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
	}
}
