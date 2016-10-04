package com.comcast.century.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





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
	
	Logger log = Logger.getLogger(SetCriticalDatesTaskPage.class);
	
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
	
	@FindBy(id = "hotCut")
	private WebElement ddHotCut ;
	
	@FindBy(id = "focDate")
	private WebElement focDate ;
	
	@FindBy(id = "custNotificationDate")
	private WebElement CustomerNotificationDate ;
	
	@FindBy(id = "scheduledInstallDate")
	private WebElement ScheduleCPEInstallDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	private boolean mstatus=true;
	
	public boolean SetCriticalDates() {
		try {
			scrollDown();
			if (waitForElement(CustomerNotificationDate)) {
				CustomerNotificationDate.click();
				CustomerNotificationDate.clear();
				CustomerNotificationDate.sendKeys(getCurrentDate());
				if(waitForElement(ddHotCut, 2)){
				new Select(ddHotCut).selectByValue("Yes");
				}
				focDate.click();
				focDate.clear();
				focDate.sendKeys(getCurrentDate());
				ScheduleCPEInstallDate.click();
				ScheduleCPEInstallDate.clear();
				ScheduleCPEInstallDate.sendKeys(getCurrentDateTime());
				this.ClickCompleteButton();
				waitForElement(browser
						.findElement(By.xpath("//*[text()='Set Critical Dates' and contains(@onclick, 'COMPLETED')]")));
			}
		} catch (Exception ex) {
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
		
	}

}
