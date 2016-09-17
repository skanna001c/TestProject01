package com.comcast.century.cm.pages;



import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.ServiceInfo;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class FeatureTabPageCM extends Page {

	public FeatureTabPageCM(FrameworkContext context){
		super(context);
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
	
	private boolean mstatus;
	
	public boolean BGP(){
		mstatus = true;
		try {
			if(waitForElement(chkBoxBGP)){
				chkBoxBGP.click();
			}
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean ENS() throws InterruptedException{
		mstatus = true;		
		try {
			waitForElementDisappear(elementLoading);
			if(waitForElement(txtUNIandEVCqt.get(0))){
				txtUNIandEVCqt.get(0).clear();
				iSendKeys(txtUNIandEVCqt.get(0),"2");
				txtUNIandEVCqt.get(1).clear();
				iSendKeys(txtUNIandEVCqt.get(1),"2");
				scrollDown();
				waitForElement(btnContinue);
				iClick(btnContinue,null,"Click on continue in feature tab: feature tab: ContinueButton");
				waitForElementDisappear(elementLoading);
				
			}
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean EVPL() throws InterruptedException, AWTException{
		mstatus = true;		
		try {
			waitForElementDisappear(elementLoading);
			if(waitForElement(txtUNIqt)){
				txtUNIqt.click();
				txtUNIqt.clear();
				iSendKeys(txtUNIqt, "3");
				waitForElement(txtUNIandEVCqt.get(0));
				txtUNIandEVCqt.get(0).click();
				txtUNIandEVCqt.get(0).clear();
				iSendKeys(txtUNIandEVCqt.get(0), "2");
				scrollDown();
				waitForElement(btnContinue);
				iClick(btnContinue,null,"Click on continue in feature tab: feature tab: ContinueButton");
				waitForElementDisappear(elementLoading);
			}
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean BVE(){
		mstatus = true;		
		try {
		waitForElementDisappear(elementLoading);
		enterQuantity(txtAdditionalAlternateTN,"5");
		enterQuantity(txtStandardSeat,"2");
		enterQuantity(txtUCSeat,"3");
		enterQuantity(txtTfn,"1");
		scrollDown();
		waitForElement(btnContinue);		
		iClick(btnContinue,null,"Click on continue in feature tab: feature tab: ContinueButton");		
	    waitForElementDisappear(elementLoading);
		} catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
		
	}
	
	
	public boolean configureServices(ServiceInfo serviceInfo){
		mstatus=true;
		try{
			
			switch(serviceInfo.serviceName)
			{
			case "EDI" :
			case "EDI-BGP" :
			case "EDI-ToF" :
				if(serviceInfo.serviceName.equalsIgnoreCase("EDI-BGP")){
					this.BGP();
				}
				this.ClickOnContinueButton();
				break;
			case "EPL" :
				this.ClickOnContinueButton();
				break;
			case "ENS" :
			case "ENS-PRI" :
				this.ENS();
				break;
			case "EVPL" :
				this.EVPL();
				break;
			default :
				System.out.println("Invalid Service");
			}
		}catch(Exception e){
			mstatus=false;
		}
		return mstatus;
	}
	
	public boolean ClickOnContinueButton() throws InterruptedException{
		mstatus = true;		
		try {
			waitForElementDisappear(elementLoading);
			scrollDown();
			waitForElement(btnContinue);
			iClick(btnContinue,null,"Click on continue in feature tab: feature tab: ContinueButton");			
		    waitForElementDisappear(elementLoading);			 
	
	} catch (Exception e) {
		mstatus = false;
	}
	return mstatus;
	}
}
