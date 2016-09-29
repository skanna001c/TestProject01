package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.ServiceInfo;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;

public class InstallCPETaskPage extends Page {

	public InstallCPETaskPage(FrameworkContext context) {
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

	@FindBy(xpath = "//input[@id='scheduledInstallDate']")
	private WebElement SchCPEInstallDate ;
	
	@FindBy(xpath = "//input[@id='actualCompleteDate']")
	private WebElement ActualCompletionDate ;
	
	@FindBy(xpath = "//*[@id='scheduledIADDate']")
	private WebElement SchIADDate ;
	
	@FindBy(xpath = "//*[@id='actualIADDate']")
	private WebElement ActualIADDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	private boolean mstatus = true;
	
	
	public boolean InstallCPE(ServiceInfo serviceIfo) {
		try {

			waitForElement(SchCPEInstallDate);
			SchCPEInstallDate.clear();
			SchCPEInstallDate.sendKeys(getCurrentDateTime());
			ActualCompletionDate.clear();
			ActualCompletionDate.sendKeys(getCurrentDateTime());

			if ((serviceIfo.serviceName.contains("PRI")) && (waitForElement(SchIADDate, 3))) {
				waitForElement(SchIADDate);
				SchIADDate.clear();
				SchIADDate.sendKeys(getCurrentDateTime());
				waitForElement(ActualIADDate);
				ActualIADDate.clear();
				ActualIADDate.sendKeys(getCurrentDateTime());
			}

			this.ClickCompleteButton();
		} catch (Exception ex) {
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
				iClick(btnComplete, btnBack, "Complete InstallCPE Task: Complete InstallCPE Task page: CompleteButton");
				waitforPageLoadComplete();
				waitForElement(browser.findElement(By.xpath("//*[text()='Install CPE' and contains(@onclick, 'COMPLETED')]")));
				report.reportDoneEvent("Complete InstallCPE Task", " InstallCPE Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}


}
