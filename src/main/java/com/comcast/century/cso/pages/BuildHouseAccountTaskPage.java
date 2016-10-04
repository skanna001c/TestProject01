package com.comcast.century.cso.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class BuildHouseAccountTaskPage extends Page {

	public BuildHouseAccountTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//*[@id='RightFrame' and contains(@src,'MyOrder.exc')]")
	private WebElement frameRight;

	@FindBy(id = "comboHomelocalBillerNameMarket")
	private WebElement ddTextLocalBillerName;
	
	@FindBy(xpath = "//div[text()='DDP']")
	private WebElement ddValueLocalBillerName;
	
	@FindBy(id = "localBillerHouseAccountNumber")
	private WebElement txtLBHouseAccountNumber;
	
	@FindBy(xpath = "//input[@id='comboHomelocalBillerNameMarket']/following-sibling::img")
	private WebElement txtLocalBillerName;
	
	@FindBy(xpath = "//div[text()='CSG']")
	private WebElement ddValueCSG;
	
	@FindBy(xpath = "//div[text()='DDP']")
	private WebElement ddValueDDP;
	
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
	
	private boolean mstatus = true;
	
	Logger log = Logger.getLogger(BuildHouseAccountTaskPage.class);
	
	public boolean BuildHouseAccount(SiteLevelTaskInfo siteLevelTaskInfo){
		mstatus = true;
		try{
			waitforPageLoadComplete();
			waitForElement(txtLocalBillerName);
			txtLocalBillerName.click();
			waitForElement(ddValueCSG);
			ddValueCSG.click();
			waitForElement(txtLBHouseAccountNumber);
			txtLBHouseAccountNumber.sendKeys(siteLevelTaskInfo.localBillerHouseAccountNumber);
			this.ClickCompleteButton();
			waitForElement(browser.findElement(By.xpath("//*[text()='Build House Account' and contains(@onclick, 'COMPLETED')]")));
		}catch(Exception e){
			log.info(e.getMessage());
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
			log.info(e.getMessage());
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
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ClickCompleteButton(){
		mstatus = true;
		try{
			if(waitForElement(btnComplete)){
				iClick(btnComplete, null, "Complete BuildHouseAccount Task: Complete BuildHouseAccount Task page: CompleteButton");
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete BuildHouseAccount Task", " BuildHouseAccount Task Completed");
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
}
