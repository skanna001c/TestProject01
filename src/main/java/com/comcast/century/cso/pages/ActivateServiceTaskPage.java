package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceInfo;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

public class ActivateServiceTaskPage extends Page {

	public ActivateServiceTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//input[@id='edpCompletionString']")
	private WebElement edpCompletionDate ;
	
	@FindBy(xpath = "//input[@id='internalTestingCompleteString']")
	private WebElement internalTestingCompletionDate ;
	
	@FindBy(xpath = "//span[text()='Circuit Testing Details']")
	private WebElement tabCircuitTestingDetails ;

	@FindBy(xpath = "//select[@id='statusAct']")
	private WebElement ddStatus ;
	
	@FindBy(xpath = "//div[text()='EPL' or text()='EVPL']")
	private List<WebElement> elementEPLorEVPL ;
	
	
	private boolean mstatus;
	
	
	public boolean activateService(ServiceInfo serviceInfo,ServiceLevelTaskInfo serviceLevelTaskInfo){
		mstatus=true;
		try{
			scrollDown();
			waitForElement(edpCompletionDate);
			edpCompletionDate.click();
			edpCompletionDate.clear();
			edpCompletionDate.sendKeys(getCurrentDate());
			waitForElement(internalTestingCompletionDate);
			internalTestingCompletionDate.click();
			internalTestingCompletionDate.clear();
			internalTestingCompletionDate.sendKeys(getCurrentDate());
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			if(serviceInfo.serviceName.matches("EPL|EVPL") && elementEPLorEVPL.size() > 1)
			{
				btnSave.click();
				waitforPageLoadComplete();
				sleep(2000);
				waitForElement(tabCircuitTestingDetails);
				tabCircuitTestingDetails.click();
				waitForElementDisappear(elementLoading);
				waitForElement(elementEPLorEVPL.get(1));
				elementEPLorEVPL.get(1).click();
				waitForElement(ddStatus);
				new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			} 
			  this.ClickCompleteButton();
			  waitForElement(browser.findElement(By.xpath("//*[text()='Activate Service' and contains(@onclick, 'COMPLETED')]")));
		}catch(Exception e){
			e.printStackTrace();
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
				iClick(btnComplete, btnBack, "Complete Activate Service: Activate service task page: CompleteButton");
				btnComplete.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ActivateService Task", " ActivateService Task Completed");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}

}
