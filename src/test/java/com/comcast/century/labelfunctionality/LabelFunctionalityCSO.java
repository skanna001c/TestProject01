package com.comcast.century.labelfunctionality;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.cso.pages.ManageTab;
import com.comcast.utils.ComcastTestMain;
import com.comcast.utils.PerfTransaction;

public class LabelFunctionalityCSO extends ComcastTestMain {

	
	
	@BeforeClass
	  public void beforeTest() {
				  
	  }
	
	@Test(priority=100)
	  @PerfTransaction(name="CreateLabelCSO")
	  public void createLabelCSO() throws InterruptedException{
		String labelName;
		if((new ManageTab(frameworkContext)).clickOnManageTab()){
			if((new ManageTab(frameworkContext)).goToLabelPageCSO()){
			labelName=(new ManageTab(frameworkContext)).createLabel();
			getDataDump().setValue("LabelName_RT", labelName);
			}else Assert.fail("Go to label page CSO failed");
		}else Assert.fail("Click on Manage Tab failed");
		
	}
	
	
}
