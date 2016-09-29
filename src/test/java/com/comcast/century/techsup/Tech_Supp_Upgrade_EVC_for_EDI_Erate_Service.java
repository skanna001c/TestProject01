package com.comcast.century.techsup;

import org.testng.annotations.Test;

import com.comcast.century.cm.pages.ProcessTabPageCM;

public class Tech_Supp_Upgrade_EVC_for_EDI_Erate_Service extends ServiceFlow_Techsup {
                     
	
	@Test(priority = 30001)
	public void upgradeEVC() throws InterruptedException {
		new ProcessTabPageCM(frameworkContext).UpgradeEVCBW(1, "20Mbps");
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
	}
	
}
