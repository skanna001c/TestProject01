package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ProcessTabPageCM extends Page {

	public ProcessTabPageCM(WebDriver browser, SeleniumReport report) {
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
	
	
		@FindBy(xpath = "//select[@paramname='Terms']")
		private WebElement ddSelectTerms;
		
		@FindBy(xpath = "//input[@value='Save']")
		private WebElement btnSave;
		
		@FindBy(xpath = "//a[text()='UNI']")
		private WebElement LinkUNI;
		
		@FindBy(xpath = "//a[text()='UNI~2']")
		private WebElement LinkUNI2;
		
		@FindBy(xpath = "//a[text()='UNI~3']")
		private WebElement LinkUNI3;
		
		@FindBy(xpath = "//a[text()='BGP']")
		private WebElement LinkBGP;
		
		@FindBy(xpath = "//a[text()='Trunk-PRI']")
		private WebElement LinkTrunkPRI;
	
		@FindBy(xpath = "//select[@paramname='BGP Change?']")
		private WebElement ddBGPChange;
		
		@FindBy(xpath = "//a[text()='EVC']")
		private WebElement LinkEVC;
		
		@FindBy(xpath = "//a[text()='EVC']")
		private List<WebElement> LinkEVCPRI;
		
		@FindBy(xpath = "//a[text()='EVC~2']")
		private WebElement LinkEVC2;
		
		@FindBy(xpath = "//a[text()='Equipment Fee']")
		private WebElement LinkEquipmentFee;
		
		@FindBy(xpath = "//a[text()='Equipment Fee~2']")
		private WebElement LinkEquipmentFee2;
		
		@FindBy(xpath = "//a[text()='Equipment Fee~3']")
		private WebElement LinkEquipmentFee3;
		
		@FindBy(xpath = "//a[text()='Equipment Fee Configuration']")
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
		
		@FindBy(xpath = "//div[contains(text(),'ProdTest_ENT')]")
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
		
		@FindBy(xpath = "//div[contains(text(),'ProdTest_ENT')]")
		private List<WebElement> ddValueEqFeeServiceLocation;
		
		@FindBy(xpath = "//input[@value='Continue']")
		private WebElement btnContinue;
	
		@FindBy(xpath = "//span[text()='No']/following-sibling::*")
		private WebElement BtnEqFeePopUp;
		
		@FindBy(xpath = "//div[text()='loading...']")
		private WebElement elementLoading ;
		
		@FindBy(xpath = "//b[text()='Service Request ID :']/../following-sibling::*[position()=1]")
		private WebElement txtSRId ;
		
		private boolean mstatus;
		
		
		public IDataDump processServices(ServiceInfo serviceInfo,ProcessInfo processInfo, IDataDump iDataDump){
			
			IDataDump localiDataDump=iDataDump;
			try{
				switch(serviceInfo.serviceName)
				{
				case "EDI" :
					this.UNIConfiguration(processInfo,iDataDump.getValue("SITE1_RT"));
					iDataDump=SetSite(processInfo.UNITransportType1,iDataDump.getValue("SITE1_RT"),iDataDump);
					this.EVCConfiguration_EDI(processInfo);
					break;
				case "EPL" :
					this.UNIConfiguration(processInfo,localiDataDump.getValue("SITE1_RT") );
					localiDataDump=SetSite(processInfo.UNITransportType1,localiDataDump.getValue("SITE1_RT"),localiDataDump);
					this.UNI2Configuration(processInfo,localiDataDump.getValue("SITE2_RT"));
					localiDataDump=SetSite(processInfo.UNITransportType2,localiDataDump.getValue("SITE2_RT"),localiDataDump);
					this.EVCConfiguration_EPL(processInfo);
					break;
				case "ENS" :
					this.UNIConfiguration(processInfo,iDataDump.getValue("SITE1_RT") );
					iDataDump=SetSite(processInfo.UNITransportType1,iDataDump.getValue("SITE1_RT"),iDataDump);
					this.UNI2Configuration(processInfo, iDataDump.getValue("SITE2_RT"));
					iDataDump=SetSite(processInfo.UNITransportType2,iDataDump.getValue("SITE2_RT"),iDataDump);
					this.EVCConfiguration_ENS(processInfo);
					this.EVC2Configuration_ENS(processInfo);
					break;
				case "EVPL" :
					this.UNIConfiguration(processInfo,iDataDump.getValue("SITE1_RT") );
					iDataDump=SetSite(processInfo.UNITransportType1,iDataDump.getValue("SITE1_RT"),iDataDump);
					this.UNI2Configuration(processInfo,iDataDump.getValue("SITE2_RT"));
					iDataDump.setValue(processInfo.UNITransportType2+"SITE2_RT", iDataDump.getValue("SITE2_RT"));
					this.UNI3Configuration(processInfo,iDataDump.getValue("SITE3_RT"));
					iDataDump.setValue(processInfo.UNITransportType3+"SITE3_RT", iDataDump.getValue("SITE3_RT"));
					this.EVCConfiguration_EVPL(processInfo);
					this.EVC2Configuration_EVPL(processInfo);
					break;
				default :
					System.out.println("Invalid Service");
				}
				
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
		
		public String ProcessConfiguration(ProcessInfo processInfo) throws InterruptedException{
			String SRID;
			SRID = txtSRId.getText();
			if(waitForElement(ddSelectTerms)){
			System.out.println("Terms Present");
			}
			new Select(ddSelectTerms).selectByVisibleText(processInfo.terms);
			btnSave.click();
			report.reportDoneEvent("Save Terms", "Terms Saved");
			waitForElementDisappear(elementLoading);
			return SRID;
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
				LinkTrunkPRI.click();
				waitForElementDisappear(elementLoading);
				if(waitForElement(ddSelectTerms)){
					System.out.println("Terms Present");
					}
					new Select(ddSelectTerms).selectByVisibleText(processInfo.terms);
					btnSave.click();
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
		
		public boolean UNIConfiguration(ProcessInfo processInfo, String Site) throws InterruptedException{
			mstatus= true;
			
			try {
				waitForElement(LinkUNI);
				LinkUNI.click();
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
				 waitForElement(ddtxtSURCILI);
				 ddtxtSURCILI.clear();
				 ddtxtSURCILI.click();	 
				 ddtxtSURCILI.sendKeys(processInfo.surCILI1);		 
				 //Thread.sleep(10000);
				/* ddValue(ddtxtSURCILI,processInfo.surCILI1);			 
				 WebElement ddvalueSURCILI1 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI1+"']"));
				 waitForElement(ddvalueSURCILI1);
				 ddvalueSURCILI1.click();*/
				 waitForElement(txtUNInumber);
				 txtUNInumber.clear();
				 txtUNInumber.sendKeys(randomNumber(5));
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 btnSave.click();
				 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
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
				waitForElement(LinkBGP);
				 LinkBGP.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 btnSave.click();
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
		
		public boolean UNI2Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
			mstatus= true;
			
			try {
				waitForElement(LinkUNI2);
				LinkUNI2.click();
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
				 waitForElement(ddtxtSURCILI);
				 ddValue(ddtxtSURCILI,processInfo.surCILI2);
				 WebElement ddvalueSURCILI2 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI2+"']"));
				 waitForElement(ddvalueSURCILI2);
				 ddvalueSURCILI2.click();
				 waitForElement(txtUNInumber);
				 txtUNInumber.sendKeys(randomNumber(5));
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 btnSave.click();
				 report.reportDoneEvent("Save UNI~2 Configuration", "UNI~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			return mstatus;
		}
		
		
		/*Method to save UNI3 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public boolean UNI3Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
			mstatus= true;
			try {
				waitForElement(LinkUNI3);
				LinkUNI3.click();
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
				 waitForElement(ddtxtSURCILI);
				 ddValue(ddtxtSURCILI,processInfo.surCILI3);
				 WebElement ddvalueSURCILI3 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI3+"']"));
				 waitForElement(ddvalueSURCILI3);
				 ddvalueSURCILI3.click();
				 waitForElement(txtUNInumber);
				 txtUNInumber.sendKeys(randomNumber(5));
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");;
				 btnSave.click();
				 report.reportDoneEvent("Save UNI~3 Configuration", "UNI~3 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			return mstatus;
		}
		
		
		/*Method to save UNI Configuration for PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean UNIConfiguration_PRI(ProcessInfo processInfo,String Site) throws InterruptedException{
			mstatus= true;
			try {
				waitForElement(LinkUNI);
				LinkUNI.click();
				waitForElementDisappear(elementLoading);
				waitForElement(imgAddressLookup);
				imgAddressLookup.click();
				 waitforPageLoadComplete();
				 if (WaitandSwitchToFrame(frameCondition.get(1))){
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
				 waitForElement(ddtxtSURCILI);
				 ddValue(ddtxtSURCILI,processInfo.surCILI1);
				 WebElement ddvalueSURCILI1 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI1+"']"));
				 waitForElement(ddvalueSURCILI1);
				 ddvalueSURCILI1.click();
				 waitForElement(txtUNInumber);
				 txtUNInumber.sendKeys(randomNumber(5));
				 new Select(ddUNIPortSpeed).selectByVisibleText("10/100");
				 btnSave.click();
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
				waitForElement(LinkEVCPRI.get(1));
				 LinkEVCPRI.get(1).click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 txtEVCnumber.sendKeys(randomNumber(5));
				 btnSave.click();
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
		
		
		public boolean EVCConfiguration_EDI(ProcessInfo processInfo){
			 mstatus = true;
			
			 try {
				waitForElement(LinkEVC);
				 LinkEVC.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 txtEVCnumber.sendKeys(randomNumber(5));
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddIpBlockChange).selectByVisibleText("No");
				 new Select(ddIpAddressAllocation).selectByVisibleText(processInfo.ipAddressAllocation);
				 new Select(ddAdditionalIpAddress).selectByVisibleText("No");
				 btnSave.click();
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		/*Method to save EVC Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EVCConfiguration_ENS(ProcessInfo processInfo){
			mstatus= true;
			 try {
				waitForElement(LinkEVC);
				 LinkEVC.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(0).click();
				 new Select(ddExistingEVC).selectByVisibleText("No");
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 txtEVCnumber.sendKeys(randomNumber(5));
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxUNIExceeded).selectByVisibleText("No");
				 btnSave.click();
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}
		
		
		/*Method to save EVC2 Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EVC2Configuration_ENS(ProcessInfo processInfo){
			mstatus= true;
			 try {
				waitForElement(LinkEVC2);
				 LinkEVC2.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(1).click();
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 btnSave.click();
				 report.reportDoneEvent("Save EVC~2 Configuration", "EVC~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}
		
		
		/*Method to save EVC Configuration for EPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		public boolean EVCConfiguration_EPL(ProcessInfo processInfo){
			mstatus= true;
			 try {
				waitForElement(LinkEVC);
				 LinkEVC.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();            //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(3).click();           //Select 2nd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 txtEVCnumber.sendKeys(randomNumber(5));
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 btnSave.click();
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		/*Method to save EVC Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public boolean EVCConfiguration_EVPL(ProcessInfo processInfo){
			mstatus= true;
			 try {
				waitForElement(LinkEVC);
				 LinkEVC.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();              //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(4).click();              //Select 2nd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 txtEVCnumber.sendKeys(randomNumber(5));
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 txtCustomerVLANInfo.sendKeys(processInfo.customerVLANInfo);
				 btnSave.click();
				 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
		}
		
		
		/*Method to save EVC2 Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public boolean EVC2Configuration_EVPL(ProcessInfo processInfo){
			mstatus= true;
			 try {
				waitForElement(LinkEVC2);
				 LinkEVC2.click();
				 waitForElementDisappear(elementLoading);
				 waitForElement(ddArrwLocationAuni);
				 ddArrwLocationAuni.click();
				 ddvalueLocationZuni.get(0).click();                 //Select 1st site at location A UNI
				 waitForElement(ddArrwLocationZuni);
				 ddArrwLocationZuni.click();
				 ddvalueLocationZuni.get(5).click();                 //Select 3rd site at location Z UNI
				 waitForElement(txtEVCnumber);
				 txtEVCnumber.sendKeys(randomNumber(5));
				 new Select(ddEVCAreaType).selectByVisibleText(processInfo.evcAreaType);
				 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
				 new Select(ddMaxEVCExceeded).selectByVisibleText("No");
				 txtCustomerVLANInfo.sendKeys(processInfo.customerVLANInfo);
				 btnSave.click();
				 report.reportDoneEvent("Save EVC~2 Configuration", "EVC~2 Configuration Saved");
				 waitForElementDisappear(elementLoading);
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
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
				 LinkEquipmentFee.click();
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 btnSave.click();
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(0))){
				 LinkEquipmentFeeConfiguration.get(0).click();
				 waitForElementDisappear(elementLoading);
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(0).click();
				  }
				 btnSave.click();
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
				 LinkEquipmentFee2.click();
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 btnSave.click();
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(1))){
				 LinkEquipmentFeeConfiguration.get(1).click();
				 waitForElementDisappear(elementLoading);
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(1).click();
				  }
				 btnSave.click();
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
				 LinkEquipmentFee3.click();
				 }
				 waitForElementDisappear(elementLoading);
				 waitForElement(btnSave);
				 btnSave.click();
				 waitForElementDisappear(elementLoading);
				 
				 if (waitForElement(LinkEquipmentFeeConfiguration.get(2))){
				 LinkEquipmentFeeConfiguration.get(2).click();
				 waitForElementDisappear(elementLoading);
				 }
				 if (waitForElement(ddArrwEqFeeServiceLocation)){
					 ddArrwEqFeeServiceLocation.click();
					 ddValueEqFeeServiceLocation.get(2).click();
				  }
				 btnSave.click();
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
					 btnContinue.click();
					 waitforPageLoadComplete();
					 browser.switchTo().defaultContent();
				  }
			} catch (Exception e) {
				mstatus= false;
			}
			 return mstatus;
			 
		}
	

}
