package com.comcast.century.disconnect;


import org.testng.annotations.Test;

import com.comcast.century.common.Supplements;
import com.comcast.century.cso.pages.DisconnectTaskPage;
import com.comcast.century.cso.pages.LoadCPEConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceDisconnectionTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.StartBillingTaskPage;
import com.comcast.century.disconnect.Disconnect;

public class Disconnect_ServiceLevelTaskFlow extends Disconnect {
           
	@Test(priority = 34500) //10989088
	public void Start_Billing() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).StopBilling())
			{
				(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Start_Billing");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	@Test(priority = 35000) //10989088
	public void Issue_Soft_Disconnect() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).Issue_Soft_Disconnect())
			{
				(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Issue_Soft_Disconnect");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	@Test(priority = 35500) //10989088
	public void Notify_Customer_of_Service_Disconnection() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).Notify_Customer_of_Service_Disconnection())
				
			{	
				(new NotifyCustomerofServiceDisconnectionTaskPage(frameworkContext)).Notify_Customer_of_Service_Disconnection();
				(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Notify_Customer_of_Service_Disconnection");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
		
	}
	
	@Test(priority = 36000) //10989088
	public void Generate_CPE_Config_SUP() throws InterruptedException {
		Generate_CPE_Config();
		
	}
	
	@Test(priority = 36500) //10989088
	public void Load_CPE_Configs() throws InterruptedException {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).LoadCPEConfigs())
				
			{	
				(new LoadCPEConfigsTaskPage(frameworkContext)).LoadCPEConfigs();
				(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("LoadCPEConfigs");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	
	
}
