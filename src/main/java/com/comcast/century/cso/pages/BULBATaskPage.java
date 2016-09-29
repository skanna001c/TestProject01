package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class BULBATaskPage extends Page {

	public BULBATaskPage(FrameworkContext context) {
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
	
	@FindBy(id = "localBillerAccountNumber")
	private WebElement txtLBCustomerAccountNumber;
	
	@FindBy(id = "localBillerName")
	private WebElement ddlocalBillerName;
	
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
	
	private boolean mstatus;
	
	public boolean BULBA(ServiceLevelTaskInfo serviceLevelTaskInfo){
		mstatus = true;
		try{
			waitForElement(ddlocalBillerName);
			new Select(ddlocalBillerName).selectByVisibleText("CSG");
			waitForElement(txtLBCustomerAccountNumber);
			txtLBCustomerAccountNumber.clear();
			txtLBCustomerAccountNumber.sendKeys(serviceLevelTaskInfo.localBillerCustomerAccountNumber);
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Build Update Local Biller Account' and contains(@onclick, 'COMPLETED')]")));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}
	
	public boolean ClickBackButton(){
		mstatus = true;
		try{
			if(waitForElement(btnBack)){
				btnBack.click();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		mstatus = true;
		try{	
			if(waitForElement(btnComplete)){
				iClick(btnComplete, btnBack, "Complete BULBA Task: Complete BULBA Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete BULBA Task", " BULBA Task Completed");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;			
		}
		return mstatus;
	}

}
