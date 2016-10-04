package com.comcast.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class UpdateDesignTaskPage extends Page {

	public UpdateDesignTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(UpdateDesignTaskPage.class);
	
	@FindBy(xpath = "//span[text()='BOM Change Details']")
	private WebElement tabBOMChangeDetails;
	
	@FindBy(id = "designChanged")
	private WebElement ddDesignChanged;
	
	@FindBy(id = "bomChangeRequired")
	private WebElement ddBOMChanged;
	
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
	
	public boolean UpdateDesign(){
		try{
			waitForElement(ddDesignChanged);
			if(waitForElement(ddDesignChanged)){
				new Select(ddDesignChanged).selectByValue("No");
			}
			
			waitForElement(tabBOMChangeDetails);
			tabBOMChangeDetails.click();
			if(waitForElement(ddBOMChanged)){
				new Select(ddBOMChanged).selectByValue("No");
			}
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Update Design' and contains(@onclick, 'COMPLETED')]")));
		}	
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
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
				iClick(btnComplete, btnBack, "omplete UpdateDesign Task: omplete UpdateDesign Task Page: CompleteButton");
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.reportDoneEvent("Complete UpdateDesign Task", " UpdateDesign Task Completed");
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
