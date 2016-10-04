package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class CompleteCoaxBuildTaskPage extends Page {
	//String windowHandle = browser.getWindowHandle();
	
	public CompleteCoaxBuildTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(CompleteCoaxBuildTaskPage.class);
	
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
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
}
	
	public boolean ClickCompleteButton(){		
		try{
			if(waitForElement(btnComplete)){
				windowHandle=browser.getWindowHandle();			
				iClick(btnComplete, null, "Complete ComleteCoaxBuild Task: Complete CompleteCoaxBuild Taskpage: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Completed CompleteCoaxBuild Task", " CompleteCoaxBuild Task Completed");
				waitForElement(browser.findElement(By.xpath("//*[text()='Complete Coax Build' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
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
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	


}
	
	


