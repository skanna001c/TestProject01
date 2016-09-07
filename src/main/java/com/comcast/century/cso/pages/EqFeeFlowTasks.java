package com.comcast.century.cso.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class EqFeeFlowTasks extends Page {
          
	public EqFeeFlowTasks(WebDriver browser, SeleniumReport report){
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
	
	@FindBy(xpath = "//img[@title='Back']")
	private WebElement btnBack;
	
	@FindBy(xpath = "//div[@class[contains(.,'refresh')]]")
	private WebElement btnRefresh;
	
	@FindBy(xpath = "//*[text()='Start Billing']")
	private WebElement taskEqFeeStartBilling;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	private boolean mstatus= true;
	
	public boolean EqFeeStartBilling() throws InterruptedException{
		try{
			if(waitForElement(taskEqFeeStartBilling)){
				if(checkifStatusChanged(taskEqFeeStartBilling,btnRefresh,"COMPLETED") || checkifStatusChanged(taskEqFeeStartBilling,btnRefresh,"INPROGRESS")){
					waitForElement(taskEqFeeStartBilling);
					Thread.sleep(5*1000);
					jsClick(taskEqFeeStartBilling);
					report.reportDoneEvent("Click EqFeeStartBilling Task", " EqFeeStartBilling Task Clicked");
				}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickBackButton() throws InterruptedException{
		try{
			if(waitForElement(btnBack)){
				clickndRelease(btnBack);
				//btnBack.click();
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Validate", "EqFee Flow Task Completed", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}

}
