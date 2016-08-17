package com.comcast.century.cm.pages;



import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.utils.SeleniumReport;

public class FeatureTabPageCM extends Page {

	public FeatureTabPageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}

	@Override
	protected boolean isValidPage() {
		
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		
		
	}
	
	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadServOrderManagementPanel.exc')]")
	private WebElement frameMain;
	
	@FindBy(xpath = "//b[text()='BGP Configuration']/preceding-sibling::input[@type='checkbox']")
	private WebElement chkBoxBGP ;
	
	@FindBy(xpath = ".//*[@value='Continue' and @type='button']")
	private WebElement btnContinue;
	
	@FindBy(xpath = "//span[contains(.,'Min 1')]/preceding-sibling::input")
	private List<WebElement>  txtUNIandEVCqt;
	
	@FindBy(xpath = "//span[contains(.,'Min 2')]/preceding-sibling::input")
	private WebElement  txtUNIqt;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(xpath = "//b[text()='Seats']/../../../following-sibling::tr[1]/child::td[2]/child::*/child::input[1]")
	private WebElement txtStandardSeat  ;
	
	@FindBy(xpath = "//b[text()='Seats']/../../../following-sibling::tr[2]/child::td[2]/child::*/child::input[1]")
	private WebElement txtUCSeat  ;
	
	@FindBy(xpath = "//b[text()='Optional Features']/../../../following-sibling::tr[1]/child::td[2]/child::*/child::input[1]")
	private WebElement txtTfn ;
	
	@FindBy(xpath = "//b[text()='TN Block Selection']/../../../following-sibling::tr[1]/child::td[2]/child::*/child::input[1]")
	private WebElement txtAdditionalAlternateTN ;
	
	
	
	public void BGP(){
		if(waitForElement(chkBoxBGP)){
			chkBoxBGP.click();
		}
	}
	
	public void ENS() throws InterruptedException{
		waitForElementDisappear(elementLoading);
		if(waitForElement(txtUNIandEVCqt.get(0))){
			txtUNIandEVCqt.get(0).clear();
			txtUNIandEVCqt.get(0).sendKeys("2");
			txtUNIandEVCqt.get(1).clear();
			txtUNIandEVCqt.get(1).sendKeys("2");
			waitForElement(btnContinue);
			clickndRelease(btnContinue);
			waitForElementDisappear(elementLoading);
			
		}
	}
	
	public void EVPL() throws InterruptedException, AWTException{
		waitForElementDisappear(elementLoading);
		if(waitForElement(txtUNIqt)){
			//Thread.sleep(8000);
			txtUNIqt.click();
			txtUNIqt.clear();
			keyPress(KeyEvent.VK_NUMPAD3,1);
			waitForElement(txtUNIandEVCqt.get(0));
			txtUNIandEVCqt.get(0).click();
			txtUNIandEVCqt.get(0).clear();
			keyPress(KeyEvent.VK_NUMPAD2,1);
			scrollDown();
			waitForElement(btnContinue);
			clickndRelease(btnContinue);
			waitForElementDisappear(elementLoading);
		}
	}
	
	public void BVE(){
		waitForElementDisappear(elementLoading);
		enterQuantity(txtAdditionalAlternateTN,"5");
		enterQuantity(txtStandardSeat,"2");
		enterQuantity(txtUCSeat,"3");
		enterQuantity(txtTfn,"1");
		scrollDown();
		waitForElement(btnContinue);
		waitForElement(btnContinue);
		btnContinue.sendKeys(Keys.ENTER);
	    waitForElementDisappear(elementLoading);
		
	}
	
	public void ClickOnContinueButton() throws InterruptedException{
		waitForElementDisappear(elementLoading);
		scrollDown();
		waitForElement(btnContinue);
		waitForElement(btnContinue);
		btnContinue.click();
		//btnContinue.sendKeys(Keys.ENTER); Updated with click method for continue button By krajam003c
	    waitForElementDisappear(elementLoading);			 
	}

}
