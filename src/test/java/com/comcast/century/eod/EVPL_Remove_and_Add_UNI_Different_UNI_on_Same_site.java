package com.comcast.century.eod;

import org.testng.annotations.Test;

import com.comcast.century.change.ServiceFlow_Change;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.utils.PerfTransaction;

public class EVPL_Remove_and_Add_UNI_Different_UNI_on_Same_site extends ServiceFlow_Change{

	@Test(priority = 4000)	
	public void UpgradeUNIPortSpeed() throws InterruptedException {
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(1,"1GigE");
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(2,"1GigE");
		new ProcessTabPageCM(frameworkContext).UpgradeUNIPortSpeed(3,"1GigE");		
	
	}
}
