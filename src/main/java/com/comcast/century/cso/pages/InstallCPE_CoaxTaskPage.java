package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class InstallCPE_CoaxTaskPage extends Page {

	public InstallCPE_CoaxTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//input[@value='Complete']")
	private WebElement btnComplete;
	
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@value='Reset']")
	private WebElement btnReset;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;

	@FindBy(xpath = "//input[@id='scheduledInstallDate']//following-sibling::img")
	private WebElement SchCPEInstallDate ;
	
	@FindBy(xpath = "//input[@id='actualCompleteDate']//following-sibling::img")
	private WebElement ActualCompletionDate ;
	
	@FindBy(xpath = "//*[@id='scheduledIADDate']//following-sibling::img")
	private WebElement SchIADDate ;
	
	@FindBy(xpath = "//*[@id='actualIADDate']//following-sibling::img")
	private WebElement ActualIADDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	private boolean mstatus = true;
	
	public boolean InstallCPECoax() throws InterruptedException{
		try{
			if(waitForElement(SchCPEInstallDate)){
				clickndRelease(SchCPEInstallDate);
				//SchCPEInstallDate.click();
				btnToday.get(0).click();
				clickndRelease(ActualCompletionDate);
				//ActualCompletionDate.click();
				btnToday.get(1).click();
				this.ClickCompleteButton();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean installCPEPRI() throws InterruptedException{
		try{
			if(waitForElement(SchCPEInstallDate)){
				clickndRelease(SchCPEInstallDate);
				//SchCPEInstallDate.click();
				btnToday.get(0).click();
				clickndRelease(ActualCompletionDate);
				//ActualCompletionDate.click();
				btnToday.get(1).click();
				clickndRelease(SchIADDate);
				btnToday.get(2).click();
				clickndRelease(ActualIADDate);
				btnToday.get(3).click();
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
				iClick(btnComplete, null, "Complete InstallCPE_Coax Task: Complete InstallCPE_Coax Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete InstallCPE_Coax Task", " InstallCPE Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}


}
