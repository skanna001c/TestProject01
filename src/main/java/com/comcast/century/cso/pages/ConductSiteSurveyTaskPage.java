package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class ConductSiteSurveyTaskPage extends Page {

	public ConductSiteSurveyTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	public void ConductSiteSurvey(SiteLevelTaskInfo siteLevelTaskInfo){
		if(waitForElement(ScheduledSurveyDate)){
			ScheduledSurveyDate.click();
			btnToday.get(0).click();
			SurveyCompletionDate.click();
			btnToday.get(1).click();
			waitForElement(ddSitePermitRequired);
			new Select(ddSitePermitRequired).selectByIndex(2);
			new Select(ddCPElocationType).selectByIndex(1);
			new Select(ddPowerStatus).selectByIndex(1);
			new Select(ddEquipmentLocationDescription).selectByIndex(1);	
			this.ClickCompleteButton();
		}
	}
	
	public void ClickBackButton(){
		if(waitForElement(btnBack)){
			btnBack.click();
		}
	}
	
	public void ClickSaveButton(){
		if(waitForElement(btnSave)){
			btnSave.click();
		}
	}
	
	public void ClickCompleteButton(){
		if(waitForElement(btnComplete)){
			btnComplete.click();
			waitforPageLoadComplete();
			waitForElement(btnYes);
			btnYes.click();
			waitforPageLoadComplete();
			report.reportDoneEvent("Complete ConductSiteSurvey Task", " ConductSiteSurvey Task Completed");
		}
	}
}
