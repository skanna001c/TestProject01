package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.SeleniumReport;

public class WorkOrderTabServiceRequestPage extends Page {

	public WorkOrderTabServiceRequestPage(FrameworkContext context) {
		super(context);
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
	
	@FindBy(css = "img[class*='close']")
	private WebElement closeLabelWindow;
	
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
	
	@FindBy(xpath = "//div[contains(@style,'!important')]")
	private List<WebElement> labelContent ;
	
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
 
 
	public boolean verifyLabelCSO(String SRID,String labelName) {
		mstatus = true;
		try {
			
			waitforPageLoadComplete();
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
            browser.switchTo().defaultContent();
            this.verifyLabelContent(labelName);
            waitForElement(linkSOCount);
			iClick(linkSOCount);
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
			waitForElement(labelCount);
			if(labelCount.getText().equalsIgnoreCase("1")){
				report.updateTestLog("Verify label count in SO", "Label count Verified", Status.SCREENSHOT);
			} else report.reportFailEvent("Verify label count in SO", "Label count not  Verified");
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
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
	
	public boolean verifyLabelContent(String labelName){
		mstatus=true;
		try{
			WaitandSwitchToFrame(frameRight);
			waitForElement(labelCount);
				if(labelCount.getText().equalsIgnoreCase("1")){
				report.updateTestLog("Verify label count", "Label count verified",Status.SCREENSHOT);
			}else  report.reportFailEvent("Verify label count","Label count not verified" );
			iClick(labelCount);
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameDetailCondition);
			if(labelContent.get(0).getText().equalsIgnoreCase(labelName)){
				if(labelContent.get(1).getText().equalsIgnoreCase("Doe, John")){
					if(isElementDisplayed(labelContent.get(2))){
						report.reportPassEvent("Verify label added date & time", "label added date & time present");
					}else report.reportFailEvent("Verify label added date & time", "label added date & time not present");
					report.reportPassEvent("Verify label added by", "label added by verified");
				}else report.reportFailEvent("Verify label added by", "label added by not verified");
				report.updateTestLog("Verify label name", "label name verified",Status.SCREENSHOT);
			}else report.reportFailEvent("Verify label name", "label name not verified");
			browser.switchTo().defaultContent();
			WaitandSwitchToFrame(frameRight);
			closeLabelWindow.click();
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
