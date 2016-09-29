package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.cso.pages.WorkOrderTabPageCSO;
import com.comcast.century.data.ProcessInfo;
import com.comcast.century.data.ServiceInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.DataDump;
import com.comcast.utils.IDataDump;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;
import com.comcast.utils.ComcastTest.FrameworkContext;

public class ProcessTabPageCM extends Page {

	
	public ProcessTabPageCM(FrameworkContext context){
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
	
	
		@FindBy(xpath = "//select[@paramname='Terms']")
		private WebElement ddSelectTerms;
		
		@FindBy(xpath = "//input[@value='Save']")
		private WebElement btnSave;
		
		@FindBy(xpath = "//a[text()='UNI' or contains(text(),'UNI:')]")
		private WebElement LinkUNI1;
		
		@FindBy(xpath = "//a[text()='UNI~2' or contains(text(),'UNI~2:')]")
		private WebElement LinkUNI2;
		
		@FindBy(xpath = "//a[text()='UNI~3' or contains(text(),'UNI~3:')]")
		private WebElement LinkUNI3;
		
		@FindBy(xpath = "//a[text()='UNI~4' or contains(text(),'UNI~4:')]")
		private WebElement LinkUNI4;
		
		@FindBy(xpath = "//a[text()='BGP']")
		private WebElement LinkBGP;
		
		@FindBy(xpath = "//a[text()='Trunk-PRI']")
		private WebElement LinkTrunkPRI;
	
		@FindBy(xpath = "//select[@paramname='BGP Change?']")
		private WebElement ddBGPChange;
		
		@FindBy(xpath = "//a[text()='EVC']")
		private WebElement LinkEVC1;
		
		@FindBy(xpath = "//a[contains(@onclick,'Trunk-PRI')][.='EVC']")
		private WebElement LinkEVCPRI;
		
		@FindBy(xpath = "//a[contains(@onclick,'Trunk-PRI')][.='UNI']")
		private WebElement LinkUNIPRI;
		
		@FindBy(xpath = "//a[text()='EVC~2']")
		private WebElement LinkEVC2;
		
		@FindBy(xpath = "//a[text()='EVC~3']")
		private WebElement LinkEVC3;
		
		@FindBy(xpath = "//a[text()='Equipment Fee']")
		private WebElement LinkEquipmentFee;
		
		@FindBy(xpath = "//a[text()='Equipment Fee~2']")
		private WebElement LinkEquipmentFee2;
		
		@FindBy(xpath = "//a[text()='Equipment Fee~3']")
		private WebElement LinkEquipmentFee3;
		
		@FindBy(xpath = "//a[text()='Equipment Fee Configuration'][contains(@onmouseover,'Equipment Fee Configuration')]")
		private List<WebElement> LinkEquipmentFeeConfiguration;
		
		@FindBy(xpath = "//img[@title='Address Lookup']")
		private WebElement imgAddressLookup;
		
		@FindBy(xpath = "//*[@id='codition']")
		private List<WebElement> frameCondition;
		
		@FindBy(id = "1_SiteName")
		private WebElement txtSiteName;
		
		@FindBy(xpath = "//*[@value='Search']")
		private WebElement btnSearch;
		
		@FindBy(xpath = "//*[@id='addr']")
		private WebElement btnRadioSelectSite;
		
		@FindBy(xpath = "//input[@id='backToProcess']")
		private WebElement btnOK;
		
		@FindBy(xpath = ".//select[@paramname='Transport Type']")
		private WebElement TransportType;
		
		@FindBy(xpath = "//select[@paramname='Aggregator Needed']")
		private WebElement ddAggregatorNeeded;
		
		@FindBy(xpath = "//input[@id='surClliCom-inputEl']")
		private WebElement ddtxtSURCILI;
		
		@FindBy(xpath = "//input[@paramname='UNI Number']")
		private WebElement txtUNInumber;
		
		@FindBy(xpath = "//select[@paramname='UNI Port Speed']")
		private WebElement ddUNIPortSpeed;
		
		@FindBy(xpath = "//input[@id='locZCombo-inputEl']")
		private WebElement ddtxtLocationZuni;
		
		@FindBy(xpath = "//input[@id='locZCombo-inputEl']/../following-sibling::*/child::*")
		private WebElement ddArrwLocationZuni;
		
		@FindBy(xpath = "//input[@id='locACombo-inputEl']/../following-sibling::*/child::*")
		private WebElement ddArrwLocationAuni;
		
		@FindBy(xpath = "//div[contains(text(),'ProdTest_ENT') or contains(text(),'CATTest_ENT')]")
		private List<WebElement> ddvalueLocationZuni;
		
		@FindBy(xpath = "//input[@paramname='EVC Number']")
		private WebElement txtEVCnumber;
		
		@FindBy(xpath = "//select[@paramname='Basic CoS Bandwidth']")
		private WebElement ddBasicCoSBandwidth;
		
		@FindBy(xpath = "//select[@paramname='IP Block Change Required?']")
		private WebElement ddIpBlockChange;
		
		@FindBy(xpath = "//select[@paramname='IP Address Allocation Provided By']")
		private WebElement ddIpAddressAllocation;
		
		@FindBy(xpath = "//select[@paramname='Additional IP Address Allocation Required']")
		private WebElement ddAdditionalIpAddress;
		
		@FindBy(xpath = "//select[@paramname='Existing EVC']")
		private WebElement ddExistingEVC;
		
		@FindBy(xpath = "//select[@paramname='EVC Area Type']")
		private WebElement ddEVCAreaType;
		
		@FindBy(xpath = "//select[@paramname='Premium CoS Bandwidth']")
		private WebElement ddPremiumCoSBandwidth;
		
		@FindBy(xpath = "//select[@paramname='Priority CoS Bandwidth']")
		private WebElement ddPriorityCoSBandwidth;
		
		@FindBy(xpath = "//select[@paramname='Max UNIs Exceeded']")
		private WebElement ddMaxUNIExceeded;
		
		@FindBy(xpath = "//select[@paramname='Max EVCs Exceeded Per UNI']")
		private WebElement ddMaxEVCExceeded;
		
		@FindBy(xpath = "//input[@paramname='Customer VLAN Info']")
		private WebElement txtCustomerVLANInfo;
		
		@FindBy(xpath = "//input[@id='EqFeeCombo-inputEl']")
		private WebElement ddtxtEqFeeServiceLocation;
		
		@FindBy(xpath = "//input[@id='EqFeeCombo-inputEl']/../following-sibling::td/child::div")
		private WebElement ddArrwEqFeeServiceLocation;
		
		@FindBy(xpath = "//div[contains(text(),'ProdTest_ENT') or contains(text(),'CATTest_ENT')]")
		private List<WebElement> ddValueEqFeeServiceLocation;
		
		@FindBy(xpath = "//input[@value='Continue']")
		private WebElement btnContinue;
		
		@FindBy(xpath = "//input[@id='customerOrderSignatureDate-inputEl']/../following-sibling::*/child::*")
		private WebElement dtCustomerOrderSig;
	
		@FindBy(xpath = "//span[text()='No']/following-sibling::*")
		private WebElement BtnEqFeePopUp;
		
		@FindBy(xpath = "//div[text()='loading...']")
		private WebElement elementLoading ;
		
		@FindBy(xpath = "//b[text()='Service Request ID :']/../following-sibling::*[position()=1]")
		private WebElement txtSRId ;
		
		private boolean mstatus;
		
		
		public IDataDump processServices(ServiceInfo serviceInfo,ProcessInfo processInfo, IDataDump iDataDump){

			String UNINo1, UNINo2, UNINo3,EVCNo1,EVCNo2;
			IDataDump localiDataDump=iDataDump;
			try{
				switch(serviceInfo.serviceName)
				{
				case "EDI" :
				case "EDI-BGP" :
				case "EDI-PRI" :
					UNINo1 = this.UNIConfiguration(processInfo,localiDataDump.getValue("SITE1_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType1,localiDataDump.getValue("SITE1_RT"),localiDataDump);					
					EVCNo1 = this.EVCConfiguration_EDI(processInfo);					
					localiDataDump.setValue("EVCcount_RT","1");
					if(serviceInfo.serviceName.equalsIgnoreCase("EDI-BGP")){
						this.BGPConfiguration();
					} else if(serviceInfo.serviceName.equalsIgnoreCase("EDI-PRI")){
						this.TrunkPRIConfiguration(processInfo, localiDataDump.getValue("SITE1_RT"), serviceInfo);
						/*this.Trunk_PRI(processInfo);
						this.UNIConfiguration_PRI(processInfo,localiDataDump.getValue("SITE1_RT"),serviceInfo );
						this.EVCConfiguration_PRI(processInfo);*/
					}
					break;
				case "EPL" :
					UNINo1 = this.UNIConfiguration(processInfo,localiDataDump.getValue("SITE1_RT") );
					localiDataDump=SetSite(processInfo.UNITransportType1,localiDataDump.getValue("SITE1_RT"),localiDataDump);					
					UNINo2 = this.UNI2Configuration(processInfo,localiDataDump.getValue("SITE2_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType2,localiDataDump.getValue("SITE2_RT"),localiDataDump);					
					EVCNo1 = this.EVCConfiguration_EPL(processInfo);					
					localiDataDump.setValue("EVCcount_RT","1");
					break;
				case "ENS" :
				case "ENS-PRI" :
					UNINo1 = this.UNIConfiguration(processInfo,localiDataDump.getValue("SITE1_RT") );
					localiDataDump=SetSite(processInfo.UNITransportType1,localiDataDump.getValue("SITE1_RT"),localiDataDump);					
					UNINo2 = this.UNI2Configuration(processInfo, localiDataDump.getValue("SITE2_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType2,localiDataDump.getValue("SITE2_RT"),localiDataDump);					
					EVCNo1 = this.EVCConfiguration_ENS(processInfo);
					EVCNo2 = this.EVC2Configuration_ENS(processInfo);
					localiDataDump.setValue("EVCcount_RT","2");
					if(serviceInfo.serviceName.equalsIgnoreCase("ENS-PRI")){
						this.TrunkPRIConfiguration(processInfo, localiDataDump.getValue("SITE1_RT"), serviceInfo);
						/*this.Trunk_PRI(processInfo);
						this.UNIConfiguration_PRI(processInfo,localiDataDump.getValue("SITE1_RT"),serviceInfo );
						this.EVCConfiguration_PRI(processInfo);*/
					}
					break;
				case "EVPL" :
					UNINo1 = this.UNIConfiguration(processInfo,localiDataDump.getValue("SITE1_RT") );
					localiDataDump=SetSite(processInfo.UNITransportType1, localiDataDump.getValue("SITE1_RT"),localiDataDump);					
					UNINo2 = this.UNI2Configuration(processInfo,localiDataDump.getValue("SITE2_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType2, localiDataDump.getValue("SITE2_RT"), localiDataDump);					
					UNINo3 = this.UNI3Configuration(processInfo,localiDataDump.getValue("SITE3_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType3, localiDataDump.getValue("SITE3_RT"), localiDataDump);					
					EVCNo1 = this.EVCConfiguration_EVPL(processInfo);					
					EVCNo2 = this.EVC2Configuration_EVPL(processInfo);					
					localiDataDump.setValue("EVCcount_RT","2");
					break;	
				default :
					System.out.println("Invalid Service");
				}
				
				int FiberCount = 0;
				int CoaxCount = 0;
				
				// Calculation of Fiber/Coax site count and update it in dump
				//Added by Kesavan on 07th Sep 2016
				if (processInfo.UNITransportType1.equalsIgnoreCase("fiber")) FiberCount++;
				else if(processInfo.UNITransportType1.equalsIgnoreCase("coax"))  CoaxCount++;
				
				if (processInfo.UNITransportType2.equalsIgnoreCase("fiber")) FiberCount++; 
				else if(processInfo.UNITransportType2.equalsIgnoreCase("coax"))  CoaxCount++;
				
				if (processInfo.UNITransportType3.equalsIgnoreCase("fiber")) FiberCount++; 
				else if(processInfo.UNITransportType3.equalsIgnoreCase("coax"))  CoaxCount++;
				
				localiDataDump.setValue("Fibercount_RT",Integer.toString(FiberCount));	
				System.out.println("" + FiberCount);
				localiDataDump.setValue("Coaxcount_RT",Integer.toString(CoaxCount));
				
				if( serviceInfo.equipmentFee.equalsIgnoreCase("0") || serviceInfo.equipmentFee ==null || serviceInfo.equipmentFee =="" ){
					this.ClickOnContinueButton();
				}
				else if (serviceInfo.equipmentFee.equalsIgnoreCase("1")){
					this.EqFeeConfiguration(processInfo);
					this.ClickOnContinueButton();
				}else if(serviceInfo.equipmentFee.equalsIgnoreCase("2")){
					this.EqFeeConfiguration(processInfo);
					this.EqFee2Configuration(processInfo);
					this.ClickOnContinueButton();
				}else if(serviceInfo.equipmentFee.equalsIgnoreCase("3")){
					this.EqFeeConfiguration(processInfo);
					this.EqFee2Configuration(processInfo);
					this.EqFee3Configuration(processInfo);
					this.ClickOnContinueButton();
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			return localiDataDump;
		}
		
		
		public IDataDump SetSite(String TT,String Site,IDataDump iDataDump)
		{  int maxFiber=Integer.parseInt((new TestSettings().getValue("MAXNOOFFIBERSITES")));
		   int maxCoax=Integer.parseInt((new TestSettings().getValue("MAXNOOFCOAXSITES")));
		 if (TT.equalsIgnoreCase("fiber"))
		 {
		   for (int i = 1; i <= maxFiber; i++) {
				if ((iDataDump.getValue("FiberSite" + i+"_RT").equalsIgnoreCase(""))) {
					 iDataDump.setValue("FiberSite" + i+"_RT",Site);
					 break;

				}
			}
		  		}
		 
		 if (TT.equalsIgnoreCase("coax"))
		 {
		   for (int i = 1; i <= maxCoax; i++) {
				if ((iDataDump.getValue("CoaxSite" + i+"_RT").equalsIgnoreCase(""))) {
					 iDataDump.setValue("CoaxSite" + i+"_RT",Site);
					 break;
				}
			}
		  		}
		 return iDataDump;
		}
		
		/*Method to save terms on process page
		 * 
		 * 
		 * 
		 */
		
	public boolean ProcessConfiguration(ProcessInfo processInfo) {
		boolean mstatus = true;

		try {			
			waitForElement(ddSelectTerms);
			new Select(ddSelectTerms).selectByVisibleText(processInfo.terms);
			iClick(btnSave, null, "Click on save button: Process page: SaveButton");
			report.reportDoneEvent("Save Terms", "Terms Saved");
			waitForElementDisappear(elementLoading);
		} catch (Exception e) {
			e.printStackTrace();
			mstatus = false;
		}
		return mstatus;
	}
		
		
		/*Method to save terms for Trunk-PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean Trunk_PRI(ProcessInfo processInfo){
			mstatus= true;
			try {
				waitForElement(LinkTrunkPRI);
				jsClick(LinkTrunkPRI);				
				waitForElementDisappear(elementLoading);
				if(waitForElement(ddSelectTerms)){
					System.out.println("Terms Present");
					}
					new Select(ddSelectTerms).selectByVisibleText(processInfo.terms);
					iClick(btnSave, null, "Click on save button: Process page: SaveButton");
					report.reportDoneEvent("Save Terms", "Terms Saved");
					waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= true;
			}
			return true;
		}
		
		/*Method to save UNI Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public String UNIConfiguration(ProcessInfo processInfo, String Site) throws InterruptedException{			
			String UNINo1=null;
			try {
				waitForElement(LinkUNI1);
				jsClick(LinkUNI1);	
				waitForElementDisappear(elementLoading);
				waitForElement(imgAddressLookup);
				imgAddressLookup.click();
				 waitforPageLoadComplete();
				 if (WaitandSwitchToFrame(frameCondition.get(0))){
					 waitForElement(txtSiteName);
					 txtSiteName.sendKeys(Site);
					 waitForElement(btnSearch);
					 btnSearch.click();
					 waitForElementDisappear(elementLoading);
					 waitForElement(btnRadioSelectSite);
					 btnRadioSelectSite.click();
					 waitForElement(btnOK);
					 btnOK.click();
				 }
				 browser.switchTo().defaultContent();
				 WaitandSwitchToFrame(frameMain);
				 new Select(TransportType).selectByVisibleText(processInfo.UNITransportType1);
				 new Select(ddAggregatorNeeded).selectByVisibleText("No");
				 setSURCLLI(ddtxtSURCILI,processInfo.surCILI);
				 UNINo1 = randomNumber(5);
				 iSendKeys(txtUNInumber, UNINo1);			 
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				UNINo1= null;
			}
			return UNINo1;
		}
		
		
		/*Method to set SURCLLI
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		private boolean setSURCLLI(WebElement ddtxtSURCILI, String surCILI) {
			// TODO Auto-generated method stub
			By by=By.xpath("//li[text()='"+surCILI+"']");
			mstatus=false;
			if (waitForElement(ddtxtSURCILI))
			{
				 ddtxtSURCILI.click();
				 ddtxtSURCILI.clear();	
			do{
				ddtxtSURCILI.sendKeys(surCILI);
				ddtxtSURCILI.sendKeys(Keys.ARROW_DOWN);
				if(isElementPresent(by))
				{
					browser.findElement(by).click();
				}
				sleep(1000);
				ddtxtSURCILI.sendKeys(Keys.TAB);
			}while(ddtxtSURCILI.getAttribute("class").contains("x-form-empty-field"));
			}
			
			return mstatus;
		}
		
		
		/*Method to save BGP Configuration
		 *  
		 * 
		 * 
		 * 
		 */

		public boolean BGPConfiguration(){
			mstatus = true;
			
			 try {
				while(!waitForElement(LinkBGP)){}
				 jsClick(LinkBGP);
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return true;
		}
		
		
		/*Method to save UNI2 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public String UNI2Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
			String UNINo2=null;
			try {
				waitForElement(LinkUNI2);
				jsClick(LinkUNI2);
				waitForElementDisappear(elementLoading);
				waitForElement(imgAddressLookup);
				imgAddressLookup.click();
				 waitforPageLoadComplete();
				 if (WaitandSwitchToFrame(frameCondition.get(1))){
					 waitForElement(txtSiteName);
					 jsSendKeys(txtSiteName,Site);
					 //txtSiteName.sendKeys(Site);
					 waitForElement(btnSearch);
					 btnSearch.click();
					 waitForElementDisappear(elementLoading);
					 waitForElement(btnRadioSelectSite);
					 btnRadioSelectSite.click();
					 waitForElement(btnOK);
					 btnOK.click();
				 }
				 browser.switchTo().defaultContent();
				 WaitandSwitchToFrame(frameMain);
				 new Select(TransportType).selectByVisibleText(processInfo.UNITransportType2);
				 new Select(ddAggregatorNeeded).selectByVisibleText("No");				 
				 setSURCLLI(ddtxtSURCILI,processInfo.surCILI);				 				 
				 UNINo2=randomNumber(5);
				 iSendKeys(txtUNInumber, UNINo2);
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save UNI~2 Configuration", "UNI~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				UNINo2= null;
			}
			return UNINo2;
		}
		
		
		/*Method to save UNI3 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public String  UNI3Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
		String UNINo3;
			try {
				waitForElement(LinkUNI3);
				jsClick(LinkUNI3);
				waitForElementDisappear(elementLoading);
				waitForElement(imgAddressLookup);
				imgAddressLookup.click();
				 waitforPageLoadComplete();
				 if (WaitandSwitchToFrame(frameCondition.get(2))){
					 waitForElement(txtSiteName);
					 jsSendKeys(txtSiteName,Site);
					 //txtSiteName.sendKeys(Site);
					 waitForElement(btnSearch);
					 btnSearch.click();
					 waitForElementDisappear(elementLoading);
					 btnSearch.click();
					 waitForElementDisappear(elementLoading);
					 waitForElement(btnRadioSelectSite);
					 btnRadioSelectSite.click();
					 waitForElement(btnOK);
					 btnOK.click();
				 }
				 browser.switchTo().defaultContent();
				 WaitandSwitchToFrame(frameMain);
				 new Select(TransportType).selectByVisibleText(processInfo.UNITransportType3);
				 new Select(ddAggregatorNeeded).selectByVisibleText("No");
				 setSURCLLI(ddtxtSURCILI,processInfo.surCILI);				 				 
				 UNINo3=randomNumber(5);
				 iSendKeys(txtUNInumber, UNINo3);
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");;
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save UNI~3 Configuration", "UNI~3 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				UNINo3= null;
			}
			return UNINo3;
		}
		
		public String  UNI4Configuration(ProcessInfo processInfo,String SiteName, String siteType) throws InterruptedException{
			String UNINo4;
				try {
					waitForElement(LinkUNI4);
					jsClick(LinkUNI4);
					waitForElementDisappear(elementLoading);
					waitForElement(imgAddressLookup);
					imgAddressLookup.click();
					 waitforPageLoadComplete();
					 if (WaitandSwitchToFrame(frameCondition.get(0))){ //This UNI open first time, so the frame index will be 0
						 waitForElement(txtSiteName);
						 jsSendKeys(txtSiteName,SiteName);
						 waitForElement(btnSearch);
						 btnSearch.click();
						 waitForElementDisappear(elementLoading);
						 btnSearch.click();
						 waitForElementDisappear(elementLoading);
						 waitForElement(btnRadioSelectSite);
						 btnRadioSelectSite.click();
						 waitForElement(btnOK);
						 btnOK.click();
					 }
					 browser.switchTo().defaultContent();
					 WaitandSwitchToFrame(frameMain);
					 new Select(TransportType).selectByVisibleText(siteType);
					 new Select(ddAggregatorNeeded).selectByVisibleText("No");
					 setSURCLLI(ddtxtSURCILI,processInfo.surCILI);			 				 
					 waitForElement(txtUNInumber);				 
					 UNINo4=randomNumber(5);
					 iSendKeys(txtUNInumber, UNINo4);					 
					 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");;
					 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
					 report.reportDoneEvent("Save UNI~4 Configuration", "UNI~4 Configuration Saved");
					 waitForElementDisappear(elementLoading);
				} catch (Exception e) {
					UNINo4= null;
				}
				return UNINo4;
			}
		
		
		/*Method to save UNI Configuration for PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean TrunkPRIConfiguration(ProcessInfo processInfo,String Site,ServiceInfo serviceInfo){
			mstatus= true;
			try{
				this.Trunk_PRI(processInfo);
				this.UNIConfiguration_PRI(processInfo, Site, serviceInfo);
				this.EVCConfiguration_PRI(processInfo);
			}catch (Exception e) {
				mstatus= false;
			}
			return mstatus;
		}
		
		public boolean UNIConfiguration_PRI(ProcessInfo processInfo,String Site,ServiceInfo serviceInfo) throws InterruptedException{
			mstatus= true;
			try {
				waitForElement(LinkUNIPRI);
				jsClick(LinkUNIPRI);
				waitForElementDisappear(elementLoading);
				waitForElement(imgAddressLookup);
				imgAddressLookup.click();
				waitforPageLoadComplete();
				if(serviceInfo.serviceName.equalsIgnoreCase("EDI-PRI")){
					WaitandSwitchToFrame(frameCondition.get(1));
				} else if(serviceInfo.serviceName.equalsIgnoreCase("ENS-PRI")){
					WaitandSwitchToFrame(frameCondition.get(2));
				}
					 waitForElement(txtSiteName);
					 txtSiteName.sendKeys(Site);
					 waitForElement(btnSearch);
					 btnSearch.click();
					 waitForElementDisappear(elementLoading);
					 waitForElement(btnRadioSelectSite);
					 btnRadioSelectSite.click();
					 waitForElement(btnOK);
					 btnOK.click();
				 browser.switchTo().defaultContent();
				 WaitandSwitchToFrame(frameMain);
				 setSURCLLI(ddtxtSURCILI,processInfo.surCILI);
				 waitForElement(txtUNInumber);
				 iSendKeys(txtUNInumber, randomNumber(5));
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			return mstatus;
		}
		
		
		/*Method to save EVC Configuration for PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EVCConfiguration_PRI(ProcessInfo processInfo){
			 mstatus= true;
			 try {
				 waitForElement(LinkEVCPRI);
				 jsClick(LinkEVCPRI);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 txtEVCnumber.sendKeys(randomNumber(5));
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			
		}
		
		
		/*Method to save EVC Configuration for EDI
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public String EVCConfiguration_EDI(ProcessInfo processInfo){
			String EVCNo1=null;
			
			 try {
				 waitForElement(LinkEVC1);
				 jsClick(LinkEVC1);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 EVCNo1=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo1);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddIpBlockChange).selectByVisibleText("No");
				 new Select(ddIpAddressAllocation).selectByVisibleText(processInfo.ipAddressAllocation);
				 new Select(ddAdditionalIpAddress).selectByVisibleText("No");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo1= null;
			}
			 return EVCNo1;
		}
		
		
		/*Method to save EVC Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public String EVCConfiguration_ENS(ProcessInfo processInfo){
			String EVCNo1=null;
			 try {
				 waitForElement(LinkEVC1);
				 jsClick(LinkEVC1);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 new Select(ddExistingEVC).selectByVisibleText("No");
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 EVCNo1=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo1);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxUNIExceeded).selectByVisibleText("No");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo1= null;
			}
			 return EVCNo1;
			 
		}
		
		
		/*Method to save EVC2 Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public String EVC2Configuration_ENS(ProcessInfo processInfo){
			String EVCNo2=null;
			 try {
				 waitForElement(LinkEVC2);
				 jsClick(LinkEVC2);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(1).click();
				 new Select(ddExistingEVC).selectByVisibleText("No");
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 EVCNo2=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo2);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC~2 Configuration", "EVC~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo2= null;
			}
			 return EVCNo2;
			 
		}
		
		
		/*Method to save EVC Configuration for EPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		public String EVCConfiguration_EPL(ProcessInfo processInfo){
			String EVCNo1=null;
			 try {
				 waitForElement(LinkEVC1);
				 jsClick(LinkEVC1);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();            //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(3).click();           //Select 2nd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 EVCNo1=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo1);
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo1= null;
			}
			 return EVCNo1;
		}
		
		
		/*Method to save EVC Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public String EVCConfiguration_EVPL(ProcessInfo processInfo){
			String EVCNo1=null;
			 try {
				 waitForElement(LinkEVC1);
				 jsClick(LinkEVC1);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();              //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(4).click();              //Select 2nd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 EVCNo1=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo1);
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 txtCustomerVLANInfo.sendKeys("2562");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo1= null;
			}
			 return EVCNo1;
		}
		
		
		/*Method to save EVC2 Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public String EVC2Configuration_EVPL(ProcessInfo processInfo){
			String EVCNo2= null;
			 try {
				 waitForElement(LinkEVC2);
				 jsClick(LinkEVC2);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();                 //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(5).click();                 //Select 3rd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 EVCNo2=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo2);
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 txtCustomerVLANInfo.sendKeys("2563");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC~2 Configuration", "EVC~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo2= null;
			}
			 return EVCNo2;
		}
		
		public String EVC3Configuration_EVPL(ProcessInfo processInfo){
			String EVCNo3= null;
			 try {
				 waitForElement(LinkEVC3);
				 clickndRelease(LinkEVC3);
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();                 //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(7).click();                 //Select 4th site at location Z UNI
				 waitForElement(txtEVCnumber);
				 EVCNo3=randomNumber(5);
				 txtEVCnumber.sendKeys(EVCNo3);
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 txtCustomerVLANInfo.sendKeys("2563");
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 report.reportDoneEvent("Save EVC~3 Configuration", "EVC~3 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				EVCNo3= null;
			}
			 return EVCNo3;
		}
		
		
		/*Method to save Equipment fee Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EqFeeConfiguration(ProcessInfo processInfo){
			mstatus= true;
			
			
			 try {
				if(waitForElement(LinkEquipmentFee)){
					jsClick(LinkEquipmentFee);
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(0))){
					 jsClick(LinkEquipmentFeeConfiguration.get(0));
					 waitForElementDisappear(elementLoading);
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(0).click();
				  }
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 report.reportDoneEvent("Save Equipment Fee Configuration", "Equipment Fee Configuration Saved");
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		/*Method to save Equipment fee2 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EqFee2Configuration(ProcessInfo processInfo){
			mstatus= true;
			 try {
				if(waitForElement(LinkEquipmentFee2)){
					jsClick(LinkEquipmentFee2);
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(1))){
					 jsClick(LinkEquipmentFeeConfiguration.get(1));				 
				     waitForElementDisappear(elementLoading);
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(1).click();
				  }
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 report.reportDoneEvent("Save Equipment Fee~2 Configuration", "Equipment Fee~2 Configuration Saved");
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		/*Method to save Equipment fee3 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EqFee3Configuration(ProcessInfo processInfo){
			mstatus= true;
			 try {
				if(waitForElement(LinkEquipmentFee3)){
					jsClick(LinkEquipmentFee3);
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(2))){
					 jsClick(LinkEquipmentFeeConfiguration.get(2));				
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(2).click();
				  }
				 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
				 waitForElementDisappear(elementLoading);
				 report.reportDoneEvent("Save Equipment Fee~3 Configuration", "Equipment Fee~3 Configuration Saved");
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		
		
		
		/*Method to click on Continue button on process page
		 * 
		 * 
		 * 
		 */
		
		
		public boolean ClickOnContinueButton(){
			mstatus= true;
			 try {
				report.updateTestLog("Save Process Configuration", "Process Configuration Saved", Status.SCREENSHOT);
				 if (waitForElement(btnContinue)){
					 iClick(btnContinue, dtCustomerOrderSig, "Click on continue button: Process page: ContinueButton");					 
					 waitforPageLoadComplete();
					 browser.switchTo().defaultContent();
				  }
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}
		
		public boolean UpgradeUNIPortSpeed(int UNINo, String portSpeedToSelect){
			mstatus= true;
			WebElement elementToClick = null;
			//WaitandSwitchToFrame(frameMain);
			switch (UNINo) {			
			case 1:
				elementToClick = LinkUNI1; 
				break;
			case 2:
				elementToClick = LinkUNI2; 
				break;
			case 3:
				elementToClick = LinkUNI3; 
				break;
			case 4:
				elementToClick = LinkUNI4; 
				break;							
			default:
					log.error("UNI Number Not Found. [1-4] is the valid values");
			}
			 try {
				 	if(elementToClick!=null){
					 	 waitForElement(elementToClick);
						 jsClick(elementToClick);
						 waitForElementDisappear(elementLoading);					
						 browser.switchTo().defaultContent();
						 WaitandSwitchToFrame(frameMain);										 
						 new Select(ddUNIPortSpeed).selectByVisibleText(portSpeedToSelect);;
						 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
						 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Updated");
						 log.info("UNI Port Speed upgraded to: " + portSpeedToSelect);
						 waitForElementDisappear(elementLoading);
						// browser.switchTo().defaultContent();
				 	}
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}
		
		public boolean UpgradeEVCBW(int EVCNo, String BasicCosBW){
			mstatus= true;
			WebElement elementToClick = null;
			//WaitandSwitchToFrame(frameMain);
			switch (EVCNo) {
			case 1:
				elementToClick = LinkEVC1; 
				break;
			case 2:
				elementToClick = LinkEVC2; 
				break;
			case 3:
				elementToClick = LinkEVC3;
				break;	
			default:
					log.error("EVC Number Not Found. [1-3] is the valid values");
			}
			 try {
				 	if(elementToClick!=null){
				 		 waitForElement(elementToClick);
						 jsClick(elementToClick);
						 waitForElementDisappear(elementLoading);					
						 browser.switchTo().defaultContent();
						 WaitandSwitchToFrame(frameMain);										 
						 new Select(ddBasicCoSBandwidth).selectByVisibleText(BasicCosBW);						
						 iClick(btnSave, null, "Click on save button: Process page: SaveButton");
						 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
						 log.info("EVC Basic Cos Bandwidth to: " + BasicCosBW);
						 waitForElementDisappear(elementLoading);	
				 	}
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}

		
		
		

}
