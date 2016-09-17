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
	
	@FindBy(xpath = "//input[@id='edpCompletionString']/following-sibling::img")
	private WebElement edpCompletionDate ;
	
	@FindBy(xpath = "//input[@id='internalTestingCompleteString']/following-sibling::img")
	private WebElement internalTestingCompletionDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	@FindBy(xpath = "//span[text()='Circuit Testing Details']")
	private WebElement tabCircuitTestingDetails ;

	@FindBy(xpath = "//select[@id='statusAct']")
	private WebElement ddStatus ;
	
	@FindBy(xpath = "//div[text()='EPL']")
	private List<WebElement> elementEPL ;
	
	@FindBy(xpath = "//div[text()='EVPL']")
	private List<WebElement> elementEVPL ;
	
	private boolean mstatus;
	
	
	public boolean activateService(ServiceInfo serviceInfo,ServiceLevelTaskInfo serviceLevelTaskInfo){
		mstatus=true;
		try{
		switch(serviceInfo.serviceName){
		case "EDI" :
		case "ENS" :
		case "EDI-ToF" :
		case "ENS-PRI" :
			this.ActivateService_EDI(serviceLevelTaskInfo);
			break;
		case "EPL" :
			this.ActivateService_EPL(serviceLevelTaskInfo);
			break;
		case "EVPL" :
			this.ActivateService_EVPL(serviceLevelTaskInfo);
			break;
		default :
			System.out.println("Activate Service task not found");
		}
		
		}catch(Exception e){
			e.printStackTrace();
			mstatus = false;
		}
		return mstatus;
		
	}
	
	public boolean ActivateService_EDI(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		mstatus = true;
		try{
		scrollDown();
		if(waitForElement(edpCompletionDate)){
			edpCompletionDate.click();
			btnToday.get(0).sendKeys(Keys.ENTER); // Updated by Kesavan, today button normal click is not working as the button is hidden behind the status bar
			internalTestingCompletionDate.click();
			btnToday.get(1).sendKeys(Keys.ENTER);
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			if(waitForElement(ddStatus)){
				new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			}
		}
		this.ClickCompleteButton();
		waitForElement(browser.findElement(By.xpath("//*[text()='Activate Service' and contains(@onclick, 'COMPLETED')]")));
		}
		catch(Exception Ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ActivateService_EPL(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		mstatus = true;
		try{
			scrollDown();
			waitForElement(edpCompletionDate);
			edpCompletionDate.click();
			btnToday.get(0).sendKeys(Keys.ENTER);
			internalTestingCompletionDate.click();
			btnToday.get(1).sendKeys(Keys.ENTER);
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			btnSave.click();
			waitforPageLoadComplete();
			sleep(2000);
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(elementEPL.get(1));
			elementEPL.get(1).click();
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Activate Service' and contains(@onclick, 'COMPLETED')]")));
		}catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ActivateService_EVPL(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		mstatus = true;
		try{
			scrollDown();
			waitForElement(edpCompletionDate);
			edpCompletionDate.click();
			btnToday.get(0).sendKeys(Keys.ENTER);
			internalTestingCompletionDate.click();
			btnToday.get(1).sendKeys(Keys.ENTER);
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			btnSave.click();
			waitforPageLoadComplete();
			sleep(2000);
			waitForElement(tabCircuitTestingDetails);
			tabCircuitTestingDetails.click();
			waitForElementDisappear(elementLoading);
			waitForElement(elementEVPL.get(1));
			elementEVPL.get(1).click();
			waitForElement(ddStatus);
			new Select(ddStatus).selectByValue(serviceLevelTaskInfo.status);
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Activate Service' and contains(@onclick, 'COMPLETED')]")));
		}catch(Exception e){
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
