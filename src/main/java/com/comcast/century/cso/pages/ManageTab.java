package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.cm.pages.Page;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class ManageTab extends Page {

	public ManageTab(WebDriver browser, SeleniumReport report) {
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

	private boolean mstatus=true;
	
	private String labelName;
	
	@FindBy(css = "span#Manage")
	private WebElement tabManage;
	
	@FindBy(xpath = "//img[@class[contains(.,'expand-right')]]")
	private WebElement btnExpand ;
	
	@FindBy(xpath = "//div[text()='Labels']/../../following-sibling::*/child::*")
	private WebElement btnExpandLabels ;
	
	@FindBy(xpath = "//*[@id='Labels']/a")
	private WebElement LinkLabels ;
	
	@FindBy(xpath = "//*[@id='RightFrame']")
	private WebElement frameRight;
	
	@FindBy(css = "input[placeholder='---More Actions---']")
	private WebElement ddTextMoreActions;
	
	@FindBy(xpath = "//li[text()='Create Label']")
	private WebElement ddValueCreateLabel;
	
	@FindBy(css = "input[value='GO']")
	private WebElement btnGO;
	
	@FindBy(css = "input#labelName")
	private WebElement textLabelName;
	
	@FindBy(css = "div[id='labelColorPalette']>div>div")
	private WebElement btnLabelColor;
	
	@FindBy(css = "div[id*='colorpicker']>a")
	private List<WebElement> labelColor;
	
	@FindBy(css = "input#labelPriority-inputEl")
	private WebElement ddTextLabelPriority;
	
	@FindBy(xpath = "//li[text()='2']")
	private WebElement ddValueLabelPriority;
	
	@FindBy(css = "input#createLab")
	private WebElement btnCreateLabel;
	
	@FindBy(xpath = "//div[text()='Label Created Successfully']")
	private WebElement msgLabelCreatedSuccessfully;
	
	@FindBy(xpath = "//span[.='OK']/following-sibling::*")
	private WebElement btnOK;
	
	public boolean clickOnManageTab(){
		
		try{
			waitforPageLoadComplete();
			waitForElement(tabManage);
			iClick(tabManage);
			waitforPageLoadComplete();
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
	public boolean goToLabelPageCSO(){
		try{
			
			waitForElement(btnExpand);
			iClick(btnExpand);
			waitForElement(btnExpandLabels);
			iClick(btnExpandLabels);
			waitForElement(LinkLabels);
			iClick(LinkLabels);
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameRight);
			
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
	
	public String createLabel(){
		try{
			
			waitForElement(ddTextMoreActions);
			ddValueSelect(ddTextMoreActions,ddValueCreateLabel,"Create Label");
			iClick(btnGO);
			waitForElement(textLabelName);
			labelName="CATTest_Label"+randomNumber(5);
			textLabelName.sendKeys(labelName);
			iClick(btnLabelColor);
			waitForElement(labelColor.get(2));
			iClick(labelColor.get(2));
			waitForElement(ddTextLabelPriority);
			ddValueSelect(ddTextLabelPriority,ddValueLabelPriority,"2");
			iClick(btnCreateLabel);
			waitforPageLoadComplete();
			if(waitForElement(msgLabelCreatedSuccessfully)){
				report.reportPassEvent("Verify label", "label Created Successfully");
				report.updateTestLog("Verify label", "label Created Successfully", Status.SCREENSHOT);
			}else{
				report.reportFailEvent("Verify label", "label not Created");
			}
			iClick(btnOK);
			browser.switchTo().defaultContent();
		}catch(Exception e){
			e.printStackTrace();
		}
		return labelName;
	}
	
}
