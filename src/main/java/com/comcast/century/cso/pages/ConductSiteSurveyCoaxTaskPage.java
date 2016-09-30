package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class ConductSiteSurveyCoaxTaskPage extends Page {

	public ConductSiteSurveyCoaxTaskPage(FrameworkContext context) {
		super(context);
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
	
	@FindBy(xpath = "//*[.='Site Survey Information']")
	private WebElement tabSiteServeyInformation;
	
	@FindBy(xpath = "//input[@id='scheduledSurveyString']/following-sibling::*")
	private WebElement ScheduledSurveyDate ;
	
	@FindBy(xpath = "//input[@id='surveyCompletionString']/following-sibling::*")
	private WebElement SurveyCompletionDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	@FindBy(xpath = "//select[@id='permitRequired']")
	private WebElement ddSitePermitRequired ;
	
	@FindBy(xpath = "//select[@id='siteType']")
	private WebElement ddCPElocationType ;
	
	@FindBy(xpath = "//select[@id='powerStatus']")
	private WebElement ddPowerStatus ;
	
	@FindBy(xpath = "//select[@id='equipmentLocationDescription']")
	private WebElement ddEquipmentLocationDescription ;
	
	//button[text()='Yes']
	
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnYes ;
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;
	
	private boolean mstatus= true;
	public boolean ConductSiteSurveyCoax(SiteLevelTaskInfo siteLevelTaskInfo){
		try{
			if(waitForElement(ScheduledSurveyDate)){
				ScheduledSurveyDate.click();
				btnToday.get(0).click();
				SurveyCompletionDate.click();
				btnToday.get(1).click();
				waitForElement(ddSitePermitRequired);
				new Select(ddSitePermitRequired).selectByVisibleText("No");
				new Select(ddCPElocationType).selectByVisibleText("Raw Land Site");
				new Select(ddPowerStatus).selectByVisibleText("Existing Power");
				new Select(ddEquipmentLocationDescription).selectByVisibleText("Leased Area");
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Conduct Site Survey(Coax)' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickBackButton(){
		try{
			if(waitForElement(btnBack)){
				btnBack.click();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickSaveButton(){
		try{
			if(waitForElement(btnSave)){
				btnSave.click();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, null, "Complete ConductSiteSurveyCoax Task: Complete ConductSiteSurveyCoax Task page: CompleteButton");
				waitforPageLoadComplete();
				waitForElement(btnYes);
				btnYes.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ConductSiteSurveyCoax Task", " ConductSiteSurveyCoax Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
}
