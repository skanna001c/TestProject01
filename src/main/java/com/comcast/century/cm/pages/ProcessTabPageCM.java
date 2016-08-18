package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.comcast.century.data.ProcessInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;

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
			new Select(ddSelectTerms).selectByValue(processInfo.terms);
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
		
		public void Trunk_PRI(ProcessInfo processInfo){
			waitForElement(LinkTrunkPRI);
			LinkTrunkPRI.click();
			waitForElementDisappear(elementLoading);
			if(waitForElement(ddSelectTerms)){
				System.out.println("Terms Present");
				}
				new Select(ddSelectTerms).selectByValue(processInfo.terms);
				btnSave.click();
				report.reportDoneEvent("Save Terms", "Terms Saved");
				waitForElementDisappear(elementLoading);
		}
		
		/*Method to save UNI Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void UNIConfiguration(ProcessInfo processInfo, String Site) throws InterruptedException{
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
			 new Select(ddAggregatorNeeded).selectByValue("No");
			 waitForElement(ddtxtSURCILI);
			 ddtxtSURCILI.clear();
			 ddtxtSURCILI.sendKeys(processInfo.surCILI1);
			 //Thread.sleep(10000);
			/* ddValue(ddtxtSURCILI,processInfo.surCILI1);			 
			 WebElement ddvalueSURCILI1 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI1+"']"));
			 waitForElement(ddvalueSURCILI1);
			 ddvalueSURCILI1.click();*/
			 waitForElement(txtUNInumber);
			 txtUNInumber.clear();
			 txtUNInumber.sendKeys(randomNumber(5));
			 new Select(ddUNIPortSpeed).selectByIndex(1);
			 btnSave.click();
			 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Saved");
			 waitForElementDisappear(elementLoading);
		}
		
		
		/*Method to save BGP Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public void BGPConfiguration(){
			 waitForElement(LinkBGP);
			 LinkBGP.click();
			 waitForElementDisappear(elementLoading);
			 waitForElement(btnSave);
			 btnSave.click();
			 waitForElementDisappear(elementLoading);
		}
		
		
		/*Method to save UNI2 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void UNI2Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
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
			 new Select(ddAggregatorNeeded).selectByValue("No");
			 waitForElement(ddtxtSURCILI);
			 ddValue(ddtxtSURCILI,processInfo.surCILI2);
			 WebElement ddvalueSURCILI2 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI2+"']"));
			 waitForElement(ddvalueSURCILI2);
			 ddvalueSURCILI2.click();
			 waitForElement(txtUNInumber);
			 txtUNInumber.sendKeys(randomNumber(5));
			 new Select(ddUNIPortSpeed).selectByIndex(1);
			 btnSave.click();
			 report.reportDoneEvent("Save UNI~2 Configuration", "UNI~2 Configuration Saved");
			 waitForElementDisappear(elementLoading);
		}
		
		
		/*Method to save UNI3 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public void UNI3Configuration(ProcessInfo processInfo,String Site) throws InterruptedException{
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
			 new Select(ddAggregatorNeeded).selectByIndex(1);
			 waitForElement(ddtxtSURCILI);
			 ddValue(ddtxtSURCILI,processInfo.surCILI3);
			 WebElement ddvalueSURCILI3 = browser.findElement(By.xpath("//li[text()='"+processInfo.surCILI3+"']"));
			 waitForElement(ddvalueSURCILI3);
			 ddvalueSURCILI3.click();
			 waitForElement(txtUNInumber);
			 txtUNInumber.sendKeys(randomNumber(5));
			 new Select(ddUNIPortSpeed).selectByValue("No");;
			 btnSave.click();
			 report.reportDoneEvent("Save UNI~3 Configuration", "UNI~3 Configuration Saved");
			 waitForElementDisappear(elementLoading);
		}
		
		
		/*Method to save UNI Configuration for PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void UNIConfiguration_PRI(ProcessInfo processInfo,String Site) throws InterruptedException{
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
			 new Select(ddUNIPortSpeed).selectByIndex(1);
			 btnSave.click();
			 report.reportDoneEvent("Save UNI Configuration", "UNI Configuration Saved");
			 waitForElementDisappear(elementLoading);
		}
		
		
		/*Method to save EVC Configuration for PRI
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EVCConfiguration_PRI(ProcessInfo processInfo){
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
			
		}
		
		
		/*Method to save EVC Configuration for EDI
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public void EVCConfiguration_EDI(ProcessInfo processInfo){
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
		}
		
		
		/*Method to save EVC Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EVCConfiguration_ENS(ProcessInfo processInfo){
			 waitForElement(LinkEVC);
			 LinkEVC.click();
			 waitForElementDisappear(elementLoading);
			 waitForElement(ddArrwLocationZuni);
			 ddArrwLocationZuni.click();
			 ddvalueLocationZuni.get(0).click();
			 new Select(ddExistingEVC).selectByValue("No");
			 new Select(ddEVCAreaType).selectByValue(processInfo.evcAreaType);
			 txtEVCnumber.sendKeys(randomNumber(5));
			 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
			 new Select(ddMaxUNIExceeded).selectByValue("No");
			 btnSave.click();
			 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
			 waitForElementDisappear(elementLoading);
			 
		}
		
		
		/*Method to save EVC2 Configuration for ENS
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EVC2Configuration_ENS(ProcessInfo processInfo){
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
			 
		}
		
		
		/*Method to save EVC Configuration for EPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EVCConfiguration_EPL(ProcessInfo processInfo){
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
			 new Select(ddEVCAreaType).selectByValue(processInfo.evcAreaType);
			 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
			 new Select(ddMaxEVCExceeded).selectByValue("No");
			 btnSave.click();
			 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
			 waitForElementDisappear(elementLoading); 
		}
		
		
		/*Method to save EVC Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public void EVCConfiguration_EVPL(ProcessInfo processInfo){
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
			 new Select(ddEVCAreaType).selectByValue(processInfo.evcAreaType);
			 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
			 new Select(ddMaxEVCExceeded).selectByValue("No");
			 txtCustomerVLANInfo.sendKeys(processInfo.customerVLANInfo);
			 btnSave.click();
			 report.reportDoneEvent("Save EVC Configuration", "EVC Configuration Saved");
			 waitForElementDisappear(elementLoading); 
		}
		
		
		/*Method to save EVC2 Configuration for EVPL
		 *  
		 * 
		 * 
		 * 
		 */
		
		
		public void EVC2Configuration_EVPL(ProcessInfo processInfo){
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
			 new Select(ddEVCAreaType).selectByValue(processInfo.evcAreaType);
			 new Select(ddBasicCoSBandwidth).selectByVisibleText(processInfo.basicCosBandwidth);
			 new Select(ddMaxEVCExceeded).selectByValue("No");
			 txtCustomerVLANInfo.sendKeys(processInfo.customerVLANInfo);
			 btnSave.click();
			 report.reportDoneEvent("Save EVC~2 Configuration", "EVC~2 Configuration Saved");
			 waitForElementDisappear(elementLoading); 
		}
		
		
		/*Method to save Equipment fee Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EqFeeConfiguration(ProcessInfo processInfo){
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
		}
		
		
		/*Method to save Equipment fee2 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EqFee2Configuration(ProcessInfo processInfo){
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
		}
		
		
		/*Method to save Equipment fee3 Configuration
		 *  
		 * 
		 * 
		 * 
		 */
		
		public void EqFee3Configuration(ProcessInfo processInfo){
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
		}
		
		
		
		
		
		/*Method to click on Continue button on process page
		 * 
		 * 
		 * 
		 */
		
		
		public void ClickOnContinueButton(){
			 report.updateTestLog("Save Process Configuration", "Process Configuration Saved", Status.SCREENSHOT);
			 if (waitForElement(btnContinue)){
				 btnContinue.click();
				 waitforPageLoadComplete();
			  }
			
			 
		}
	

}
