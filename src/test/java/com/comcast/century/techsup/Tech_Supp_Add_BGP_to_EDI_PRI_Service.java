package com.comcast.century.techsup;

import org.testng.annotations.Test;

import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.ProcessTabPageCM;

public class Tech_Supp_Add_BGP_to_EDI_PRI_Service extends ServiceFlow_Techsup {

	@Test(priority = 30000)
	public void addBGP() throws InterruptedException {
		new FeatureTabPageCM(frameworkContext).clickOnFeatureTab();
		new FeatureTabPageCM(frameworkContext).selectBGP();
		new ProcessTabPageCM(frameworkContext).BGPConfiguration();
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
	}
}
