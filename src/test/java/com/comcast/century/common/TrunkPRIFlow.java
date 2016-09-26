package com.comcast.century.common;

import org.testng.annotations.Test;

import com.comcast.century.cso.pages.ADITaskPage;
import com.comcast.century.cso.pages.ActivateServiceTaskPage;
import com.comcast.century.cso.pages.BULBATaskPage;
import com.comcast.century.cso.pages.CAETaskPage;
import com.comcast.century.cso.pages.DaysOfConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCPEConfigsTaskPage;
import com.comcast.century.cso.pages.GenerateCoreConfigsTaskPage;
import com.comcast.century.cso.pages.InstallCPETaskPage;
import com.comcast.century.cso.pages.LoadCoreConfigsTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.SetCriticalDatesTaskPage;
import com.comcast.century.cso.pages.ShipCPETaskPage;

public class TrunkPRIFlow extends CoaxTaskFlow {

	@Test(priority = 22001)
	public void Build_Update_Local_Biller_Account_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).BULBA(0);
		(new BULBATaskPage(frameworkContext)).BULBA(serviceLevelTaskInfo);

	}

	@Test(priority = 22002)
	public void Create_Account_and_Equipment_PRI() throws Exception {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).CAE();
		(new CAETaskPage(frameworkContext)).CAETask(serviceInfo);

	}

	@Test(priority = 22003)
	public void Ship_CPE_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).ShipCPE();
		(new ShipCPETaskPage(frameworkContext)).ShipCPE(serviceLevelTaskInfo);
	}

	@Test(priority = 22004)
	public void Assign_Design_Info_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).ADI();
		(new ADITaskPage(frameworkContext)).ADITask(serviceInfo);
		if(testcaseName.matches("EDI+PRI_Equipment Fee_Cancel Sup_Completed_Activate_Service")){
			startCM();
		}
	}

	@Test(priority = 22005)
	public void Generate_CPE_Config_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).GenerateCPEConfigs();
		(new GenerateCPEConfigsTaskPage(frameworkContext)).ClickCompleteButton();
	}

	@Test(priority = 22006)
	public void Generate_Core_Config_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).GenerateCoreConfigs();
		(new GenerateCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
	}

	@Test(priority = 22007)
	public void Load_Core_Config_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).LoadCoreConfigs();
		(new LoadCoreConfigsTaskPage(frameworkContext)).ClickCompleteButton();
	}

	@Test(priority = 22008)
	public void Install_CPE_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).InstallCPE(0);
		(new InstallCPETaskPage(frameworkContext)).InstallCPE(serviceInfo);
	}

	@Test(priority = 22009)
	public void Set_Critical_Dates_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).SetCriticalDates();
		(new SetCriticalDatesTaskPage(frameworkContext)).SetCriticalDates();
	}

	@Test(priority = 22010)
	public void Day_of_Configs_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).DayofConfigs();
		(new DaysOfConfigsTaskPage(frameworkContext)).ClickCompleteButton();
	}

	@Test(priority = 22011)
	public void Activate_Service_PRI() throws InterruptedException {
		SearchOrderndLaunchPRIFlow();
		(new ServiceLevelTasks(frameworkContext)).ActivateService();
		(new ActivateServiceTaskPage(frameworkContext)).activateService(serviceInfo, serviceLevelTaskInfo);
	}

}
