package com.comcast.century.newConnect;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class NewConnectFlow extends ComcastTest {
	
	protected CustomerInfo customerInfo;
	protected AccountInfo accountInfo;
	protected ContactInfo contactInfo;
	protected SiteInfo siteInfo;
	protected ServiceInfo serviceInfo;
	protected ProcessInfo processInfo;
	protected OrderSummaryInfo orderSummaryInfo;
	protected SiteLevelTaskInfo siteLevelTaskInfo;
	protected ServiceLevelTaskInfo serviceLevelTaskInfo;
	
	@BeforeClass
	public void beforeTest() {
		loadDataNewConnect();
	}

	
	@Test(enabled = false)
	public void EDI(){
		if(settings == frameworkContext.getSettings())
			Assert.assertTrue(true);
		System.out.println(frameworkContext.getTestCaseName());
	}
	
	@Test(priority = 500)
	@PerfTransaction(name = "CreateCustomer")
	public void createCustomer() {
		String customerName;
		try {
			customerName = (new CustomerTabPageCM(frameworkContext)).createCustomer(customerInfo);
			getDataDump().setValue("CustomerName_RT", customerName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	@Test(priority = 1000, enabled = false)
	@PerfTransaction(name = "CreateServiceAccount")
	public void createServiceAccount() throws InterruptedException {
		if ((new AccountTabPageCM(browser, report)).CreateServiceAccount(accountInfo)) {
			if ((new AccountTabPageCM(browser, report)).clickOnAddContact()) {
				if ((new ContactTabPageCM(browser, report)).CreateAccountPrimaryContact(contactInfo)) {
					if ((new ContactTabPageCM(browser, report)).ClickOnBackBtn()) {
					} else
						Assert.fail("Click on back button failed");
				} else
					Assert.fail("Create account primary contact failed");
			} else
				Assert.fail("Click on add contact failed");
		} else
			Assert.fail("Create service account failed");
	}
	
	public void loadDataNewConnect() {
		accountInfo = AccountInfo.loadFromDatatable(dataTable);
		siteInfo = SiteInfo.loadFromDatatable(dataTable);
		customerInfo = CustomerInfo.loadFromDatatable(dataTable);
		contactInfo = ContactInfo.loadFromDatatable(dataTable);
		serviceInfo = ServiceInfo.loadFromDatatable(dataTable);
		processInfo = ProcessInfo.loadFromDatatable(dataTable);
		orderSummaryInfo = OrderSummaryInfo.loadFromDatatable(dataTable);
		siteLevelTaskInfo = SiteLevelTaskInfo.loadFromDatatable(dataTable);
		serviceLevelTaskInfo = ServiceLevelTaskInfo.loadFromDatatable(dataTable);
	}

	
	
	

}
