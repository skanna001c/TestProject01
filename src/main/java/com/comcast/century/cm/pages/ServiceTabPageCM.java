package com.comcast.century.cm.pages;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

public class ServiceTabPageCM extends Page {

	public ServiceTabPageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}

	@Override
	protected boolean isValidPage() {
		
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		
	}
	
	private static String EqFeeqt;
	
	@FindBy(xpath = "//a[.='Service']")
	private WebElement tabService;
	
	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadServOrderManagementPanel.exc')]")
	private WebElement frameMain;
	
	@FindBy(xpath = "//input[@id='loadAvailProduct']")
	private WebElement btnContinueAvailProduct;
	
	@FindBy(xpath = "//*[@id='loadSelectedProducts']")
	private WebElement btnContinueSelectedProduct;

	@FindBy(xpath = "//input[@name='BVE']")
	private WebElement selectCheckBoxBVE;
	
	@FindBy(xpath = "//input[@name='BVE Teleworker']")
	private WebElement selectCheckBoxBVETeleworker;
	
	@FindBy(xpath = "//input[@name='EDI']")
	private WebElement selectCheckBoxEDI;
	
	@FindBy(xpath = "//input[@name='ENS']")
	private WebElement selectCheckBoxENS;
	
	@FindBy(xpath = "//input[@name='EPL']")
	private WebElement selectCheckBoxEPL;
	
	@FindBy(xpath = "//input[@name='Equipment Fee']")
	private WebElement selectCheckBoxEqFee;
	
	@FindBy(xpath = "//div[text()='Equipment Fee']/../following-sibling::td/child::div/child::input[@type='text']")
	private WebElement txtEqFeeqt;
	
	@FindBy(xpath = "//div[text()='Auxiliary Service']/../following-sibling::td/child::div/child::input[@type='text']")
	private WebElement txtAuxService;
	
	@FindBy(xpath = "//input[@name='EVPL']")
	private WebElement selectCheckBoxEVPL;
	
	@FindBy(xpath = "//input[@name='Trunk-PRI']")
	private WebElement selectCheckBoxTrunkPRI;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	
	
	public void ClickOnServiceTab(){
		 waitforPageLoadComplete();
		 if (waitForElement(tabService)){
			  tabService.click();
			  report.reportDoneEvent("Click On Service Tab", "Service Tab Clicked");
			  waitforPageLoadComplete();
		  }	
	}
	
	
	public void SelectPricePlan() throws InterruptedException{
		  if (WaitandSwitchToFrame(frameMain)){
			  if(waitForElement(btnContinueAvailProduct)){
			  btnContinueAvailProduct.click();
			  jsClickWE(btnContinueAvailProduct);
			  //scrollToElementandclick(btnContinueAvailProduct); 
				waitForElementDisappear(elementLoading);
				//scrollDown();
		  }
	} 
}
			 public void EDI(){
			  if (waitForElement(selectCheckBoxEDI)){
				  selectCheckBoxEDI.click();
				  EqFeeqt="1";
				  report.updateTestLog("Select EDI Service", "EDI Service Selected", Status.SCREENSHOT);
			  }
	  }
			 
			 public void ENS(){
				 if(waitForElement(selectCheckBoxENS)){
					 selectCheckBoxENS.click();
					 EqFeeqt="2";
				 }
			 }
			 
			public void EPL(){
				if(waitForElement(selectCheckBoxEPL)){
					 selectCheckBoxEPL.click();
					 EqFeeqt="2";
				 }
			}
			
			public void EVPL() throws AWTException{
				if(waitForElement(txtAuxService)){
					txtAuxService.click();
					keyPress(KeyEvent.VK_TAB,20);
				}
				if(waitForElement(selectCheckBoxEVPL)){
					 selectCheckBoxEVPL.click();
					 EqFeeqt="3";
					
				 }
			}
			
			public void TrunkPRI(){
				if(waitForElement(selectCheckBoxTrunkPRI)){
					selectCheckBoxTrunkPRI.click();
				 }
			}
			
			
			/*public void scrollToEvpl() throws AWTException{
				if(waitForElement(txtAuxService)){
					txtAuxService.click();
					keyPress(KeyEvent.VK_TAB,20);
				}
			}*/
			
			public void EquipmentFee() throws InterruptedException{
				if(waitForElement(selectCheckBoxEqFee)){
					selectCheckBoxEqFee.click();
					Thread.sleep(2*1000);
					if(waitForElement(txtEqFeeqt)){
					txtEqFeeqt.click();	
					txtEqFeeqt.clear();
					txtEqFeeqt.sendKeys(EqFeeqt);
					report.updateTestLog("Select Equipment Fee", "Equipment Fee Selected", Status.SCREENSHOT);
				 }
			}
				
	}
     
			
			public void BVE(){
				if(waitForElement(selectCheckBoxBVE)){
					selectCheckBoxBVE.click();
				 }
			}
			
			public void BVETeleworker(){
				if(waitForElement(selectCheckBoxBVETeleworker)){
					selectCheckBoxBVETeleworker.click();
				 }
			}
			
			public void ClickOnContinueButton(){
				
				
				if(waitForElement(btnContinueSelectedProduct)){
					System.out.println("Continue selected button present");
					btnContinueSelectedProduct.sendKeys(Keys.ENTER);
					waitforPageLoadComplete();
					waitForElementDisappear(elementLoading);
					report.reportDoneEvent("Click Continue", "Continue Clicked");
				}
			}
	}
	
	
	
	


