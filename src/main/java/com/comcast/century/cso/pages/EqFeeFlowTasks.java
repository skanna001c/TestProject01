package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class EqFeeFlowTasks extends Page {
          
	public EqFeeFlowTasks(FrameworkContext context){
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
	
	Logger log = Logger.getLogger(EqFeeFlowTasks.class);
	
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
	  return clicktask(taskEqFeeStartBilling, "EqFee Star tBilling");
	  
		/*try{
			if(waitForElement(taskEqFeeStartBilling)){
				//if(checkifStatusChanged(taskEqFeeStartBilling,btnRefresh,"COMPLETED") || checkifStatusChanged(taskEqFeeStartBilling,btnRefresh,"INPROGRESS")){
					waitForElement(taskEqFeeStartBilling);
					Thread.sleep(5*1000);
					jsClick(taskEqFeeStartBilling);
					report.reportDoneEvent("Click EqFeeStartBilling Task", " EqFeeStartBilling Task Clicked");
				//}
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;*/
	}
	
	public boolean ClickBackButton() throws InterruptedException{
		try{
			if(waitForElement(btnBack)){
				clickndRelease(btnBack);				
				waitforPageLoadComplete();
				waitForElementDisappear(elementLoading);
				report.updateTestLog("Validate", "EqFee Flow Task Completed", Status.SCREENSHOT);
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

	
	public boolean clicktask(WebElement task,String taskName) {
		mstatus=true;
		try{
			if(waitForElement(task)){
				//if(checkifStatusChanged(taskStartBilling,btnRefresh,"INPROGRESS") || checkifStatusChanged(taskStartBilling,btnRefresh,"COMPLETED")){
				if(checkifStatusChanged(task,btnRefresh,"INPROGRESS")){
					jsClick(task);
					report.reportDoneEvent("Inside "+taskName, "");
					waitforPageLoadComplete();
				}
				else{ mstatus=false;}
				
			}else  mstatus=false;
		}
		catch(Exception ex)
		{  
			mstatus = false;
			log.info(ex.getMessage());
		}
		return mstatus;
	}
}
