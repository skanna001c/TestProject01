package com.comcast.cso.pages;

import java.awt.AWTException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.data.SiteLevelTaskInfo;
import com.comcast.utils.Page;




public class ConductCoaxSurveyTaskPage extends Page {

	public ConductCoaxSurveyTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(ConductCoaxSurveyTaskPage.class);
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
	@FindBy(xpath = "//*[text()='Coax Survey Information']")
	private WebElement tabCoaxServeyInformation;
	
	@FindBy(xpath = "//*[text()='Coax Survey Results']")
	private WebElement tabCoaxSurveyResults;
	
	@FindBy(xpath = ".//*[@id='surveyCompDate']/following-sibling::*")
	private WebElement SurveyCompletionDate;

	@FindBy(xpath = "//*[text()='Today']")
	private List<WebElement> btnToday;
	
	@FindBy(id = "ospPermitReqd")
	private WebElement ddOSPPermitRequired;
	
	@FindBy(id = "ospConstReq")
	private WebElement ddConstructionRequired;
	
	@FindBy(id = "totalDistanceFt")
	private WebElement txtTotalDistanceFt;
	
	@FindBy(id = "nodeNumber")
	private WebElement txtNodeNumber;
	
	@FindBy(id = "siteType")
	private WebElement ddSiteType;	
	
	@FindBy(id = "serviceable")
	private WebElement ddServiceable;
	
	@FindBy(xpath = ".//Button[text()='Yes']")
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
	
	public boolean ConductCoaxSurvey(SiteLevelTaskInfo siteLevelTaskInfo) throws AWTException, InterruptedException{
		try{
			if(waitForElement(tabCoaxServeyInformation)){
				clickndRelease(tabCoaxServeyInformation);
				waitForElement(SurveyCompletionDate);
				SurveyCompletionDate.click();
				iClick(btnToday.get(0));
				new Select(ddOSPPermitRequired).selectByVisibleText("Yes");
				new Select(ddConstructionRequired).selectByVisibleText("Yes");
				waitForElement(tabCoaxSurveyResults);
				clickndRelease(tabCoaxSurveyResults);				
				txtNodeNumber.sendKeys(randomNumber(5));
				new Select(ddServiceable).selectByVisibleText("Yes");
				if(waitForElement(ddSiteType, 2))
				{
					new Select(ddSiteType).selectByVisibleText("Commercial Single Tenant");
				}
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Conduct Coax Survey' and contains(@onclick, 'COMPLETED')]")));
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
				iClick(btnComplete, null, "Complete ConductCoaxSurvey Task: Complete ConductCoaxSurvey Task page: CompleteButton");
				waitForElement(btnYes);
				btnYes.click();
				waitForElement(btnYes);
				btnYes.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ConductCoaxSurvey Task", " ConductCoaxSurvey Task Completed");
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
