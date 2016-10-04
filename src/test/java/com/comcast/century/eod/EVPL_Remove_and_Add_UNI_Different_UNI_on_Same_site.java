package com.comcast.century.eod;

import org.testng.annotations.Test;

import com.comcast.century.change.ServiceFlow_Change;
import com.comcast.cm.pages.ProcessTabPageCM;

public class EVPL_Remove_and_Add_UNI_Different_UNI_on_Same_site extends ServiceFlow_Change{

	@Test(priority = 23501)	
	public void UpgradeUNIPortSpeed() throws InterruptedException {		
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(1,"1GigE");
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(2,"1GigE");
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(3,"1GigE");		
	
	}
	
	@Test(priority = 23502)	
	public void UpgradeEVCBW() throws InterruptedException {
		new ProcessTabPageCM(frameworkContext).UpgradeEVCBW(1, "6Mbps");
		new ProcessTabPageCM(frameworkContext).UpgradeEVCBW(2, "6Mbps");
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();
		dataDump.setValue("EVCcount_RT", "2");
	}
}
