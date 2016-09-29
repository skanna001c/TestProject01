package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;

public class DaysOfConfigsTaskPage extends Page {

	public DaysOfConfigsTaskPage(FrameworkContext context) {
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
	
	private boolean mstatus = true;
	
	public boolean ClickCompleteButton(){
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack, "Complete DayofConfigs Task: Complete DayofConfigs Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete DayofConfigs Task", " DayofConfigs Task Completed");
				waitForElement(browser.findElement(By.xpath("//*[text()='Day of Configs' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}

}
