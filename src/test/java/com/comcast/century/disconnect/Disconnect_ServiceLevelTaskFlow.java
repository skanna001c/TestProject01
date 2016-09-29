package com.comcast.century.disconnect;


import org.testng.annotations.Test;

import com.comcast.century.cso.pages.DisconnectTaskPage;
import com.comcast.century.cso.pages.LoadCPEConfigsTaskPage;
import com.comcast.century.cso.pages.NotifyCustomerofServiceDisconnectionTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;

public class Disconnect_ServiceLevelTaskFlow extends Disconnect {
	 
	@Test(priority = 34500) //34250//10989088
	public void Stop_Billing() throws InterruptedException {
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
	
	@Test(priority = 37000) //10989088
	public void Update_Local_Biller() throws InterruptedException  {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).Update_Local_Biller())
				
			{	(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Update_Local_Biller");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	
	@Test(priority = 37500) //10989088
	public void Generate_Core_Configs_SUP() throws InterruptedException  {
		Generate_Core_Config();
	}
	
	
	@Test(priority = 38500) //10989088
	public void Remove_Core_Configs() throws InterruptedException  {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).removeCoreConfigs())
				
			{	(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Update_Local_Biller");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	@Test(priority = 39000) //10989088
	public void Un_Assign_Design_Information() throws InterruptedException  {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).Un_Assign_Design_Information())
				
			{	(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Update_Local_Biller");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	//h2[contains(.,'Confirm Order Complete')]
	@Test(priority = 39500) //10989088
	public void Confirm_Order_Complete() throws InterruptedException  {
		for(int i=0; i < Integer.parseInt(getDataDump().getValue("EVCcount_RT")); i++){
			SearchOrderndLaunchServiceFlow(i);
			if((new ServiceLevelTasks(frameworkContext)).Confirm_Order_Complete())
				
			{	(new DisconnectTaskPage(frameworkContext)).ClickCompleteButton("Update_Local_Biller");
				//(new ServiceLevelTasks(frameworkContext)).ClickBackButton();
			}
			
		}
	}
	
	@Test(priority = 40000) //10989088
	public void CompleteTaskFlow() throws InterruptedException  {
		SearchOrder();
		
	}
}
