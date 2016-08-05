package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class SCDBGPTaskPage extends Page {

	public SCDBGPTaskPage(WebDriver browser, SeleniumReport report) {
		super(browser, report);
		// TODO Auto-generated constructor stub
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
	
	@FindBy(xpath = "//input[@id='scheduledInstallDate']//following-sibling::img")
	private WebElement cpeInstallDate ;
	
	@FindBy(xpath = "//input[@id='focDate']//following-sibling::img")
	private WebElement focDate ;
	
	@FindBy(xpath = "//input[@id='custNotificationDate']//following-sibling::img")
	private WebElement CustomerNotificationDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	public void setCriticalDates(){
		if(waitForElement(cpeInstallDate)){
			cpeInstallDate.click();
			btnToday.get(0).click();
			CustomerNotificationDate.click();
			btnToday.get(1).click();
			focDate.click();
			btnToday.get(2).click();
			this.ClickCompleteButton();
		}
	}
	
	
	
	public void ClickCompleteButton(){
		if(waitForElement(btnComplete)){
			btnComplete.click();
			waitforPageLoadComplete();
			report.reportDoneEvent("Complete SetCriticalDates Task", " SetCriticalDates Task Completed");
		}
		
	}

}
