package com.comcast.century.TechSup;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cm.pages.SupplementPageServiceTabCM;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.century.data.SupplementInfo;
import com.comcast.century.newConnect.NewConnectTest;
import com.comcast.utils.PerfTransaction;

public class InitiateTechSup extends NewConnectTest {


	@Test(priority = 9100)
	public void updateCMStatusFail() {
		getDataDump().setValue("CM_Status", "FAIL");
		getDataDump().setValue("CMLoggedIN", "FAIL");
	}

	@Test(priority = 9200)
	@PerfTransaction(name = "SearchInprogressOrder")
	public void searchInprogressOrder() throws InterruptedException {
		if (settings.getPERerunStatus().equalsIgnoreCase("false")) {
			new HomePageCM(frameworkContext).searchCustomer(getDataDump().getValue("CustomerName_RT"));
		}
	}

	@Test(priority = 9300)
	@PerfTransaction(name = "InitiateSupplements")
	public void initiateSupplements() throws InterruptedException {		

		if (new CustomerTabPageCM(frameworkContext).clickSRIDInOrderHierarchy(getDataDump().getValue("CustomerName_RT"),
				supplementInfo)) {
			if ((new SupplementPageServiceTabCM(frameworkContext)).placeSupplements(supplementInfo)) {
			}
		}

	}

}
