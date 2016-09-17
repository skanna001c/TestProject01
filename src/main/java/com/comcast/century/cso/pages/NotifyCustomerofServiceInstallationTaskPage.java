package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

public class NotifyCustomerofServiceInstallationTaskPage extends Page {

	public NotifyCustomerofServiceInstallationTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//input[@id='customerNotificationDate1']/following-sibling::img")
	private WebElement customerNotificationDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement btnToday ;
	
	private boolean mstatus=true;
	
	public boolean NotifyCustomerofServiceInstallation(){
		try{
			if(waitForElement(customerNotificationDate)){
				customerNotificationDate.click();
				btnToday.click();
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Notify Customer of Service Installation' and contains(@onclick, 'COMPLETED')]")));
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
				iClick(btnComplete, btnBack, "Complete NotifyCustomerofServiceInstallation Task: Complete NotifyCustomerofServiceInstallation Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete NotifyCustomerofServiceInstallation Task", " NotifyCustomerofServiceInstallation Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}

}
