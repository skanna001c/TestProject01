package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class CAETaskPage extends Page {

	public CAETaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//span[text()='On-net Service Component(s) Information']")
	private WebElement tabOnnetServiceCI;
	
	@FindBy(xpath = "//div[text()='EDI']")
	private WebElement elementEDI ;
	
	@FindBy(xpath = "//div[text()='EPL']")
	private WebElement elementEPL ;
	
	@FindBy(xpath = "//div[text()='Trunk-PRI']")
	private WebElement elementPRI ;
	
	@FindBy(xpath = "//input[@id='uniId']")
	private WebElement txtUNIid ;
	
	@FindBy(xpath = "//input[@id='siteCili']")
	private WebElement txtSiteCili ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	@FindBy(xpath = "//input[@id='projectName']")
	private WebElement txtProjectName ;
	
	
	public void CAE(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		if(waitForElement(tabOnnetServiceCI)){
			tabOnnetServiceCI.click();
			Thread.sleep(2*1000);
			scrollToElementandclick(elementEDI);
			waitForElement(txtUNIid);
			txtUNIid.sendKeys(serviceLevelTaskInfo.UNI1);
			txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
			linkRetrieveCircuitID.click();
			Thread.sleep(5*1000);
			txtProjectName.clear();
			txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
			this.ClickCompleteButton();
			
		}
	}
	
	public void CAE_EPL(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		if(waitForElement(tabOnnetServiceCI)){
			tabOnnetServiceCI.click();
			Thread.sleep(2*1000);
			scrollToElementandclick(elementEPL);
			waitForElement(txtUNIid);
			txtUNIid.sendKeys(serviceLevelTaskInfo.UNI1);
			txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
			/*txtProjectName.clear();
			txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);*/
			linkRetrieveCircuitID.click();
			Thread.sleep(5*1000);
			btnSave.click();
			waitforPageLoadComplete();
			Thread.sleep(2*1000);
			waitForElement(tabOnnetServiceCI);
			tabOnnetServiceCI.click();
			Thread.sleep(2*1000);
			scrollToElementandclick(elementEPL);
			waitForElement(txtUNIid);
			txtUNIid.sendKeys(serviceLevelTaskInfo.UNI2);
			txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
			/*txtProjectName.clear();
			txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);*/
			linkRetrieveCircuitID.click();
			Thread.sleep(5*1000);
			this.ClickCompleteButton();
			
		}
	}
	
	public void cAE_PRI(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		if(waitForElement(tabOnnetServiceCI)){
			tabOnnetServiceCI.click();
			Thread.sleep(2*1000);
			scrollToElementandclick(elementPRI);
			waitForElement(txtUNIid);
			txtUNIid.sendKeys(serviceLevelTaskInfo.UNI2);
			txtSiteCili.sendKeys(serviceLevelTaskInfo.siteCili);
			linkRetrieveCircuitID.click();
			Thread.sleep(5*1000);
			txtProjectName.clear();
			txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
			this.ClickCompleteButton();
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
			report.reportDoneEvent("Complete CAE Task", " CAE Task Completed");
		}
		
	}

}
