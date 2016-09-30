package com.comcast.century.cm.pages;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.ServiceInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.Page;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class ServiceTabPageCM extends Page {

	
	public ServiceTabPageCM(FrameworkContext context){
		super(context);
	}

	@Override
	protected boolean isValidPage() {
		
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		
	}
	
	
	
	@FindBy(id="Service")
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
	
	@FindBy(xpath = "//*[@name='Equipment Fee']")
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
	
	@FindBy(xpath = "//span[.='Plan Search']")
	private WebElement tabPlanSearch ;
	
	
	public boolean mstatus;
	
	public boolean ClickOnServiceTab(){
		mstatus= true;
		try {
			waitforPageLoadComplete();
			 if (waitForElement(tabService)){
				  tabService.click();
				  report.reportDoneEvent("Click On Service Tab", "Service Tab Clicked");
				  waitforPageLoadComplete();
			  }
		} catch (Exception e) {
			mstatus= false;
		}
		return mstatus;
	}
	
	
	public boolean clickOnPlanSearchTab(){
		try{
			waitForElementDisappear(elementLoading);
			waitForElement(tabPlanSearch);
			clickndRelease(tabPlanSearch);
			waitforPageLoadComplete();
			waitForElementDisappear(elementLoading);
		}catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean selectTrunkPRI(){
		try{
			if(waitForElement(txtAuxService)){
				txtAuxService.click();
				keyPress(KeyEvent.VK_TAB,20);
			}
			this.TrunkPRI();
			this.ClickOnContinueButton();
		}catch (Exception e) {
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean SelectPricePlan() throws InterruptedException{
		mstatus = true;
		try{
			WaitandSwitchToFrame(frameMain);
			waitForElement(btnContinueAvailProduct);
			do{
				iClick(btnContinueAvailProduct, btnContinueSelectedProduct, "Click on continue button to navigate to select a service plan: service page: ContinueButton");	
			}while(!waitForElement(btnContinueSelectedProduct));
			waitForElementDisappear(elementLoading);
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}
		return mstatus;
	}
		
		 
	
	
	public boolean selectServices(ServiceInfo serviceInfo) throws InterruptedException{
		mstatus=true;
		try{
			
			this.ClickOnServiceTab();
			this.SelectPricePlan();
			switch(serviceInfo.serviceName)
			{
			case "EDI" :
			case "EDI-BGP" :
			case "EDI-PRI" :
				this.EDI();
				if(serviceInfo.serviceName.equalsIgnoreCase("EDI-PRI")){
					this.TrunkPRI();
				}
				this.EquipmentFee(serviceInfo);
				this.ClickOnContinueButton();
				break;
			case "EPL" :
				this.EPL();
				this.EquipmentFee(serviceInfo);
				this.ClickOnContinueButton();
				break;
			case "ENS" :
			case "ENS-PRI" :
				this.ENS();
				if(serviceInfo.serviceName.equalsIgnoreCase("ENS-PRI")){
					this.TrunkPRI();
				}
				this.EquipmentFee(serviceInfo);
				this.ClickOnContinueButton();
				break;
			case "EVPL" :
				this.EVPL();
				this.EquipmentFee(serviceInfo);
				this.ClickOnContinueButton();
				break;
			default :
				System.out.println("Invalid Service");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			mstatus=false;
		}	
		return mstatus;
	}
	
	
	
	
			 public boolean EDI(){
			  boolean mStatus = true; 
			  if (waitForElement(selectCheckBoxEDI)){
				  selectCheckBoxEDI.click();
				  report.updateTestLog("Select EDI Service", "EDI Service Selected", Status.SCREENSHOT);
			  }else mStatus = false;
			  return mStatus;
	  }
			 
			 public boolean ENS(){
				 mstatus= true;
				 try {
					if(waitForElement(selectCheckBoxENS)){
						 selectCheckBoxENS.click();
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			 }
			 
			public boolean EPL(){
				mstatus= true;
				try {
					if(waitForElement(selectCheckBoxEPL)){
						 selectCheckBoxEPL.click();
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			}
			
			public boolean EVPL() throws AWTException{
				mstatus= true;
				try {
					if(waitForElement(txtAuxService)){
						txtAuxService.click();
						keyPress(KeyEvent.VK_TAB,20);
					}
					if(waitForElement(selectCheckBoxEVPL)){
						 selectCheckBoxEVPL.click();
						
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			}
			
			public boolean TrunkPRI(){
				mstatus= true;
				try {
					if(waitForElement(selectCheckBoxTrunkPRI)){
						selectCheckBoxTrunkPRI.click();
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			}
			
			
			/*public void scrollToEvpl() throws AWTException{
				if(waitForElement(txtAuxService)){
					txtAuxService.click();
					keyPress(KeyEvent.VK_TAB,20);
				}
			}*/
			
			public boolean EquipmentFee(ServiceInfo serviceInfo) throws InterruptedException{
				boolean mStatus = true;
				if((Integer.parseInt(serviceInfo.equipmentFee))>0){
					if(waitForElement(selectCheckBoxEqFee)){
						selectCheckBoxEqFee.click();
						Thread.sleep(2*1000);
						if(waitForElement(txtEqFeeqt)){
						txtEqFeeqt.click();	
						txtEqFeeqt.clear();
						txtEqFeeqt.sendKeys(serviceInfo.equipmentFee);
						report.updateTestLog("Select Equipment Fee", "Equipment Fee Selected", Status.SCREENSHOT);
						}else mStatus = false;
					}
				}
			return mStatus;	
			}
			
			
			public boolean BVE(){
				mstatus = true;
				try {
					if(waitForElement(selectCheckBoxBVE)){
						selectCheckBoxBVE.click();
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			}
			
			public boolean BVETeleworker(){
				mstatus = true;
				try {
					if(waitForElement(selectCheckBoxBVETeleworker)){
						selectCheckBoxBVETeleworker.click();
					 }
				} catch (Exception e) {
					mstatus= false;
				}
				return mstatus;
			}
			
			public boolean ClickOnContinueButton(){
				boolean mStatus = true;
				
				if(waitForElement(btnContinueSelectedProduct)){
					System.out.println("Continue selected button present");
					iClick(btnContinueSelectedProduct, null, "Click on continue button to navigate to feature tab: service page: ContinueButton");					
					waitforPageLoadComplete();
					waitForElementDisappear(elementLoading);
					report.reportDoneEvent("Click Continue", "Continue Clicked");
				}else mStatus = false;
				return mStatus;
			}
	}
	
	
	
	


