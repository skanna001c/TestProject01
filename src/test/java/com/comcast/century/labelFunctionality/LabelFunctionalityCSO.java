package com.comcast.century.labelFunctionality;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cso.pages.ManageTab;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class LabelFunctionalityCSO extends ComcastTest {

	
	
	@BeforeClass
	  public void beforeTest() {
				  
	  }
	
	@Test(priority=100)
	  @PerfTransaction(name="CreateLabelCSO")
	  public void createLabelCSO() throws InterruptedException{
		String labelName;
		if((new ManageTab(browser,report)).clickOnManageTab()){
			if((new ManageTab(browser,report)).goToLabelPageCSO()){
			labelName=(new ManageTab(browser,report)).createLabel();
			getDataDump().setValue("LabelName_RT", labelName);
			}else Assert.fail("Go to label page CSO failed");
		}else Assert.fail("Click on Manage Tab failed");
		
	}
	
	
}