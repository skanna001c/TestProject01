package com.comcast.century.cso.pages;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class ConductFiberPlantSurveyTaskPage extends Page {

	public ConductFiberPlantSurveyTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//*[contains(text(),'Fiber Information')]")
	private WebElement tabFiberInformation;
	
	@FindBy(xpath = "//div[contains(text(),'Fiber Survey Results')]/..")
	private WebElement tabFiberSurveyResults;
	
	@FindBy(xpath = "//input[@id='surveyCompDate-inputEl']/../following-sibling::*/child::*")
	private WebElement SurveyCompletionDate;

	@FindBy(xpath = "//span[text()='Today']")
	private WebElement btnToday;
	
	@FindBy(xpath = "//select[@id='permitReqd']")
	private WebElement ddPermitRequired;
	
	@FindBy(xpath = "//select[@id='constructReqd']")
	private WebElement ddConstructionRequired;
	
	@FindBy(xpath = "//input[@id='headEndDistance']")
	private WebElement txtHeadEndDistance;
	
	@FindBy(xpath = "//input[@id='nodeNumber']")
	private WebElement txtNodeNumber;
	
	@FindBy(xpath = "//select[@id='serviceable']")
	private WebElement ddServiceable;
	
	@FindBy(xpath = "//span[text()='Yes']/following-sibling::*")
	private WebElement btnYes ;
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	
	public void ConductFiberPlantSurvey(SiteLevelTaskInfo siteLevelTaskInfo) throws AWTException, InterruptedException{
		if(waitForElement(tabFiberInformation)){
			clickndRelease(tabFiberInformation);
			waitForElement(SurveyCompletionDate);
			SurveyCompletionDate.click();
			btnToday.click();
			new Select(ddPermitRequired).selectByIndex(1);
			new Select(ddConstructionRequired).selectByIndex(1);
			waitForElement(tabFiberSurveyResults);
			clickndRelease(tabFiberSurveyResults);			
			waitForElement(txtHeadEndDistance);
			txtHeadEndDistance.sendKeys(RandomNumber());
			txtNodeNumber.sendKeys(RandomNumber());
			new Select(ddServiceable).selectByIndex(2);
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
			btnYes.click();
			waitforPageLoadComplete();
			report.reportDoneEvent("Complete ConductFiberPlantSurvey Task", " ConductFiberPlantSurvey Task Completed");
		}
	}
	
	

}
