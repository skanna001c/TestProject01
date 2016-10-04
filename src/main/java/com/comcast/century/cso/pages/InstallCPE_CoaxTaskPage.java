package com.comcast.century.cso.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;





public class InstallCPE_CoaxTaskPage extends Page {

	public InstallCPE_CoaxTaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(InstallCPE_CoaxTaskPage.class);
	
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
				btnToday.get(0).click();
				clickndRelease(ActualCompletionDate);
				btnToday.get(1).click();
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Install CPE (Coax)' and contains(@onclick, 'COMPLETED')]")));
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean installCPEPRI() throws InterruptedException{
		try{
			if(waitForElement(SchCPEInstallDate)){
				clickndRelease(SchCPEInstallDate);				
				btnToday.get(0).click();
				clickndRelease(ActualCompletionDate);
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
				iClick(btnComplete, btnBack, "Complete InstallCPE_Coax Task: Complete InstallCPE_Coax Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete InstallCPE_Coax Task", " InstallCPE Task Completed");
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
