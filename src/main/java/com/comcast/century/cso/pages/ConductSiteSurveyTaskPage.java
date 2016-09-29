package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.SiteLevelTaskInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




public class ConductSiteSurveyTaskPage extends Page {

	public ConductSiteSurveyTaskPage(FrameworkContext context) {
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
	
	@FindBy(xpath = "//input[@id='scheduledSurveyString']/following-sibling::*")
	private WebElement ScheduledSurveyDate ;
	
	@FindBy(xpath = "//input[@id='surveyCompletionString']/following-sibling::*")
	private WebElement SurveyCompletionDate ;
	
	@FindBy(xpath = "//button[text()='Today']")
	private List<WebElement> btnToday ;
	
	@FindBy(xpath = "//select[@id='permitRequired']")
	private WebElement ddSitePermitRequired ;
	
	@FindBy(xpath = "//select[@id='siteType']")
	private WebElement ddCPElocationType ;
	
	@FindBy(xpath = "//select[@id='powerStatus']")
	private WebElement ddPowerStatus ;
	
	@FindBy(xpath = "//select[@id='equipmentLocationDescription']")
	private WebElement ddEquipmentLocationDescription ;
	
	//button[text()='Yes']
	
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement btnYes ;
	
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
	
	@FindBy(xpath = "//*[.='General Information']")
	private WebElement tabGeneralInformation;
	
	@FindBy(xpath = "//*[.='Order Details']")
	private WebElement tabOrderDetails;
	
	@FindBy(xpath = "//b[.='External Related Order ID']/../following-sibling::td")
	private WebElement valueRelatedOrderID;
	
	@FindBy(xpath = "//*[@id='dynaDiv']/child::div[contains(.,'CATTest_Label')]")
	private WebElement labelDisplay ;
	
	
	private boolean mstatus= true;
	
	
	public boolean validateRelatedOrderIDAttribute(String relatedOrderIDValue){
		try{
			waitForElement(tabGeneralInformation);
			iClick(tabGeneralInformation);
			waitForElementDisappear(elementLoading);
			iClick(tabOrderDetails);
			scrollDown();
			waitForElement(valueRelatedOrderID);
			if(valueRelatedOrderID.getText().equalsIgnoreCase(relatedOrderIDValue)){
			   report.reportPassEvent("Validate Realted Order ID Value", "Realted Order ID Value Validated");
			   report.updateTestLog("Validate Realted Order ID Value", "Realted Order ID Value Validated", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Validatec Realted Order ID Value", "Realted Order ID Value not Validated");
			}
			
			
		}catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	public boolean validateDisplayLabelInTask(String labelName){
		mstatus=true;
		try{
			
			waitForElement(labelDisplay);
			if(labelDisplay.getText().equalsIgnoreCase(labelName)){
				report.updateTestLog("Verify label display at task level", "Display label Verified at task level", Status.SCREENSHOT);
			}else report.reportFailEvent("Verify label display at task level", "Display label not Verified");
			
			
		}catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	
	
	
	
	public boolean ConductSiteSurvey(SiteLevelTaskInfo siteLevelTaskInfo){
		try{
			if(waitForElement(ScheduledSurveyDate)){
				ScheduledSurveyDate.click();
				btnToday.get(0).click();
				SurveyCompletionDate.click();
				btnToday.get(1).click();
				waitForElement(ddSitePermitRequired);
				new Select(ddSitePermitRequired).selectByVisibleText("No");
				new Select(ddCPElocationType).selectByVisibleText("Raw Land Site");
				new Select(ddPowerStatus).selectByVisibleText("Existing Power");
				new Select(ddEquipmentLocationDescription).selectByVisibleText("Leased Area");
				this.ClickCompleteButton();
				waitForElement(browser.findElement(By.xpath("//*[text()='Conduct Site Survey' and contains(@onclick, 'COMPLETED')]")));
				
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
	
	public boolean ClickBackButton(int sec){
		try{
			if(waitForElement(btnBack,2)){
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
				iClick(btnComplete, null, "Complete ConductSiteSurvey Task: Complete ConductSiteSurvey Task page: CompleteButton");
				waitforPageLoadComplete();
				waitForElement(btnYes);
				btnYes.click();
				waitforPageLoadComplete();
				report.reportDoneEvent("Complete ConductSiteSurvey Task", " ConductSiteSurvey Task Completed");
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
}
