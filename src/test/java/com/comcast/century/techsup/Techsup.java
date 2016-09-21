package com.comcast.century.TechSup;

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

public class Techsup extends Supplements {
 
	@Test(priority = 30000)
	@PerfTransaction(name = "AddBGPandSubmitOrder")
	public void addBGP() throws InterruptedException {		
		new FeatureTabPageCM(frameworkContext).clickOnFeatureTab();
		new FeatureTabPageCM(frameworkContext).selectBGP();
		new ProcessTabPageCM(frameworkContext).BGPConfiguration();
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
	}
	
	
	@Test(priority = 36000)
	public void Contact_Customer_Supp() throws InterruptedException, AWTException {
		new NewConnectTest().Contact_Customer();

	}

	@Test(priority = 37000)
	public void Update_Design_Supp() throws InterruptedException, AWTException {
		new NewConnectTest().Update_Design();

	}
	
	
}
