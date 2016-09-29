package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

public class ObtainSiteAgreementTaskPage extends Page {

	public ObtainSiteAgreementTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//*[text()='Site Agreement Information']")
	private WebElement tabSiteAgreementInfo;
	
	@FindBy(xpath = "//input[@id='sagiSubmittedDate']/following-sibling::*")
	private WebElement SubmittedDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	@FindBy(xpath = "//input[@id='sagiReceivedDate']/following-sibling::*")
	private WebElement ActualReceivedDate ;
	
	@FindBy(xpath = "//input[@id='sagiScheduledReceivedDate']/following-sibling::*")
	private WebElement estimatedReceivedDate ;
	
	@FindBy(xpath = "//input[@id='sagiAgreementName']")
	private WebElement txtAgreementName ;
	
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
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus=true;
	
	public boolean ObtainSiteAgreement(SiteLevelTaskInfo siteLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(tabSiteAgreementInfo)){
				tabSiteAgreementInfo.click();
				waitForElementDisappear(elementLoading);
				scrollDown();
				waitForElement(txtAgreementName);
				txtAgreementName.sendKeys(siteLevelTaskInfo.agreementName+randomNumber(5));
				SubmittedDate.click();
				btnToday.get(0).click();
				clickndRelease(estimatedReceivedDate);
				btnToday.get(1).click();
				clickndRelease(ActualReceivedDate);
				btnToday.get(2).click();	
				this.ClickCompleteButton();
				waitUntilElementPresent(By.xpath("//*[text()='Obtain Site Agreement(s)' and contains(@onclick, 'COMPLETED')]"),60);
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
				iClick(btnComplete, null, "Complete ObtainSiteAgreement Task: Complete ObtainSiteAgreement Task Page: CompleteButton");
				waitforPageLoadComplete();
				waitForElement(btnYes);
				btnYes.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ObtainSiteAgreement Task", " ObtainSiteAgreement Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}

}
