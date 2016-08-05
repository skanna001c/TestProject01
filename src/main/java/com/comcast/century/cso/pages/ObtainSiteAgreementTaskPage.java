package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class ObtainSiteAgreementTaskPage extends Page {

	public ObtainSiteAgreementTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//*[text()='Site Agreement Information']")
	private WebElement tabSiteAgreementInfo;
	
	@FindBy(xpath = "//input[@id='sagiSubmittedDate']/following-sibling::*")
	private WebElement SubmittedDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	@FindBy(xpath = "//input[@id='sagiReceivedDate']/following-sibling::*")
	private WebElement ActualReceivedDate ;
	
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
	
	public void ObtainSiteAgreement(SiteLevelTaskInfo siteLevelTaskInfo) throws InterruptedException{
		if(waitForElement(tabSiteAgreementInfo)){
			tabSiteAgreementInfo.click();
			waitForElementDisappear(elementLoading);
			scrollDown();
			waitForElement(txtAgreementName);
			txtAgreementName.sendKeys(siteLevelTaskInfo.agreementName+RandomNumber());
			SubmittedDate.click();
			btnToday.get(0).click();
			clickndRelease(ActualReceivedDate);
			//ActualReceivedDate.click();
			btnToday.get(1).click();	
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
			report.reportDoneEvent("Complete ObtainSiteAgreement Task", " ObtainSiteAgreement Task Completed");
		}
	}

}
