package com.comcast.century.cancel;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.century.common.Supplements;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainFiberPlantPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.UpdateDesignTaskPage;

public class CancelSupFlow extends Supplements {
          
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
	public void Obtain_Fiber_Plant_Permit_CancelSup() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(frameworkContext.getDataDump().getValue("Fibercount_RT")); i++){
			SearchOrderndLaunchFiberSiteFlow(frameworkContext.getDataDump().getValue("FiberSite" + i + "_RT"));
			if((new SiteLevelTasks(frameworkContext)).ObtainFiberPlantPermits())
			{
				(new ObtainFiberPlantPermitsTaskPage(frameworkContext)).ClickCompleteButton();
			}
		}
	}
	
	
	/*@Test(priority = 50500)
	public void Contact_Customer_CancelSup() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		(new ServiceLevelTasks(frameworkContext)).ContactCustomer();
		(new ContactCustomerTaskPage(frameworkContext)).ClickCompleteButton();

	}*/
	
	
	
	@Test(priority = 60500)
	public void Set_Critical_Dates_CancelSup() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
			(new SetCriticalDatesTaskPage(frameworkContext)).ClickCompleteButton();
		}
	}
	
		//p[contains(.,'Generate Core Configs')]
	@Test(priority = 61000)
	public void Generate_Core_Configs() throws InterruptedException {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs())
			{
				(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Generate_Core_Configs");
			}
			
		}
	}
		
		//a[contains(.,'Update Local Biller')]	
	@Test(priority = 61500)	
		public void Update_Local_Biller() throws InterruptedException {
			for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
				SearchOrderndLaunchServiceFlow(i);
				if((new ServiceLevelTasks(frameworkContext)).Update_Local_Biller())
				{
					(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Generate_Core_Configs");
				}
				
			}	
		
		}
	
	@Test(priority = 62000)
	public void Remove_Core_Config() throws InterruptedException  {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).removeCoreConfigs())
				
			{	(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Update_Local_Biller");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}	
	
	@Test(priority = 62100)
	public void Assign_Design_Info_SUP() throws InterruptedException  {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).ADI())
			{
				(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Assign_Design_Info");
			}
		}
	}

	@Test(priority = 63000)
	public void Create_Account_and_Equipment_SUP() throws Exception {
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).CAE())
			{
				(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Create_Account_and_Equipment");
			}
			
		}
	}

	@Test(priority = 63500)
	public void Schedule_CPE_Pickup() throws Exception {
		By by= By.xpath("//a[text()='Schedule CPE Pickup' and contains(@onclick, 'PROGRESS')]");
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
		   if((new ServiceLevelTasks(frameworkContext)).waitUntilElementPresent(by, 60))
		   { int taskcount=browser.findElements(by).size();		   	
		   	for (int count = 0; count <taskcount; count++) {
		   		if((new ServiceLevelTasks(frameworkContext)).scheduleCPEPickup(by))
				{
					(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Schedule_CPE_Pickup");
				}
			}
		   
			}
			
		}
	}
	
	@Test(priority = 64000) 
	public void Pick_up_CPE() throws Exception {
		By by= By.xpath("//a[text()='Pick up CPE' and contains(@onclick, 'PROGRESS')]");
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
		   if((new ServiceLevelTasks(frameworkContext)).waitUntilElementPresent(by, 60))
		   { int taskcount=browser.findElements(by).size();		   	
		   	for (int count = 0; count <taskcount; count++) {
		   		if((new ServiceLevelTasks(frameworkContext)).pickupCPE(by))
				{
					(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Pick_up_CPE");
				}
			}
		   
			}
			
		}
	}
	
	@Test(priority = 64500)  //div[contains(.,'Reclamation of Physical Equipment')]
	public void Reclamation_of_Physical_Equipment() throws Exception {
		By by= By.xpath("//a[text()='Reclamation of Physical Equipment' and contains(@onclick, 'PROGRESS')]");
		for (int i = 0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++) {
			SearchOrderndLaunchServiceFlow(i);
		   if((new ServiceLevelTasks(frameworkContext)).waitUntilElementPresent(by, 60))
		   { int taskcount=browser.findElements(by).size();		   	
		   	for (int count = 0; count <taskcount; count++) {
		   		if((new ServiceLevelTasks(frameworkContext)).Reclamation_of_Physical_Equipment(by))
				{
					(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Reclamation_of_Physical_Equipment");
				}
			}
		   
			}
			
		}
	}
	
	@Test(priority = 65000)
	public void Update_Design_CancelSup() throws InterruptedException, AWTException {
		SearchOrderndLaunchServiceRequest();
		if((new ServiceLevelTasks(frameworkContext)).UpdateDesign()){
			(new ServiceLevelTasks(frameworkContext)).ClickCompleteButton("Update_Design");
		}
				
	}
	
	
	
	@Test(priority = 65500)
	public void CancelSup_CompleteTaskFlow() throws InterruptedException, AWTException {
		SearchOrder(frameworkContext.getDataDump().getValue("SRID_RT"));
	}
	
	
	
	
	
}