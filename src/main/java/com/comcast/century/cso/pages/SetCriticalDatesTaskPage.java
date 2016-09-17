package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

public class SetCriticalDatesTaskPage extends Page {

	public SetCriticalDatesTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//select[@id='hotCut']")
	private WebElement ddHotCut ;
	
	@FindBy(xpath = "//input[@id='focDate']//following-sibling::img")
	private WebElement focDate ;
	
	@FindBy(xpath = "//input[@id='custNotificationDate']//following-sibling::img")
	private WebElement CustomerNotificationDate ;
	
	@FindBy(xpath = "//*[@id='scheduledInstallDate']//following-sibling::img")
	private WebElement ScheduleCPEInstallDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	private boolean mstatus=true;
	
	public boolean SetCriticalDates(){
		try{
			if(waitForElement(CustomerNotificationDate)){
				CustomerNotificationDate.click();
				btnToday.get(0).click();
				new Select(ddHotCut).selectByValue("Yes");
				scrollDown();
				focDate.click();
				btnToday.get(1).click();
				ScheduleCPEInstallDate.click();
				btnToday.get(2).click();
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Set Critical Dates' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean setCriticalDatesPRI(){
		try{
			waitForElement(ddHotCut);
			new Select(ddHotCut).selectByValue("Yes");
			focDate.click();
			btnToday.get(1).click();
			this.ClickCompleteButton();
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
				iClick(btnComplete, btnBack, "Complete SetCriticalDates Task: Complete SetCriticalDates Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete SetCriticalDates Task", " SetCriticalDates Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}

}
