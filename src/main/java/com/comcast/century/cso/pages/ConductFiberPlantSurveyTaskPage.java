package com.comcast.century.cso.pages;

import java.awt.AWTException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class ConductFiberPlantSurveyTaskPage extends Page {

	public ConductFiberPlantSurveyTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(ConductFiberPlantSurveyTaskPage.class);
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
	@FindBy(xpath = "//*[contains(text(),'Fiber Information')]")
	private WebElement tabFiberInformation;
	
	@FindBy(xpath = "//div[contains(text(),'Fiber Survey Results')]/..")
	private WebElement tabFiberSurveyResults;
	
	@FindBy(id = "surveyCompDate-inputEl")
	private WebElement SurveyCompletionDate;

	@FindBy(xpath = "//*[text()='Today']")
	private List<WebElement> btnToday;
	
	@FindBy(id = "permitReqd")
	private WebElement ddPermitRequired;
	
	@FindBy(id = "onstructReqd")
	private WebElement ddConstructionRequired;
	
	@FindBy(id = "headEndDistance")
	private WebElement txtHeadEndDistance;
	
	@FindBy(id = "nodeNumber")
	private WebElement txtNodeNumber;
	
	@FindBy(id = "serviceable")
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
	
	private boolean mstatus = true;
	
	public boolean ConductFiberPlantSurvey(SiteLevelTaskInfo siteLevelTaskInfo) throws AWTException, InterruptedException{
		try{
			if(waitForElement(tabFiberInformation)){
				clickndRelease(tabFiberInformation);
				waitForElement(SurveyCompletionDate);
				SurveyCompletionDate.click();
				SurveyCompletionDate.clear();
				SurveyCompletionDate.sendKeys(getCurrentDate());
				new Select(ddPermitRequired).selectByVisibleText("Yes");
				new Select(ddConstructionRequired).selectByVisibleText("Yes");
				waitForElement(tabFiberSurveyResults);
				clickndRelease(tabFiberSurveyResults);
				waitForElement(txtHeadEndDistance);
				txtHeadEndDistance.sendKeys(randomNumber(5));
				txtNodeNumber.sendKeys(randomNumber(5));
				new Select(ddServiceable).selectByVisibleText("No");
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Conduct Fiber Plant Survey' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		try{
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
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	

}
