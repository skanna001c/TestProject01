package com.comcast.century.TechSup;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cm.pages.SupplementPageServiceTabCM;
import com.comcast.century.newConnect.NewConnectTest;
import com.comcast.utils.PerfTransaction;

public class ME_EDI_TechSup_Add_BGP extends NewConnectTest {
	
	
	@Test(priority = 9100)
	public void updateCMStatusFail()
	{
		getDataDump().setValue("CM_Status", "FAIL");
		getDataDump().setValue("CMLoggedIN", "FAIL");		
	}
	   
	
	@Test(priority = 9200)
	@PerfTransaction(name = "SearchInprogressOrder")
	public void searchInprogressOrder() throws InterruptedException {
		
		if((new HomePageCM(frameworkContext)).searchOrder(getDataDump().getValue("SRID_RT"))){
			if((new HomePageCM(frameworkContext)).clickCustomerName(getDataDump().getValue("CustomerName_RT"))){
			}else Assert.fail("Customer not clicked");
		}else Assert.fail("Order not searched");
	
	}
	
	@Test(priority = 9300)
	@PerfTransaction(name = "InitiateTechSup")
	public void initiateTechSup() throws InterruptedException {
		
		if(new CustomerTabPageCM(frameworkContext).serachOrderHierarchy(getDataDump().getValue("CustomerName_RT"))){
			if((new SupplementPageServiceTabCM(frameworkContext)).techSupEDI()){
			}
		}
		
	
	}
  
}

