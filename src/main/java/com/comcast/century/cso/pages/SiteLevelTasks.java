package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class SiteLevelTasks extends Page {

	public SiteLevelTasks(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}

	@Override
	protected boolean isValidPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		// TODO Auto-generated method stub
		
	}
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//div[@class[contains(.,'refresh')]]")
	private WebElement btnRefresh;
	
	@FindBy(xpath = "//a[text()='Conduct Site Survey']")
	private WebElement taskConductSiteSurvey;
	
	@FindBy(xpath = "//a[text()='Obtain Site Agreement(s)']")
	private WebElement taskObtainSiteAgreement;
	
	@FindBy(xpath = "//a[text()='Conduct Fiber Plant Survey']")
	private WebElement taskConductFiberPlantSurvey;
	
	@FindBy(xpath = "//a[text()='Build House Account']")
	private WebElement taskBuildHouseAccount;
	
	@FindBy(xpath = "//a[text()='Complete Wavelength Reservation']")
	private WebElement taskCompleteWavelengthReservation;
	
	@FindBy(xpath = "//a[text()='Obtain Site Permits']")
	private WebElement taskObtainSitePermits;
	
	@FindBy(xpath = "//a[text()='Complete Site Build']")
	private WebElement taskCompleteSiteBuild;
	
	@FindBy(xpath = "//a[text()='Obtain Fiber Plant Permits']")
	private WebElement taskObtainFiberPlantPermits;
	
	@FindBy(xpath = "//*[text()='Complete Fiber Plant Build']")
	private WebElement taskCompleteFiberPlantBuild;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus = true;
	
	public boolean ClickRefreshButton() throws InterruptedException{
		try{
			if(waitForElement(btnRefresh)){
				clickndRelease(btnRefresh);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ConductSiteSurvey() throws InterruptedException{
		try{
			if(waitForElement(taskConductSiteSurvey)){
				if(checkifStatusChanged(taskConductSiteSurvey,btnRefresh,"INPROGRESS")){
					taskConductSiteSurvey.click();
					report.reportDoneEvent("Click ConductSiteSurvey Task", " ConductSiteSurvey Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ObtainSiteAgreement() throws InterruptedException{
		try{
			if(waitForElement(taskObtainSiteAgreement)){
				if(checkifStatusChanged(taskObtainSiteAgreement,btnRefresh,"INPROGRESS")){
					taskObtainSiteAgreement.click();
					report.reportDoneEvent("Click ObtainSiteAgreement Task", " ObtainSiteAgreement Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ConductFiberPlantSurvey() throws InterruptedException{
		try{
			if(waitForElement(taskConductFiberPlantSurvey)){
				if(checkifStatusChanged(taskConductFiberPlantSurvey,btnRefresh,"INPROGRESS")){
				taskConductFiberPlantSurvey.click();
				report.reportDoneEvent("Click ConductFiberPlantSurvey Task", " ConductFiberPlantSurvey Task Clicked");
			   }
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean BuildHouseAccount() throws InterruptedException{
		try{
			if(waitForElement(taskBuildHouseAccount)){
				if(checkifStatusChanged(taskBuildHouseAccount,btnRefresh,"INPROGRESS")){
				taskBuildHouseAccount.click();
				report.reportDoneEvent("Click BuildHouseAccount Task", " BuildHouseAccount Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CompleteWavelengthReservation() throws InterruptedException{
		try{
			if(waitForElement(taskCompleteWavelengthReservation)){
				if(checkifStatusChanged(taskCompleteWavelengthReservation,btnRefresh,"INPROGRESS")){
				taskCompleteWavelengthReservation.click();
				report.reportDoneEvent("Click CompleteWavelengthReservation Task", " CompleteWavelengthReservation Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ObtainSitePermits() throws InterruptedException{
		try{
			if(waitForElement(taskObtainSitePermits)){
				if(checkifStatusChanged(taskObtainSitePermits,btnRefresh,"INPROGRESS")){
				taskObtainSitePermits.click();
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CompleteSiteBuild() throws InterruptedException{
		try{
			if(waitForElement(taskCompleteSiteBuild)){
				if(checkifStatusChanged(taskCompleteSiteBuild,btnRefresh,"INPROGRESS")){
				taskCompleteSiteBuild.click();
				report.reportDoneEvent("Click CompleteSiteBuild Task", " CompleteSiteBuild Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ObtainFiberPlantPermits() throws InterruptedException{
		try{
			if(waitForElement(taskObtainFiberPlantPermits)){
				if(checkifStatusChanged(taskObtainFiberPlantPermits,btnRefresh,"INPROGRESS")){
				taskObtainFiberPlantPermits.click();
				report.reportDoneEvent("Click ObtainFiberPlantPermits Task", " ObtainFiberPlantPermits Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean CompleteFiberPlantBuild() throws InterruptedException{
		try{
			if(waitForElement(taskCompleteFiberPlantBuild)){
				if(checkifStatusChanged(taskCompleteFiberPlantBuild,btnRefresh,"INPROGRESS")){
				waitForElement(taskCompleteFiberPlantBuild);	
				jsClick(taskCompleteFiberPlantBuild);
				 //taskCompleteFiberPlantBuild.click();
				}
				waitforPageLoadComplete();
				report.reportDoneEvent("Click CompleteFiberPlantBuild Task", " CompleteFiberPlantBuild Task Clicked");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickBackButton() throws InterruptedException{
		try{
			if(waitForElement(btnBack)){
				clickndRelease(btnBack);
				//btnBack.click();
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Validate", "Site Level Tasks Completed", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}

}


