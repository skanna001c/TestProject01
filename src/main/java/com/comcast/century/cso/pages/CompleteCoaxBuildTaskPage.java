package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class CompleteCoaxBuildTaskPage extends Page {
	//String windowHandle = browser.getWindowHandle();
	
	public CompleteCoaxBuildTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	private static String windowHandle;   
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
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
	
	public boolean ClickBackButton(){
		try{
			if(waitForElement(btnBack)){
				btnBack.click();
			}		
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickSaveButton(){
		try{
			if(waitForElement(btnSave)){
				btnSave.click();
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
				windowHandle=browser.getWindowHandle();
			//	System.out.println("main windowHandle "+windowHandle);
				btnComplete.click();
				//ClosePopUp();
				waitforPageLoadComplete();
				//closeAllOtherWindows(windowHandle);
				report.reportDoneEvent("Completed CompleteCoaxBuild Task", " CompleteCoaxBuild Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean closePopup() throws InterruptedException
	{	
		try{
			ClosePopUpndSwitchtoFrame(windowHandle,frameRight);
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	


}
	
	


