package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class StartBillingTaskPage extends Page {

	public StartBillingTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(StartBillingTaskPage.class);
	
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
	
	@FindBy(id = "actualBillingStartDate")
	private WebElement actualBillingStartDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement btnToday ;
	
	@FindBy(css = "img#noteImg")
	private WebElement imgNotes ;
	
	@FindBy(css = "iframe#notesframe")
	private WebElement frameNotes ;
	
	@FindBy(xpath = "//span[.='NOTES']/preceding-sibling::div[3]")
	private WebElement closeNotes ;
	
	private boolean mstatus = true;
	
	public boolean StartBilling() throws InterruptedException{
		try{
			if(waitForElement(actualBillingStartDate)){
				if(waitForElement(btnComplete, 5))
				{
					actualBillingStartDate.click();
					actualBillingStartDate.clear();
					actualBillingStartDate.sendKeys(getCurrentDate());
					this.ClickCompleteButton();
					waitForElement(browser.findElement(By.xpath("//*[text()='Start Billing' and contains(@onclick, 'COMPLETED')]")));
				}
				else
				{
					this.verifyNotes();
				}
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean verifyNotes(){
		try{
			waitForElement(imgNotes);
			imgNotes.click();
			waitforPageLoadComplete();
			sleep(10000);
			report.updateTestLog("Verify Notes", "Notes generate for SV Integration", Status.SCREENSHOT);
			closeNotes.click();
			this.ClickBackButton();
		}catch(Exception e){
			log.info(e.getMessage());
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
				iClick(btnComplete, btnBack, "Complete StartBilling Task: Complete StartBilling Task Page: CompleteButton");
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.reportDoneEvent("Complete StartBilling Task", " StartBilling Task Completed");
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
