package com.comcast.century.cm.pages;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.ServiceInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;




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
	
	Logger log = Logger.getLogger(ServiceTabPageCM.class);
	
	
	@FindBy(id="Service")
	private WebElement tabService;
	
	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadServOrderManagementPanel.exc')]")
	private WebElement frameMain;
	
	@FindBy(id = "loadAvailProduct") 
	private WebElement btnContinueAvailProduct;
	
	@FindBy(id = "loadSelectedProducts")
	private WebElement btnContinueSelectedProduct;

	@FindBy(name = "BVE")
	private WebElement selectCheckBoxBVE;
	
	@FindBy(name = "BVE Teleworker")
	private WebElement selectCheckBoxBVETeleworker;
	
	@FindBy(name = "EDI")
	private WebElement selectCheckBoxEDI;
	
	@FindBy(name = "ENS")
	private WebElement selectCheckBoxENS;
	
	@FindBy(name = "EPL")
	private WebElement selectCheckBoxEPL;
	
	@FindBy(name = "Equipment Fee")
	private WebElement selectCheckBoxEqFee;
	
	@FindBy(xpath = "//div[text()='Equipment Fee']/../following-sibling::td/child::div/child::input[@type='text']")
	private WebElement txtEqFeeqt;
	
	@FindBy(xpath = "//div[text()='Auxiliary Service']/../following-sibling::td/child::div/child::input[@type='text']")
	private WebElement txtAuxService;
	
	@FindBy(name = "EVPL")
	private WebElement selectCheckBoxEVPL;
	
	@FindBy(name = "Trunk-PRI")
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
			log.info(e.getMessage());
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
			log.info(e.getMessage());
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
			log.info(e.getMessage());
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
			log.info(e.getMessage());
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
			case "ENS" :this.ENS();
			case "ENS-PRI" :this.ENS();this.TrunkPRI();
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
				log.info("Invalid Service");
			}
			
		}catch(Exception e){
			log.info(e.getMessage());
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
					log.info(e.getMessage());
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
					log.info(e.getMessage());
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
					log.info(e.getMessage());
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
					log.info(e.getMessage());
					mstatus= false;
				}
				return mstatus;
			}
			
									
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
					log.info(e.getMessage());
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
					log.info(e.getMessage());
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
	
	
	
	


