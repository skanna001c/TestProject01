package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.utils.SeleniumReport;

public class ObtainFiberPlantPermitsTaskPage extends Page {

	public ObtainFiberPlantPermitsTaskPage(WebDriver browser, SeleniumReport report) {
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
	
	@FindBy(xpath = "//select[@id='permitType']")
	private WebElement ddPermitType;
	
	@FindBy(xpath = "//input[@id='permitAppliedDate']/following-sibling::img")
	private WebElement PermitAppliedDate;
	
	@FindBy(xpath = "//input[@id='permitApprovedDate']/following-sibling::img")
	private WebElement PermitActualApprovedDate;

	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
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
	
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnYes ;
	
	private boolean mstatus=true;
	
	public boolean ObtainFiberPlantPermits(){
		try{
			waitForElementDisappear(elementLoading);
			scrollDown();
			if(waitForElement(ddPermitType)){
				new Select(ddPermitType).selectByVisibleText("Zoning");
				PermitAppliedDate.click();
				btnToday.get(0).click();
				PermitActualApprovedDate.click();
				btnToday.get(1).click();
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Obtain Fiber Plant Permits' and contains(@onclick, 'COMPLETED')]")));
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
				iClick(btnComplete, null, "Complete ObtainFiberPlantPermits Task: Complete ObtainFiberPlantPermits Task: CompleteButton");
				waitforPageLoadComplete();
				waitForElement(btnYes);
				btnYes.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ObtainFiberPlantPermits Task", " ObtainFiberPlantPermits Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}
	

}
