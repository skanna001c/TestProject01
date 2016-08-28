package com.comcast.century.newConnect;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.utils.PerfTransaction;


public class Validate_Related_Order_ID_attribute extends NewConnectTest {
	
	
	

	 @Test(priority=650)
	  @PerfTransaction(name="RelateOrderID")
	  public void relatedOrderIDAttribute() throws InterruptedException{
		  String relatedOrderIDValue;
		  relatedOrderIDValue = (new OrderSummaryTabCMPage(browser, report)).verifyRelatedOrderIDAttribute();
		  getDataDump().setValue("relatedOrderIDValue_RT", relatedOrderIDValue);
	  }
	 
	 
	 @Test(priority=850)
	  public void validateRelatedOrderID() throws InterruptedException {	
		  if (getDataDump().getValue("Conduct_Site_Survey_status").equalsIgnoreCase("fail"))
		  {
			  StartCSO();
		  }
		  (new SiteLevelTasks(browser, report)).ConductSiteSurvey();
		  (new ConductSiteSurveyTaskPage(browser, report)).validateRelatedOrderIDAttribute(getDataDump().getValue("relatedOrderIDValue_RT"));
	  }	
	
}
