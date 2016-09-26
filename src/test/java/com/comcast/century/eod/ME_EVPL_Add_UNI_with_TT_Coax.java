package com.comcast.century.eod;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.comcast.century.change.CoaxTaskFlow_Change;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.HomePageCM;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.common.Supplements;
import com.comcast.utils.PerfTransaction;

public class ME_EVPL_Add_UNI_with_TT_Coax extends CoaxTaskFlow_Change {

	protected String site;
	
	@Test(priority = 23100)	
	@PerfTransaction(name = "addSiteAddress")
	public void addSiteAddress() throws InterruptedException {
		new AddressTabPageCM(frameworkContext).addSiteAddress();
		 site = new AddressTabPageCM(frameworkContext).EnterSiteDetailsInvalid(siteInfo); 
		 dataDump.setValue("SITE1_RT", site);
		 new ContactTabPageCM(frameworkContext).CreateSiteTechnicalContact(contactInfo);		 
		 
	}
	
	@Test(priority = 23600)	
	@PerfTransaction(name = "addUNIWithCoax")
	public void addUNIWithCoax() throws InterruptedException, AWTException {
		new FeatureTabPageCM(frameworkContext).clickOnFeatureTab();
		new FeatureTabPageCM(frameworkContext).updateUNIandEVCCount("4", "3");
		new ProcessTabPageCM(frameworkContext).UNI4Configuration(processInfo, dataDump.getValue("SITE1_RT"), "Coax");
		dataDump.setValue("Coaxcount_RT", "1");
		new ProcessTabPageCM(frameworkContext).EVC3Configuration_EVPL(processInfo);
		dataDump.setValue("CoaxSite1_RT", site);
		new ProcessTabPageCM(frameworkContext).ClickOnContinueButton();		
		
	}
	
}
 