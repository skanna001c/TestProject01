package com.comcast.century.common;

import org.testng.annotations.Test;

import com.comcast.century.cso.pages.AssignDesignBGPTaskPage;
import com.comcast.century.cso.pages.DaysOfConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCPEConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceInstallationTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.StartBillingTaskPage;


public class BGPFlow extends TrunkPRIFlow {
            
	 
	@Test(priority = 22013)
	public void Assign_Design_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).assignDesignBGP();
		(new AssignDesignBGPTaskPage(frameworkContext)).ClickCompleteButton();

	}
	
	@Test(priority = 22014)
	public void Generate_CPE_Config_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();

	}
	
	@Test(priority = 22015)
	public void Set_Critical_Dates_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();

	}
	
	@Test(priority = 22016)
	public void Day_of_Configs_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();

	}
	
	@Test(priority = 22017)
	public void Notify_Customer_of_Service_Installation_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).NotifyCustomerofServiceInstallation();
		(new NotifyCustomerofServiceInstallationTaskPage(frameworkContext)).NotifyCustomerofServiceInstallation();

	}
	
	@Test(priority = 22018)
	public void Start_Billing_BGP() throws InterruptedException {
		SearchOrderndLaunchBGPFlow();
		(new ServiceLevelTasks(frameworkContext)).StartBilling();
		(new StartBillingTaskPage(frameworkContext)).StartBilling();	

	}
	
	
	
	
}
