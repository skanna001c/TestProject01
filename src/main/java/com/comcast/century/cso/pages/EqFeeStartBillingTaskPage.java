package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class EqFeeStartBillingTaskPage extends Page {

	public EqFeeStartBillingTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//*[@id='actualBillingStartDate']/following-sibling::img")
	private WebElement actualBillingStartDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement btnToday ;
	
	public void EqFeeStartBilling() throws InterruptedException{
		if(waitForElement(actualBillingStartDate)){
			clickndRelease(actualBillingStartDate);
			clickndRelease(btnToday);
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
			waitForElementDisappear(elementLoading);
			report.reportDoneEvent("Complete EqFeeStartBilling Task", " EqFeeStartBilling Task Completed");
		}
		
	}

}
