package com.comcast.century.common;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.comcast.cso.pages.BuildHouseAccountTaskPage;
import com.comcast.cso.pages.CompleteCoaxBuildTaskPage;
import com.comcast.cso.pages.CompleteSiteBuildCoaxTaskPage;
import com.comcast.cso.pages.ConductCoaxSurveyTaskPage;
import com.comcast.cso.pages.ConductSiteSurveyCoaxTaskPage;
import com.comcast.cso.pages.ObtainCoaxPermitsTaskPage;
import com.comcast.cso.pages.ObtainSiteAgreementTaskPage;
import com.comcast.cso.pages.SiteLevelTasks;

public class CoaxTaskFlow extends NewConnectTest {
	
	// Coax Task Site flow

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
