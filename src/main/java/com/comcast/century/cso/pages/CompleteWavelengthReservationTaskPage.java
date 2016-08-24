package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class CompleteWavelengthReservationTaskPage extends Page {

	public CompleteWavelengthReservationTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;
	
	@FindBy(xpath = "//input[@id='wdmCombo-inputEl']/../following-sibling::*/child::*")
	private WebElement ddWDMType;
	
	@FindBy(xpath = "//li[text()='Grey']")
	private WebElement ddValueWDMType;
	
	@FindBy(xpath = "//input[@id='lengthCombo-inputEl']/../following-sibling::*/child::*")
	private WebElement ddWavelength;
	
	@FindBy(xpath = "//li[text()='850 nm']")
	private WebElement ddValueWavelength;
	
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
	
	public boolean CompleteWavelengthReservation(SiteLevelTaskInfo siteLevelTaskInfo) throws InterruptedException{
		try{
			if(waitForElement(ddWDMType)){
				ddWDMType.click();
				waitForElementDisappear(elementLoading);
				ddValueWDMType.click();
				waitForElement(ddWavelength);
				ddWavelength.click();
				waitForElementDisappear(elementLoading);
				ddValueWavelength.click();
				this.ClickCompleteButton();
			}
		}
		catch(Exception ex)
		{
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
				btnComplete.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete CompleteWavelengthReservation Task", " CompleteWavelengthReservation Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}
	
	

}
