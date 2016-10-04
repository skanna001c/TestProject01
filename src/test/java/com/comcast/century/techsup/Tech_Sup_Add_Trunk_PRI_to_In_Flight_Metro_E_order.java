package com.comcast.century.techsup;

import org.testng.annotations.Test;

import com.comcast.cm.pages.FeatureTabPageCM;
import com.comcast.cm.pages.ProcessTabPageCM;
import com.comcast.cm.pages.ServiceTabPageCM;

public class Tech_Sup_Add_Trunk_PRI_to_In_Flight_Metro_E_order extends ServiceFlow_Techsup {
	
	@Test(priority = 30000)
	public void addTrunkPRIToEDI() throws InterruptedException {
		new ServiceTabPageCM(frameworkContext).clickOnPlanSearchTab();
		new ServiceTabPageCM(frameworkContext).selectTrunkPRI();
		new FeatureTabPageCM(frameworkContext).ClickOnContinueButton();
		new ProcessTabPageCM(frameworkContext).TrunkPRIConfiguration(processInfo, getDataDump().getValue("SITE1_RT"), serviceInfo);
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
	}

}
