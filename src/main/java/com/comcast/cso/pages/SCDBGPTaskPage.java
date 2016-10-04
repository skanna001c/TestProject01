package com.comcast.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class SCDBGPTaskPage extends Page {

	public SCDBGPTaskPage(FrameworkContext context) {
		super(context);
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
	
	Logger log = Logger.getLogger(SCDBGPTaskPage.class);
	
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
	
	private boolean mstatus=true;
	
	public boolean setCriticalDates(){
		try{
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
