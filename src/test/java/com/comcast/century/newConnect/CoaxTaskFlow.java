package com.comcast.century.newConnect;

import java.awt.AWTException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.comcast.century.cm.pages.AccountTabPageCM;
import com.comcast.century.cm.pages.AddressTabPageCM;
import com.comcast.century.cm.pages.ContactTabPageCM;
import com.comcast.century.cm.pages.CustomerTabPageCM;
import com.comcast.century.cm.pages.FeatureTabPageCM;
import com.comcast.century.cm.pages.OrderSummaryTabCMPage;
import com.comcast.century.cm.pages.ProcessTabPageCM;
import com.comcast.century.cm.pages.ServiceTabPageCM;
import com.comcast.century.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.century.cso.pages.CompleteCoaxBuildTaskPage;
import com.comcast.century.cso.pages.CompleteSiteBuildCoaxTaskPage;
import com.comcast.century.cso.pages.ConductCoaxSurveyTaskPage;
import com.comcast.century.cso.pages.ConductFiberPlantSurveyTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyCoaxTaskPage;
import com.comcast.century.cso.pages.ConductSiteSurveyTaskPage;
import com.comcast.century.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.century.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.century.cso.pages.SiteLevelTasks;
import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.data.AccountInfo;
import com.comcast.century.data.ContactInfo;
import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.OrderSummaryInfo;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.SiteInfo;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTest;
import com.comcast.utils.PerfTransaction;

public class CoaxTaskFlow extends NewConnectTest {
	

	@Test(priority = 9500)
	public void Conduct_Site_Survey_Coax() throws InterruptedException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));		
			(new SiteLevelTasks(frameworkContext)).ConductSiteSurveyCoax();
			(new ConductSiteSurveyCoaxTaskPage(frameworkContext)).ConductSiteSurveyCoax(siteLevelTaskInfo);
		}
	}
	
	@Test(priority = 10000)
	public void Obtain_Site_Agreement_Coax() throws InterruptedException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainSiteAgreement();
			(new ObtainSiteAgreementTaskPage(frameworkContext)).ObtainSiteAgreement(siteLevelTaskInfo);
		}
	}
	
	@Test(priority = 10500)	
	public void Conduct_Coax_Survey() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ConductCoaxSurvey();
			(new ConductCoaxSurveyTaskPage(frameworkContext)).ConductCoaxSurvey(siteLevelTaskInfo);
		}
	}
	
	@Test(priority = 11000)
	public void Build_House_Account_Coax() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			if ((new SiteLevelTasks(frameworkContext)).BuildHouseAccount()) {
				(new BuildHouseAccountTaskPage(frameworkContext)).BuildHouseAccount(siteLevelTaskInfo);
			}
		}

	}
	
	@Test(priority = 11500)	
	public void Obtain_Coax_Permit() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).ObtainCoaxPermits();
			(new ObtainCoaxPermitsTaskPage(frameworkContext)).ObtainCoaxPermits();
		}
	}
	
	@Test(priority = 12000)	
	public void Complete_Site_Build_Coax() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).CompleteSiteBuildCoax();
			(new CompleteSiteBuildCoaxTaskPage(frameworkContext)).ClickCompleteButton();
			(new CompleteSiteBuildCoaxTaskPage(frameworkContext)).closePopup();
		}
	}
	
	
	@Test(priority = 12500)	
	public void Complete_Coax_Build() throws InterruptedException, AWTException {
		for(int i=1; i <= Integer.parseInt(getDataDump().getValue("Coaxcount_RT")); i++){
			SearchOrderndLaunchCoaxSiteFlow(getDataDump().getValue("CoaxSite" + i + "_RT"));
			(new SiteLevelTasks(frameworkContext)).CompleteCoaxBuild();
			(new CompleteCoaxBuildTaskPage(frameworkContext)).ClickCompleteButton();
			if (new CompleteCoaxBuildTaskPage(frameworkContext).closePopup()) {
				UpdateRespectiveCoaxSiteFlows();
			}
		}
	}
	
	private void UpdateRespectiveCoaxSiteFlows() {
		for (int i = 1; i <= Integer.parseInt(settings.getValue("MAXNOOFCOAXSITES")); i++) {
			if (!(getDataDump().getValue("CoaxSiteFlow" + i).equalsIgnoreCase("pass"))) {
				getDataDump().setValue("CoaxSiteFlow" + i, "PASS");
				break;
			}
		}		
	}
	
	
}
