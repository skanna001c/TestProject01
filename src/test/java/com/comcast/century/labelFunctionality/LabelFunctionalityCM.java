package com.comcast.century.labelFunctionality;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.century.cso.pages.ManageTab;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class LabelFunctionalityCM extends ComcastTest {

	
	
	 @BeforeClass
	  public void beforeTest() {
				  
	  }
	
	@Test(priority=100)
	  @PerfTransaction(name="CreateLabelCM")
	  public void createLabelCM() throws InterruptedException{
		String labelName;
		if((new ManageTab(browser,report)).clickOnManageTab()){
			labelName=(new ManageTab(browser,report)).createLabel();
			getDataDump().setValue("LabelName_RT", labelName);
		}else Assert.fail("Click on Manage Tab failed");
		
	}
	
}
