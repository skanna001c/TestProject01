package com.comcast.century.eod;

import java.awt.AWTException;

import org.testng.annotations.Test;
import com.comcast.century.common.Supplements;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.UpdateDesignTaskPage;

public class CancelSupEPLService extends Supplements {
          
	@Test(priority = 40000)
	public void Obtain_Site_Agreement_CancelSup() throws InterruptedException {
		System.out.println("inside overridded method");
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
			//(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
			(new ObtainSiteAgreementTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}
	
	@Test(priority = 40500)
	public void Obtain_Site_Agreement_Coax_CancelSup() throws InterruptedException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
			(new ObtainSiteAgreementTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}
	
	@Test(priority = 50000)	
	public void Obtain_Coax_Permit_CancelSup() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainCoaxPermits();
			(new ObtainCoaxPermitsTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}
	
	@Test(priority = 50500)
	public void Contact_Customer_CancelSup() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ClickCompleteButton();

	}
	
	@Test(priority = 60000)
	public void Update_Design_CancelSup() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(frameworkContext)).UpdateDesign();
		(new UpdateDesignTaskPage(frameworkContext)).ClickCompleteButton();		
	}
	
	@Test(priority = 60500)
	public void Set_Critical_Dates_CancelSup() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
			(new SetCriticalDatesTaskPage(frameworkContext)).ClickCompleteButton();
		}
		
	}
	
}
