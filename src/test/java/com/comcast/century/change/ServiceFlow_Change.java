package com.comcast.century.change;

import java.awt.AWTException;
import java.lang.reflect.Method;


import org.testng.annotations.Test;



public class ServiceFlow_Change extends CoaxTaskFlow_Change{
	
	
	    // Service Request flow tasks
		@Test(priority = 70000)
		public void Contact_Customer_Change() throws InterruptedException, AWTException {
			Contact_Customer();

		}

		// Metro E Change flow tasks
		@Test(priority = 70100)
		public void Build_Update_Local_Biller_Account_Change() throws InterruptedException {
			Build_Update_Local_Biller_Account();
		}
		
		@Test(priority = 70200)
		public void Update_Design_Change() throws InterruptedException, AWTException {
			Update_Design();
		}

		@Test(priority = 70300)
		public void Ship_CPE_Change() throws InterruptedException {
			Ship_CPE();
		}

		@Test(priority = 70400)
		public void Create_Account_and_Equipment_Change() throws Exception {
			Create_Account_and_Equipment();
		}
			

		@Test(priority = 70500)
		public void Assign_Design_Info_Change() throws InterruptedException {
			Assign_Design_Info();
		}	


		@Test(priority = 70600)
		public void Generate_Core_Config_Change() throws InterruptedException {
			Generate_Core_Config();

		}

		@Test(priority = 70700)
		public void Generate_CPE_Config_Change() throws InterruptedException {
			Generate_Core_Config();
		}

		@Test(priority = 70800)
		public void Load_Core_Config_Change() throws InterruptedException {
			Load_Core_Config();
		}

		@Test(priority = 70900)
		public void Install_CPE_Change() throws InterruptedException {
			Install_CPE();
			
		}

		@Test(priority = 80000)	
		public void Install_CPE_Coax_Change() throws InterruptedException, AWTException {
			Install_CPE_Coax();
		}	

		@Test(priority = 80100)
		public void Set_Critical_Dates_Change() throws InterruptedException {
			Set_Critical_Dates();
		}

		@Test(priority = 80200)
		public void Day_of_Configs_Change() throws InterruptedException {
			Day_of_Configs();
		}

		@Test(priority = 80300)
		public void Activate_Service_Change() throws InterruptedException {
			Activate_Service();
		}


		@Test(priority = 80400)
		public void Notify_Customer_of_Service_Installation_Change() throws InterruptedException {
			Notify_Customer_of_Service_Installation();
		}

		
		@Test(priority = 80500)
		public void Complete_Customer_Acceptance_Testing_Change(Method method) throws InterruptedException {
			Complete_Customer_Acceptance_Testing(method);
		}

		@Test(priority = 80600)	
		public void Create_Order_Billing_Package_Change() throws AWTException, InterruptedException {
			Create_Order_Billing_Package();
		}


		@Test(priority = 80700)
		public void Start_Billing_Change() throws InterruptedException {
			Start_Billing();
		}

		
		@Test(priority = 80800)
		public void EqFeeStartBilling_Change() throws InterruptedException {
			EqFeeStartBilling();
		}	
}
