package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.century.data.SiteInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;

public class AddressTabPageCM extends Page {
	
	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";
	

	public AddressTabPageCM(WebDriver browser, SeleniumReport report) {
		
		super(browser, report);
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
	
	@FindBy(xpath = "//span[text()='Address']")
	private WebElement tabAddress;
	
	@FindBy(xpath = "//*[@id='mainFrame' and contains(@src,'loadCustomerPanel.exc')]")
	private WebElement frameMain;
	
	@FindBy(xpath = "//*[@id='notesframe']")
	private WebElement frameNotes;

	@FindBy(xpath = "//*[@id='AddressFrame' and @src='address.exc']")
	private WebElement frameAddress;
	
	//*[@id='addrSiteBean.siteName']
	
	@FindBy(xpath = "//input[@id='addrSiteBean.siteName']")
	private WebElement txtSiteName;
	
	@FindBy(xpath = "//input[@id='addrSiteBean.address1']")
	private WebElement txtSiteAddressLine1;
	
	
	@FindBy(xpath = "//input[@id='addrSiteBean.address2']")
	private WebElement txtSiteAddressLine2;
	
	//*[@id='CmbSitecity-inputEl']
	
	@FindBy(xpath = "//input[@id='CmbSitecity-inputEl']")
	private WebElement ddtxtCity;
	
	@FindBy(xpath = "//li[text()='Englewood']")
	private WebElement ddValueCity;
	
	//*[@id='CmbSitezipcode-inputEl']
	
	@FindBy(xpath = "//input[@id='CmbSitezipcode-inputEl']")
    private WebElement ddtxtZipCode;
		
	@FindBy(xpath = "//li[text()='80113']")
	private WebElement ddValueZipCode;
	
	@FindBy(xpath = "//img[@id='zipcode_imgSearch']")
	private WebElement imgZipcodeSearch;
	
	@FindBy(xpath = "//span[text()='City']")
	private WebElement txtCity;
	
	@FindBy(xpath = "//input[@id='closeLookup']")
	private WebElement btnOk;
	
	@FindBy(xpath = "//input[@id='CmbSiteregion-inputEl']")
    private WebElement ddtxtRegion;
		
	@FindBy(xpath = "//li[text()='Mile High']")
	private WebElement ddValueRegion;
	
    @FindBy(xpath = "//input[@id='CmbSitedivision-inputEl']")
	private WebElement ddtxtDivision;
			
    @FindBy(xpath = "//li[text()='West']")
    private WebElement ddValueDivision;
		
		
	@FindBy(xpath = "//input[@id='CmbSitemarket-inputEl']")
	private WebElement ddtxtMarket;
			
    @FindBy(xpath = "//li[text()='Mile High']")
    private WebElement ddValueMarket;
    
    @FindBy(xpath = "//input[@id='CmbSitestate-inputEl']")
	private WebElement ddtxtState;
	
	@FindBy(xpath = "//li[text()='Colorado']")
	private WebElement ddValueState;
	
	//*[@id='CmbSiteheadEndName-inputEl']
	
	@FindBy(xpath = "//input[@id='CmbSiteheadEndName-inputEl']")
	private WebElement ddtxtHeadendName;
	
	/*@FindBy(xpath = "//li[text()='arneysmount.nj']")
	private WebElement ddValueHeadendName;*/
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;
	//*[@value='Validate']	
	
	@FindBy(xpath = "//*[@id='create']")
	private WebElement btnCreate;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;
	
	@FindBy(xpath = "//input[@id='diclaimercheck']")
	private WebElement chkDisclaimer;
		
		
	@FindBy(xpath = "//*[@id='codition']")
	private WebElement frameCondition;
		
		
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	@FindBy(xpath = "//*[@id='0']")
	private WebElement btnSelectValidSite;
	
	//*[@id='addcontact-toolEl']
	
	@FindBy(xpath = "//*[@id='addcontact-toolEl']")
	private WebElement BtnAddContact;
	
	//input[@value='Create New Address']
	
	@FindBy(xpath = "//*[@value='Create New Address']")
	private WebElement BtnCreateNewAddress;
	
	@FindBy(xpath = "//*[@id='AddressFrame' and contains(@src,'address.exc']")
	private WebElement frameAddressNext;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(tagName = "iframe")
	private List<WebElement> frameList ;
	
	
	
	
	public void ClickAddressTab(SiteInfo siteInfo) throws InterruptedException{	
		  waitforPageLoadComplete();
		  WaitandSwitchToFrame(frameMain);
		  waitForElement(tabAddress);
		  tabAddress.click();
		  jsClick(tabAddress);
		  report.reportDoneEvent("Click on Address Tab", "Address Tab Clicked");
		  WaitandSwitchToFrame(frameAddress);
	}
	
	public String EnterSiteDetailsInvalid(SiteInfo siteInfo) throws InterruptedException{
		waitForElement(txtSiteName);
		String temp = siteInfo.siteName+RandomNumber();
		txtSiteName.sendKeys(temp);
		report.reportDoneEvent("Enter Site Name", "Entered Site Name as->" +siteInfo.siteName+RandomNumber());
		this.siteAddressInvalid(siteInfo);
		this.headEndInformation(siteInfo);
		this.clickCreateButton();
		waitForElement(btnMore);
		btnMore.click();
		WaitandSwitchToFrame(frameCondition);
		waitForElement(chkDisclaimer);
		chkDisclaimer.click();
	    waitForElement(btnContinue);
		btnContinue.click();
		waitforPageLoadComplete();
		Thread.sleep(3*1000);
		report.updateTestLog("Create Address", "Address Created Successfully", Status.SCREENSHOT);
		browser.switchTo().defaultContent();
		this.clickAddContact();	
		return temp;
			 
	}
	
	public String EnterSiteDetailsValid(SiteInfo siteInfo) throws InterruptedException{
		waitForElement(txtSiteName);
		String temp = siteInfo.siteName+RandomNumber();
		txtSiteName.sendKeys(temp);
		report.reportDoneEvent("Enter Site Name", "Entered Site Name as->" +siteInfo.siteName+RandomNumber());
		this.siteAddressValid(siteInfo);
		this.headEndInformation(siteInfo);
		this.clickCreateButton();
		waitforPageLoadComplete();
		waitForElement(btnSelectValidSite);
		btnSelectValidSite.click();
		waitForElement(btnContinue);
		btnContinue.click();
		waitforPageLoadComplete();
		report.updateTestLog("Create Address", "Address Created Successfully", Status.SCREENSHOT);
		waitForElement(BtnAddContact);
	    BtnAddContact.click();
	    report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
	    waitforPageLoadComplete();	
		return temp;
			 
	}
	
	public void siteAddressInvalid(SiteInfo siteInfo) throws InterruptedException{
		
		waitForElement(txtSiteAddressLine1);
		txtSiteAddressLine1.sendKeys(siteInfo.siteAddress1+RandomNumber());
		report.reportDoneEvent("Enter Site Address Line 1", "Entered Site Address Line 1 as->" +siteInfo.siteAddress1+RandomNumber());
		waitForElement(ddtxtZipCode);
		ddtxtZipCode.sendKeys(siteInfo.zipCode);
		report.reportDoneEvent("Enter Zipcode", "Entered Zipcode as->" +siteInfo.zipCode);
		imgZipcodeSearch.click();
		report.reportDoneEvent("Search for Zipcode", "Zipcode Searched");
		WaitandSwitchToFrame(frameCondition);
		waitForElement(txtCity);
		iterateThroughtableAndSelectCity(siteInfo.city);
		waitForElement(btnOk);
		btnOk.click();
		browser.switchTo().defaultContent();
		
	}
	
     public void siteAddressValid(SiteInfo siteInfo) throws InterruptedException{
		
		waitForElement(txtSiteAddressLine1);
		txtSiteAddressLine1.sendKeys(siteInfo.siteAddress1);
		report.reportDoneEvent("Enter Site Address Line 1", "Entered Site Address Line 1 as->" +siteInfo.siteAddress1);
		txtSiteAddressLine2.sendKeys(siteInfo.siteAddress2+ " "+RandomNumber());
		report.reportDoneEvent("Enter Site Address Line 2", "Entered Site Address Line 2 as->" +siteInfo.siteAddress2+RandomNumber());
		waitForElement(ddtxtZipCode);
		ddtxtZipCode.sendKeys(siteInfo.zipCode);
		report.reportDoneEvent("Enter Zipcode", "Entered Zipcode as->" +siteInfo.zipCode);
		imgZipcodeSearch.click();
		report.reportDoneEvent("Search for Zipcode", "Zipcode Searched");
		waitforPageLoadComplete();
		WaitandSwitchToFrame(frameCondition);
		waitForElement(txtCity);
		iterateThroughtableAndSelectCity(siteInfo.city);
		waitForElement(btnOk);
		btnOk.click();
		browser.switchTo().defaultContent();
		
	}
	
	
	
	public void headEndInformation(SiteInfo siteInfo) throws InterruptedException{
		if( WaitandSwitchToFrame(frameMain)){
			 WaitandSwitchToFrame(frameAddress);
			 waitForElement(ddtxtHeadendName);
			 ddValue(ddtxtHeadendName,siteInfo.headendName);
			 WebElement ddValueHeadendName = browser.findElement(By.xpath("//li[text()='"+siteInfo.headendName+"']"));
			 waitForElement(ddValueHeadendName);
			 ddValueHeadendName.click();
			 report.reportDoneEvent("Select Headend Name", "Selected Headend Name as->" +siteInfo.headendName);
			 Thread.sleep(4000);
		}
	}
	
	public void clickCreateButton(){
		if(waitForElement(btnCreate)){
			btnCreate.click();
			waitforPageLoadComplete();
		}
	}
	
	public void clickAddContact(){
		 WaitandSwitchToFrame(frameMain);
		 WaitandSwitchToFrame(frameAddress);
         if(waitForElement(BtnAddContact)){
        	 System.out.println("Btn exist");
         }
	     BtnAddContact.sendKeys(Keys.ENTER);
	     report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
	     waitforPageLoadComplete();
		
	}
	
	public void CreateNewAddress(){
		waitforPageLoadComplete();
		if(WaitandSwitchToFrame(frameMain)){
			WaitandSwitchToFrame(frameAddress);
			waitForElement(BtnCreateNewAddress);
			BtnCreateNewAddress.click();
			 jsClickWE(BtnCreateNewAddress);
			 waitforPageLoadComplete();
		}
		
	}
	
	public void ClickOnCreateNewAdd() throws InterruptedException{
		waitforPageLoadComplete();		
		int counter;
		WebElement frame = null;
		if(WaitandSwitchToFrame(frameMain)){
			System.out.println("Switched to main frame");
			counter = 0;
			
			do 
			  {
				System.out.println("inside do");
				frame=iterateThroughFramesinsideFrame(BtnCreateNewAddress,frameMain);
				sleep(10*1000);
				counter++;
			 } while (frame==null && counter<=5);
			
		}
		
		if(WaitandSwitchToFrame(frame)){
			if(waitForElement(BtnCreateNewAddress)){
				 clickndRelease(BtnCreateNewAddress);
				 waitforPageLoadComplete();
			 }
			
		}
		else
		{
			System.out.println("Address Frame not Found");
		}	 
	}
	
}


		
 
 
		
		

	
	
	


