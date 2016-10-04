package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.ServiceLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class ShipCPETaskPage extends Page {

	public ShipCPETaskPage(FrameworkContext context) {
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
	
	Logger log = Logger.getLogger(ShipCPETaskPage.class);
	
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
	
	@FindBy(id = "packingSlipNumber")
	private WebElement txtPackingSlipNumber ;
	
	@FindBy(xpath = "//input[@id='cpeShipDate']/following-sibling::img")
	private WebElement CPEShipDate  ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private WebElement  btnToday;
	
	@FindBy(xpath = "//input[@id='SiteAddressCombo']/following-sibling::img")
	private WebElement  ddSiteAddress;
	
	@FindBy(css = "div[class*='lovcombo']")
	private WebElement  ddvalueChkSiteAddress;
	
	private boolean mstatus=true;
	
	public boolean ShipCPE(ServiceLevelTaskInfo serviceLevelTaskInfo) throws InterruptedException{
		try{
			scrollDown();
			if(waitForElement(txtPackingSlipNumber, 5)){
				txtPackingSlipNumber.sendKeys(randomNumber(5));
				CPEShipDate.click();
				btnToday.click();
				ddSiteAddress.click();
				ddvalueChkSiteAddress.click();
			}
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Ship CPE' and contains(@onclick, 'COMPLETED')]")));
			
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
				iClick(btnComplete, btnBack, "Complete ShipCPE Task: Complete ShipCPE Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ShipCPE Task", " ShipCPE Task Completed");
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
