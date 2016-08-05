package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class ShipCPETaskPage extends Page {

	public ShipCPETaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//input[@id='packingSlipNumber']")
	private WebElement txtPackingSlipNumber ;
	
	@FindBy(xpath = "//input[@id='cpeShipDate']/following-sibling::img")
	private WebElement CPEShipDate  ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement  btnToday;
	
	@FindBy(xpath = "//input[@id='SiteAddressCombo']/following-sibling::img")
	private WebElement  ddSiteAddress;
	
	@FindBy(css = "div[class*='lovcombo']")
	private WebElement  ddvalueChkSiteAddress;
	
	
	
	public void ShipCPE(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		if(waitForElement(txtPackingSlipNumber)){
			txtPackingSlipNumber.sendKeys(RandomNumber());
			CPEShipDate.click();
			btnToday.click();
			ddSiteAddress.click();
			ddvalueChkSiteAddress.click();
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
			report.reportDoneEvent("Complete ShipCPE Task", " ShipCPE Task Completed");
		}
		
	}

}
