package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;

public class CompleteSiteBuildTaskPage extends Page {
	//String windowHandle = browser.getWindowHandle();
	
	public CompleteSiteBuildTaskPage(FrameworkContext context) {
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
				iClick(btnComplete, frameRight, "Complete CompleteSiteBuild Task: Complete CompleteSiteBuild Task page: CompleteButton");
				//ClosePopUp();
				waitforPageLoadComplete();
				//closeAllOtherWindows(windowHandle);
				report.reportDoneEvent("Completed CompleteSiteBuild Task", " CompleteSiteBuild Task Completed");
				waitForElement(browser.findElement(By.xpath("//*[text()='Complete Site Build' and contains(@onclick, 'COMPLETED')]")));
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
	
	


