package com.comcast.century.techsup;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.SupplementPageServiceTabCM;
import com.comcast.century.common.NewConnectTest;
import com.comcast.century.common.Supplements;
import com.comcast.century.cso.pages.ContactCustomerTaskPage;
import com.comcast.century.cso.pages.ServiceLevelTasks;
import com.comcast.century.cso.pages.UpdateDesignTaskPage;
import com.comcast.century.data.SupplementInfo;
import com.comcast.utils.PerfTransaction;

public class ServiceFlow_Techsup extends Supplements {
 
	
	//#######################################TECH-SUP-SERVICE-FLOW############################################################
	@Test(priority = 36000)
	public void Contact_Customer_Supp() throws InterruptedException, AWTException {
		Contact_Customer();

	}

	@Test(priority = 36001)
	public void Update_Design_Supp() throws InterruptedException, AWTException {
		Update_Design();

	}
	
	@Test(priority = 36002)
	public void Build_Update_Local_Biller_Account_Supp() throws InterruptedException, AWTException {
		Build_Update_Local_Biller_Account();

	}
	
	@Test(priority = 36003)
	public void Ship_CPE_Supp() throws InterruptedException, AWTException {
		Ship_CPE();

	}
	
	@Test(priority = 36004)
	public void Create_Account_and_Equipment_Supp() throws Exception {
		Create_Account_and_Equipment();

	}
	
	@Test(priority = 36005)
	public void Assign_Design_Info_Supp() throws InterruptedException, AWTException {
		Assign_Design_Info();

	}
	
	@Test(priority = 36006)
	public void Generate_Core_Config_Supp() throws InterruptedException, AWTException {
		Generate_Core_Config();

	}
	
	@Test(priority = 36007)
	public void Generate_CPE_Config_Supp() throws InterruptedException, AWTException {
		Generate_CPE_Config();

	}
	
	@Test(priority = 36008)
	public void Load_Core_Config_Supp() throws InterruptedException, AWTException {
		Load_Core_Config();

	}
	
	@Test(priority = 36009)
	public void Install_CPE_Supp() throws InterruptedException, AWTException {
		Install_CPE();

	}
	
	@Test(priority = 36010)
	public void Set_Critical_Dates_Supp() throws InterruptedException, AWTException {
		Set_Critical_Dates();

	}
	
	@Test(priority = 36011)
	public void Day_of_Configs_Supp() throws InterruptedException, AWTException {
		Day_of_Configs();

	}
	
	@Test(priority = 36012)
	public void Activate_Service_Supp() throws InterruptedException, AWTException {
		Activate_Service();

	}
	
	@Test(priority = 36013)
	public void Notify_Customer_of_Service_Installation_Supp() throws InterruptedException, AWTException {
		Notify_Customer_of_Service_Installation();

	}
	
	@Test(priority = 36014)
	public void Complete_Customer_Acceptance_Testing_Supp() throws InterruptedException, AWTException {
		Complete_Customer_Acceptance_Testing();

	}
	
	@Test(priority = 36015)
	public void Create_Order_Billing_Package_Supp() throws InterruptedException, AWTException {
		Create_Order_Billing_Package();

	}
	
	@Test(priority = 36016)
	public void Start_Billing_Supp() throws InterruptedException, AWTException {
		Start_Billing();

	}
	
	@Test(priority = 36017)
	public void EqFeeStartBilling_Supp() throws InterruptedException, AWTException {
		EqFeeStartBilling();
	}
	

//##########################################TECHSUP-TRUNK-PRI-FLOW##############################################################
	
	@Test(priority = 36018)
	public void Build_Update_Local_Biller_Account_PRISupp() throws InterruptedException, AWTException {
		Build_Update_Local_Biller_Account_PRI();
	}
	
	@Test(priority = 36019)
	public void Update_Design_PRISupp() throws InterruptedException, AWTException {
		Update_Design_PRI();
	}
	
	@Test(priority = 36020)
	public void Create_Account_and_Equipment_PRISupp() throws Exception {
		Create_Account_and_Equipment_PRI();
	}
	
	@Test(priority = 36021)
	public void Ship_CPE_PRISupp() throws Exception {
		Ship_CPE_PRI();
	}
	
	@Test(priority = 36022)
	public void Assign_Design_Info_PRISupp() throws Exception {
		Assign_Design_Info_PRI();
	}
	
	@Test(priority = 36023)
	public void Generate_CPE_Config_PRISupp() throws Exception {
		Generate_CPE_Config_PRI();
	}
	
	@Test(priority = 36024)
	public void Generate_Core_Config_PRISupp() throws Exception {
		Generate_Core_Config_PRI();
	}
	
	@Test(priority = 36025)
	public void Load_Core_Config_PRISupp() throws Exception {
		Load_Core_Config_PRI();
	}
	
	@Test(priority = 36026)
	public void Install_CPE_PRISupp() throws Exception {
		Install_CPE_PRI();
	}
	
	@Test(priority = 36027)
	public void Set_Critical_Dates_PRISupp() throws Exception {
		Set_Critical_Dates_PRI();
	}
	
	@Test(priority = 36028)
	public void Day_of_Configs_PRISupp() throws Exception {
		Day_of_Configs_PRI();
	}
	
	@Test(priority = 36029)
	public void Activate_Service_PRISupp() throws Exception {
		Activate_Service_PRI();
	}
	
//##############################################TECHSUP-BGP-FLOW###################################################################
	
	@Test(priority = 36030)
	public void Assign_Design_BGP_Supp() throws InterruptedException, AWTException {
		Assign_Design_BGP();
	}
	
	@Test(priority = 36031)
	public void Generate_CPE_Config_BGPSupp() throws InterruptedException, AWTException {
		Generate_CPE_Config_BGP();
	}
	
	@Test(priority = 36032)
	public void Set_Critical_Dates_BGPSupp() throws InterruptedException, AWTException {
		Set_Critical_Dates_BGP();
	}
	
	@Test(priority = 36033)
	public void Day_of_Configs_BGPSupp() throws InterruptedException, AWTException {
		Day_of_Configs_BGP();
	}
	
	@Test(priority = 36034)
	public void Notify_Customer_of_Service_Installation_BGPSupp() throws InterruptedException, AWTException {
		Notify_Customer_of_Service_Installation_BGP();
	}
	
	@Test(priority = 36035)
	public void Start_Billing_BGPSupp() throws InterruptedException, AWTException {
		Start_Billing_BGP();
	}
	
	
	
}
