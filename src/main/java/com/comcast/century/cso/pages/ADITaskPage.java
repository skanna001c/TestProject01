package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.SeleniumReport;

public class ADITaskPage extends Page {

	public ADITaskPage(WebDriver browser, SeleniumReport report) {
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

	@FindBy(xpath = "//div[text()='Circuit Mapping']")
	private WebElement tabCircuitMapping;
	
	@FindBy(xpath = "//div[text()='EDI']")
	private WebElement elementEDI ;
	
	@FindBy(xpath = "//*[text()='-']")
	private List<WebElement> elementDash ;

	@FindBy(xpath = "//input[@id='serviceId']")
	private WebElement txtServiceID ;

	@FindBy(xpath = "//input[@id='evccID']")
	private WebElement txtEVCid ;

	@FindBy(xpath = "//input[@id='projectName']")
	private WebElement txtProjectName ;
	
	@FindBy(xpath = "//b[contains(normalize-space(text()),normalize-space(concat(\"Retrieve Circuit \",\"ID's\")))]")
	private WebElement linkRetrieveCircuitID ;
	
	private boolean mstatus;
	
	public boolean ADI(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(tabCircuitMapping)){
				tabCircuitMapping.click();
				waitForElementDisappear(elementLoading);
				Thread.sleep(2*1000);
				scrollToElementandclick(elementDash.get(0));
				waitForElement(txtServiceID);
				txtServiceID.sendKeys(randomNumber(6));
				txtEVCid.sendKeys(serviceLevelTaskInfo.EVC1);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);
				/* Sometimes after clicking the above link EVC id is changed to 500.
				 As a workaround we have setting the value again and before  clicking the complete button.*/				
				waitForElement(txtEVCid);
				txtEVCid.clear();
				txtEVCid.sendKeys(serviceLevelTaskInfo.EVC1);
				this.ClickCompleteButton();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean assignDesignInformationPRI(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		mstatus = true;
		try{
			if(waitForElement(tabCircuitMapping)){
				tabCircuitMapping.click();
				waitForElementDisappear(elementLoading);
				Thread.sleep(2*1000);
				scrollToElementandclick(elementDash.get(1));
				waitForElement(txtServiceID);
				txtServiceID.sendKeys(randomNumber(6));
				txtEVCid.sendKeys(serviceLevelTaskInfo.EVC2);
				txtProjectName.clear();
				txtProjectName.sendKeys(serviceLevelTaskInfo.projectName);
				linkRetrieveCircuitID.click();
				Thread.sleep(5*1000);
				this.ClickCompleteButton();
			}
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
				iClick(btnComplete, null, "Assign Design Information Task Complete: Assign Design Information Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ADI Task", " ADI Task Completed");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			mstatus = false;
		}
		return mstatus;
		
	}

}
