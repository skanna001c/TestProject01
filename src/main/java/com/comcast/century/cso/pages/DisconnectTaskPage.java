package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class DisconnectTaskPage extends Page {

	public DisconnectTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(DisconnectTaskPage.class);
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus = true;
	
	public boolean ClickCompleteButton(String taskName){
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack, "Complete "+taskName+" Task: Complete "+taskName+" Task Page: CompleteButton");
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.reportDoneEvent("Complete "+taskName+" Task", taskName+" Task Completed");
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
