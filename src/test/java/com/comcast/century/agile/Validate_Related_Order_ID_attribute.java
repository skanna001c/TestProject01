package com.comcast.century.agile;

import org.testng.annotations.Test;

import com.comcast.century.common.NewConnectTest;
import com.comcast.cm.pages.OrderSummaryTabCMPage;
import com.comcast.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.cso.pages.SiteLevelTasks;
import com.comcast.utils.PerfTransaction;


public class Validate_Related_Order_ID_attribute extends NewConnectTest {
	
	
	

	 @Test(priority=4600)
	  @PerfTransaction(name="RelateOrderID")
	  public void relatedOrderIDAttribute() throws InterruptedException{
		  String relatedOrderIDValue;
		  relatedOrderIDValue = (new OrderSummaryTabCMPage(frameworkContext)).verifyRelatedOrderIDAttribute();
		  getDataDump().setValue("relatedOrderIDValue_RT", relatedOrderIDValue);
	  }
	 
	 
	 @Test(priority=5100)
	  public void validateRelatedOrderID() throws InterruptedException {	
		  for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Fibercount_RT")); i++){
				SearchOrderndLaunchFiberSiteFlow(getDataDump().getValue("FiberSite" + i + "_RT"));		
				(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
				(new ConductSiteSurveyTaskPage(frameworkContext)).validateRelatedOrderIDAttribute(getDataDump().getValue("relatedOrderIDValue_RT"));
			}
	  }	
	
}
