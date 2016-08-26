package com.comcast.century.cm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.century.cm.pages.Page;
import com.comcast.century.data.SiteInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.SeleniumReport;
import com.comcast.utils.TestSettings;

public class AddressTabPageCM extends Page {
	
	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";
	
	private boolean mstatus;
	
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
	
	@FindBy(xpath = "//li[text()='arneysmount.nj']")
	private WebElement ddValueHeadendName;
	
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
	
	
	
	
	public boolean ClickAddressTab(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		try{
		  waitforPageLoadComplete();
		  WaitandSwitchToFrame(frameMain,120);
		  waitForElement(tabAddress,120);
		  do{
			  tabAddress.click();		  
		  	}
		  while(!WaitandSwitchToFrame(frameAddress,2));
		  report.reportDoneEvent("Click on Address Tab", "Address Tab Clicked");
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public String EnterSiteDetailsInvalid(SiteInfo siteInfo) throws InterruptedException{
		waitForElement(txtSiteName);
		String siteName = siteInfo.siteName + getTimestamp();
		txtSiteName.sendKeys(siteName);
		report.reportDoneEvent("Enter Site Name", "Entered Site Name as->" + siteName);
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
		return siteName;
			 
	}
	
	public String EnterSiteDetailsValid(SiteInfo siteInfo) throws InterruptedException{
		waitForElement(txtSiteName);
		String siteName = siteInfo.siteName + getTimestamp();
		iSendKeys(txtSiteName,siteName);
		report.reportDoneEvent("Enter Site Name", "Entered Site Name as->" + siteName);
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
		/*while(waitForElement(BtnAddContact,2))
		{
			BtnAddContact.click();
		}*/
		
		 while(!waitForElement(BtnAddContact)){
			 
		 }
		 iClick(BtnAddContact);

	    report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
	    waitforPageLoadComplete();	
		return siteName;
			 
	}
	
	public boolean siteAddressInvalid(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		try{				
			waitForElement(txtSiteAddressLine1);
			String siteAddress1 = siteInfo.siteAddress1 + getTimestamp();
			txtSiteAddressLine1.sendKeys(siteAddress1);
			report.reportDoneEvent("Enter Site Address Line 1", "Entered Site Address Line 1 as->" + siteAddress1);
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
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}
	
     public boolean siteAddressValid(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		try{				
			waitForElement(txtSiteAddressLine1);
			txtSiteAddressLine1.sendKeys(siteInfo.siteAddress1);
			report.reportDoneEvent("Enter Site Address Line 1", "Entered Site Address Line 1 as->" +siteInfo.siteAddress1);
			String siteAddress2 = siteInfo.siteAddress2 + " " + getTimestamp();
			txtSiteAddressLine2.sendKeys(siteAddress2);
			report.reportDoneEvent("Enter Site Address Line 2", "Entered Site Address Line 2 as->" + siteAddress2);
			waitForElement(ddtxtZipCode);
			ddtxtZipCode.sendKeys(siteInfo.zipCode);
			report.reportDoneEvent("Enter Zipcode", "Entered Zipcode as->" +siteInfo.zipCode);
			imgZipcodeSearch.click();
			report.reportDoneEvent("Search for Zipcode", "Zipcode Searched");
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameCondition);
			waitForElement(txtCity);
			while(!iterateThroughtableAndSelectCity(siteInfo.city)){}
			waitForElement(btnOk);
			iClick(btnOk);
			browser.switchTo().defaultContent();
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;			
	}
	
	
	
	public boolean headEndInformation(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		try{
			if( WaitandSwitchToFrame(frameMain)){
				 WaitandSwitchToFrame(frameAddress);
				 waitForElement(ddtxtHeadendName);
				 ddValueSelect(ddtxtHeadendName,ddValueHeadendName,siteInfo.headendName);
//				 ddValue(ddtxtHeadendName,siteInfo.headendName);
//				// WebElement ddValueHeadendName = browser.findElement(By.xpath("//li[text()='"+siteInfo.headendName+"']"));
//				 //arneysmount.nj ddValueHeadendName
//				 waitForElement(ddValueHeadendName);
//				 ddValueHeadendName.click();
				 report.reportDoneEvent("Select Headend Name", "Selected Headend Name as->" +siteInfo.headendName);
				 Thread.sleep(4000);
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean clickCreateButton(){
		mstatus = true;
		try{
			if(waitForElement(btnCreate)){
				iClick(btnCreate);
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean clickAddContact(){
		mstatus= true;
		try{
			 WaitandSwitchToFrame(frameMain);
			 WaitandSwitchToFrame(frameAddress);
			 if(waitForElement(BtnAddContact)){
				 System.out.println("Btn exist");
			 }
			 iClick(BtnAddContact);
			 report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
			 waitforPageLoadComplete();
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
		
	}
	
	public boolean CreateNewAddress(){
		mstatus = true;
		try{
			waitforPageLoadComplete();
			if(WaitandSwitchToFrame(frameMain)){
				WaitandSwitchToFrame(frameAddress);
				while(waitForElement(BtnCreateNewAddress,1))
				{
					iClick(BtnCreateNewAddress);
				}
				 waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;		
	}
	
	public boolean ClickOnCreateNewAdd() throws InterruptedException{
		mstatus = true;
		try{
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
		
		catch(Exception ex)
		{
			mstatus = false;
		}
		return mstatus;
	}
}


		
 
 
		
		

	
	
	


