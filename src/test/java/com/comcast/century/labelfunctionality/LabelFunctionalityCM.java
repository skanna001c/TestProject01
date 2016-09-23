package com.comcast.century.labelfunctionality;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.common.NewConnectTest;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.ManageTab;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.cso.pages.WorkOrderTabServiceRequestPage;
import com.comcast.utils.PerfTransaction;

public class LabelFunctionalityCM extends NewConnectTest {

	
	@Test(priority=50)
	  @PerfTransaction(name="CreateLabelCM")
	  public void createLabelCM() throws InterruptedException{
		String labelName;
		if((new ManageTab(frameworkContext)).clickOnManageTab()){
			labelName=(new ManageTab(frameworkContext)).createLabel();
			getDataDump().setValue("LabelName_RT", labelName);
		}else Assert.fail("Click on Manage Tab failed");
		
	}
	
	@Test(priority=4600)
	  @PerfTransaction(name="AssignLabelCM")
	  public void assignLabelCM() throws InterruptedException{
		if((new OrderSummaryTabCMPage(frameworkContext)).assignLabelCM(getDataDump().getValue("LabelName_RT"))){
		}else Assert.fail("Label not assigned");
	}
	
	
	@Test(priority=4650)
	  @PerfTransaction(name="SubmitOrderAndVerifyNotes")
	  public void submitOrderAndVerifyNotes() throws InterruptedException{
		if (getDataDump().getValue("submitOrderAndVerifyNotes_status").equalsIgnoreCase("FAIL")) {
			selectService();
			configureService();
			processService();
			assignLabelCM();
		}
		if((new OrderSummaryTabCMPage(frameworkContext)).submitOrder(orderSummaryInfo,accountInfo.eRate)){
			  if((new OrderSummaryTabCMPage(frameworkContext)).verifyNotesForLabels()){
			}else Assert.fail("Notes Verification failed");
		}else Assert.fail("Submit Order Failed");	
	}
	
	@Test(priority=4660)
	  @PerfTransaction(name="VerifyLabelCountCM")
	  public void verifyLabelCountCM() throws InterruptedException{
		if((new HomePageCM(frameworkContext)).clickOnHomeTab()){
			if((new HomePageCM(frameworkContext)).searchOrder(getDataDump().getValue("SRID_RT"))){
				if((new HomePageCM(frameworkContext)).verifyLabelCountAndOrderStatus()){
					getDataDump().setValue("CM_Status", "PASS");
				}else Assert.fail("Label count not verified");
			}else Assert.fail("SR ID Search failed");
		}else Assert.fail("Click on home tab failed");
	}
	
	@Test(priority=4670)
	  @PerfTransaction(name="VerifyLabelCountCSO")
	  public void verifyLabelCSO() throws InterruptedException{
		if((new WorkOrderTabServiceRequestPage(frameworkContext)).verifyLabelCSO(getDataDump().getValue("SRID_RT"), getDataDump().getValue("LabelName_RT"))){
			if((new WorkOrderTabPageCSO(frameworkContext)).verifyDisplayOfLabelName(getDataDump().getValue("LabelName_RT"),getDataDump().getValue("FiberSite1_RT"))){
				(new SiteLevelTasks(frameworkContext)).ConductSiteSurvey();
				(new ConductSiteSurveyTaskPage(frameworkContext)).validateDisplayLabelInTask(getDataDump().getValue("LabelName_RT"));
			} else Assert.fail("Display label failed");
		}else Assert.fail("Verify label CSO Failed");
		
	}
	
	
}

