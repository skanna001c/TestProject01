package com.comcast.century.cso.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTest.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;

public class ManageTab extends Page {

	public ManageTab(FrameworkContext context) {
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

	private boolean mstatus=true;
	
	private String labelName;
	
	@FindBy(css = "span#Manage")
	private WebElement tabManage;
	
	@FindBy(xpath = "//iframe[@id='mainFrame']")
	private WebElement frameMain;
	
	@FindBy(xpath = "//iframe[@id='LabelsFrame']")
	private WebElement frameLabels;
	
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
	
	@FindBy(xpath = "//*[contains(@id,'splitbutton')]")
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
			
			while(!WaitandSwitchToFrame(frameMain)){}
			while(!WaitandSwitchToFrame(frameLabels)){}
			while(!waitForElement(ddTextMoreActions)){}
			ddValueSelect(ddTextMoreActions,ddValueCreateLabel,"Create Label");
			iClick(btnGO);
			Thread.sleep(2000);
			waitForElement(textLabelName);
			labelName="CATTest_Label"+randomNumber(5);
			textLabelName.sendKeys(labelName);
            int btnWidth = Integer.parseInt(btnLabelColor.getCssValue("width").substring(0, 2));
            int yOffset = 5;
            int xoffset = btnWidth - 5;
            Actions btnBuilder = new Actions(browser);
            btnBuilder.moveToElement(btnLabelColor, xoffset ,yOffset );
            btnBuilder.click().perform();
            int rn = Integer.parseInt(randomNumber(1));
            while(!waitForElement(labelColor.get(rn))){}
            iClick(labelColor.get(rn));
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
