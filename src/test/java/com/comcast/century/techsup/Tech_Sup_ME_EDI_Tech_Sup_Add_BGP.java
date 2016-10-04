package com.comcast.century.techsup;

import org.testng.annotations.Test;

import com.comcast.cm.pages.FeatureTabPageCM;
import com.comcast.cm.pages.ProcessTabPageCM;

public class Tech_Sup_ME_EDI_Tech_Sup_Add_BGP extends ServiceFlow_Techsup {

	@Test(priority = 30000)
	public void addBGPToEDI() throws InterruptedException {
		new FeatureTabPageCM(frameworkContext).clickOnFeatureTab();
		new FeatureTabPageCM(frameworkContext).selectBGP();
		new ProcessTabPageCM(frameworkContext).BGPConfiguration();
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
	}

}
