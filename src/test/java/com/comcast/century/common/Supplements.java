package com.comcast.century.common;

import org.testng.annotations.Test;

import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.SupplementPageServiceTabCM;
import com.comcast.utils.PerfTransaction;

public class Supplements extends TrunkPRIFlow {

	@Test(priority = 23000)
	@PerfTransaction(name = "SearchSRID")
	public void searchSRID() throws InterruptedException {
		if (settings.getPERerunStatus().equalsIgnoreCase("false")) {
			new HomePageCM(frameworkContext).searchCustomer(getDataDump().getValue("CustomerName_RT"));
		}
	}

	@Test(priority = 23500)
	@PerfTransaction(name = "InitiateSupplements")
	public void initiateSupplements() throws InterruptedException {

		if (new CustomerTabPageCM(frameworkContext).clickSRIDInOrderHierarchy(getDataDump().getValue("CustomerName_RT"),
				supplementInfo)) {
			if ((new SupplementPageServiceTabCM(frameworkContext)).placeSupplements(supplementInfo)) {
			}
		}

	}

	@Test(priority = 35000)
	@PerfTransaction(name = "SubmitOrderSupplements")
	public void submitOrderSupplements() throws InterruptedException {
		String SUP_SRID = null;
		SUP_SRID = new OrderSummaryTabCMPage(frameworkContext).submitOrder(orderSummaryInfo, accountInfo.eRate);
		getDataDump().setValue("SUP_SRID_RT", SUP_SRID);
		startCSO();

	}

}
