package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class AssignDesignBGPTaskPage extends Page {

	public AssignDesignBGPTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(AssignDesignBGPTaskPage.class);
	
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
		mstatus = true;
		try{
			if(waitForElement(btnBack)){
			btnBack.click();
		    }
		}
		catch(Exception e){
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
		
	}
	
	public boolean ClickSaveButton(){
		mstatus = true;
		try{
			if(waitForElement(btnSave)){
				btnSave.click();
			}
		}		
		catch(Exception e){
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		mstatus = true;
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack, "Complete AssignDesignBGP Task: Complete AssignDesignBGP task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete AssignDesignBGP Task", " AssignDesignBGP Task Completed");
			}
		}
		
		catch(Exception e){
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
		
	}

}
