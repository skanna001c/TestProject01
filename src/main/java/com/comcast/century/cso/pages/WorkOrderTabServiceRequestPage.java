package com.comcast.century.cso.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class WorkOrderTabServiceRequestPage extends Page {

	protected WorkOrderTabServiceRequestPage(WebDriver browser, SeleniumReport report) {
		super(browser, report);
		// TODO Auto-generated constructor stub
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
	
	@FindBy(css = "span#workorder")
	private WebElement tabWorkorder;
	
	@FindBy(xpath = "//*[@id='RightFrame']")
	private WebElement frameRight;
	
	@FindBy(xpath = "//img[@class[contains(.,'expand-right')]]")
	private WebElement btnExpand ;

	@FindBy(xpath = "//div[text()='Order Search']/../../following-sibling::*/child::*")
	private WebElement btnExpandOrderSearch ;
	
	@FindBy(xpath = "//*[@id='servicerequest']/a")
	private WebElement LinkServiceRequest ;
	
	@FindBy(xpath = "//*[@id='servicereqId']")
	private WebElement txtServiceReqId ;
	
	@FindBy(xpath = "//span[text()='Search']/following-sibling::*")
	private WebElement btnSearch ;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//span[.='SR ID']/../../preceding-sibling::div/child::*")
	private WebElement chkBoxSRID ;
	
	@FindBy(css = "input[placeholder='---More Actions---']")
	private WebElement ddTextMoreActions;
	
	@FindBy(xpath = "//li[.='Assign Label']")
	private WebElement ddValueAssignLabel;
	
	@FindBy(css = "input[value='GO']")
	private WebElement btnGO;
	
	@FindBy(xpath = "//*[@id='codition']")
	private WebElement frameCondition;
	
	@FindBy(xpath = "//*[@id='srDetailCodition']")
	private WebElement frameDetailCondition;
	
	@FindBy(xpath = "//input[@placeholder='---Select Label---']")
	private WebElement txtSelectLabel;
	
	@FindBy(xpath = "//*[@id='ApplyTo']")
	private WebElement btnApply ;
	
	@FindBy(xpath = "//span[text()='OK']/following-sibling::span")
	private WebElement btnOk ;
	
	@FindBy(xpath = "//div[.='Label(s) applied successfully']")
	private WebElement msgLabelAppliedSuccessfully ;
	
	@FindBy(xpath = "//a[contains(@onclick,'callLabel')]")
	private WebElement labelCount ;
	
	@FindBy(xpath = "//div[.='Doe, John']")
	private WebElement labeladdedBy ;
	
	@FindBy(xpath = "//a[contains(@onclick,'callServOrder')]")
	private WebElement linkSOCount ;
	
	
	
	private boolean mstatus=true;
	
 public boolean clickOnWorkorderTabCSO(){
		
		try{
			waitforPageLoadComplete();
			waitForElement(tabWorkorder);
			iClick(tabWorkorder);
			waitforPageLoadComplete();
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
	public boolean assignLabel(String SRID,String labelName){
		
		String xpathLabel="//div[text()='"+labelName+"']/../preceding-sibling::td/child::div/child::div";
		  try{
			  waitForElement(btnExpand);
				btnExpand.click();
				btnExpandOrderSearch.click();
				LinkServiceRequest.click();
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameRight);
				waitForElementDisappear(elementLoading);
				waitForElement(txtServiceReqId);
				txtServiceReqId.sendKeys(SRID);
				waitForElement(btnSearch);
				iClick(btnSearch);
				waitForElementDisappear(elementLoading);
				waitForElement(chkBoxSRID);
				iClick(chkBoxSRID);
				waitForElement(ddTextMoreActions);
				ddValueSelect(ddTextMoreActions, ddValueAssignLabel, "Assign Label");
				iClick(btnGO);
				waitforPageLoadComplete();
				WaitandSwitchToFrame(frameCondition);
				waitForElement(txtSelectLabel);
				txtSelectLabel.sendKeys(labelName);
				waitUntilElementPresent(By.xpath(xpathLabel), 10);
				WebElement chkboxLabelName= browser.findElement(By.xpath(xpathLabel));
				chkboxLabelName.click();
				btnApply.click();
				if(waitForElement(msgLabelAppliedSuccessfully)){
					report.reportPassEvent("Verify status", "label applied successfully");
					report.updateTestLog("Verify status", "label applied successfully", Status.SCREENSHOT);
				}else{
					report.reportFailEvent("Verify status", "label not applied");
				}
				waitForElement(btnOk);
				btnOk.click();
				waitForElementDisappear(elementLoading);
				browser.switchTo().defaultContent();
		  }catch(Exception e){
				e.printStackTrace();
				mstatus=false;
		  }
		  return mstatus;
	  }
	
	public boolean verifyLabels(String SRID, String labelName ){
		String labelAddedBy = "Doe, John";
		try{
			WaitandSwitchToFrame(frameRight);
			if(waitForElement(labelCount)){
				if(labelCount.getText().equalsIgnoreCase("1")){
				report.reportPassEvent("Verify label count", "Label count verified");
			}else{
			    report.reportFailEvent("Verify label count","Label count not verified" );}
			}
			iClick(labelCount);
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameDetailCondition);
			WebElement LabelName = browser.findElement(By.xpath("//div[text()='"+labelName+"']"));
			if(waitForElement(LabelName)){
				if(LabelName.getText().equalsIgnoreCase(labelName)){
					report.reportPassEvent("Verify lable name", "Label Name Verified");
					report.updateTestLog("Verify lable name", "Label Name Verified", Status.SCREENSHOT);
				}else{
					report.reportFailEvent("Verify lable name", "Label Name not verified");
				}
			}if(waitForElement(labeladdedBy)){
				if(labeladdedBy.getText().equalsIgnoreCase(labelAddedBy)){
					report.reportPassEvent("Verify lable added by", "Label added by verified");
				}else{
					report.reportFailEvent("Verify lable added by", "Label added by not verified");
				}
			
		}
			browser.switchTo().defaultContent();
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		 return mstatus;
	}
	
	public boolean verifyAndAssignLabelsSO(){
		try{
			WaitandSwitchToFrame(frameRight);
			waitForElement(linkSOCount);
			iClick(linkSOCount);
			waitforPageLoadComplete();
			
			
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		 return mstatus;
	}
     
}