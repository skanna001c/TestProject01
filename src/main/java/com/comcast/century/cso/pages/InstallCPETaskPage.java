package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class InstallCPETaskPage extends Page {

	public InstallCPETaskPage(WebDriver browser, SeleniumReport report) {
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
	
	public void InstallCPE() throws InterruptedException{
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
	
	public void installCPEPRI() throws InterruptedException{
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
	
	
	
	public void ClickBackButton(){
		if(waitForElement(btnBack)){
			btnBack.click();
		}
	}
	
	public void ClickSaveButton(){
		if(waitForElement(btnSave)){
			btnSave.click();
		}
	}
	
	public void ClickCompleteButton(){
		if(waitForElement(btnComplete)){
			btnComplete.click();
			waitforPageLoadComplete();
			report.reportDoneEvent("Complete InstallCPE Task", " InstallCPE Task Completed");
		}
		
	}


}
