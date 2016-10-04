package com.comcast.century.cm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.SiteInfo;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.TestSettings;


public class AddressTabPageCM extends Page {
	
	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";
	
	private boolean mstatus;
	
	Logger log = Logger.getLogger(AddressTabPageCM.class);
	
	public AddressTabPageCM(FrameworkContext context){
		super(context);
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
	
	@FindBy(id="notesframe")
	private WebElement frameNotes;

	@FindBy(xpath = "//*[@id='AddressFrame' and @src='address.exc']")
	private WebElement frameAddress;
	
	@FindBy(id = "addrSiteBean.siteName")
	private WebElement txtSiteName;
	
	@FindBy(id = "addrSiteBean.address1")
	private WebElement txtSiteAddressLine1;
	
	@FindBy(id = "addrSiteBean.address2")
	private WebElement txtSiteAddressLine2;
		
	@FindBy(id = "CmbSitecity-inputEl")
	private WebElement ddtxtCity;
	
	@FindBy(xpath = "//li[text()='Englewood']")
	private WebElement ddValueCity;
	
	@FindBy(id ="CmbSitezipcode-inputEl")
    private WebElement ddtxtZipCode;
		
	@FindBy(xpath = "//li[text()='80113']")
	private WebElement ddValueZipCode;
	
	@FindBy(id = "zipcode_imgSearch")
	private WebElement imgZipcodeSearch;
	
	@FindBy(xpath = "//span[text()='City']")
	private WebElement txtCity;
	
	@FindBy(id = "closeLookup")
	private WebElement btnOk;
	
	@FindBy(xpath = "//input[@id='CmbSiteregion-inputEl']")
    private WebElement ddtxtRegion;
		
	@FindBy(xpath = "//li[text()='Mile High']")
	private WebElement ddValueRegion;
	
    @FindBy(id = "CmbSitedivision-inputEl")
	private WebElement ddtxtDivision;
			
    @FindBy(xpath = "//li[text()='West']")
    private WebElement ddValueDivision;
				
	@FindBy(id = "CmbSitemarket-inputEl")
	private WebElement ddtxtMarket;
			
    @FindBy(xpath = "//li[text()='Mile High']")
    private WebElement ddValueMarket;
    
    @FindBy(id = "CmbSitestate-inputEl")
	private WebElement ddtxtState;
	
	@FindBy(xpath = "//li[text()='Colorado']")
	private WebElement ddValueState;
	
	@FindBy(id = "CmbSiteheadEndName-inputEl")
	private WebElement ddtxtHeadendName;
	
	@FindBy(id = "CmbSiteheadEndCLLI-inputEl")
	private WebElement ddtxtHeadendCLLI;
	
	@FindBy(xpath = "//li[text()='arneysmount.nj']")
	private WebElement ddValueHeadendName;
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;	
	
	@FindBy(id = "create")
	private WebElement btnCreate;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;
	
	@FindBy(id = "diclaimercheck")
	private WebElement chkDisclaimer;
				
	@FindBy(id = "codition")
	private WebElement frameCondition;
				
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	@FindBy(id ="0")
	private WebElement btnSelectValidSite;
	
	@FindBy(id = "addcontact-toolEl")
	private WebElement BtnAddContact;
	
	@FindBy(xpath = "//*[@value='Create New Address']")
	private WebElement BtnCreateNewAddress;
	
	@FindBy(xpath = "//*[@id='AddressFrame' and contains(@src,'address.exc']")
	private WebElement frameAddressNext;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;
	
	@FindBy(tagName = "iframe")
	private List<WebElement> frameList ;
	

	public boolean addSiteAddress() throws InterruptedException
	{
		try{
			 this.ClickAddressTab();
			 browser.switchTo().defaultContent();
			 this.CreateNewAddress();
			 return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			return false;
		}
	 }

	public boolean ClickAddressTab() throws InterruptedException{
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
			log.info(ex.getMessage());
			mstatus = false;
			
		}
		return mstatus;
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
		
		if(waitForElement(btnSelectValidSite,3)){
			btnSelectValidSite.click();
			waitForElement(btnContinue);
			iClick(btnContinue, null, "Select City and continue: Site Address Page: ContinueButton");
		}
		else if(waitForElement(btnMore, 2)) {
			iClick(btnMore, null, "Select City: Site Address Page: MoreButton");		
			WaitandSwitchToFrame(frameCondition);
			waitForElement(chkDisclaimer);
			chkDisclaimer.click();			
			waitForElement(btnContinue);
			iClick(btnContinue, null, "Select City and continue: Site Address Page: ContinueButton");
		}		
		report.updateTestLog("Create Address", "Address Created Successfully", Status.SCREENSHOT);
		browser.switchTo().defaultContent();
		this.clickAddContact();
	    report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
	    waitforPageLoadComplete();	
		return siteName;
			 
	}
	     public boolean siteAddressValid(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		String siteAddress2;
		try{				
			waitForElement(txtSiteAddressLine1);
			txtSiteAddressLine1.sendKeys(siteInfo.siteAddress1);
			report.reportDoneEvent("Enter Site Address Line 1", "Entered Site Address Line 1 as->" +siteInfo.siteAddress1);
			if(siteInfo.TestSetName.equalsIgnoreCase("Local Biller")){
				siteAddress2=siteInfo.siteAddress2;
			}
			else
			{	
				 siteAddress2 = siteInfo.siteAddress2 + " " + getTimestamp();
			}
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
			iClick(btnOk, null, "Select City and click OK: Customer Page: OKButton");
			browser.switchTo().defaultContent();
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;			
	}
	
	
	
	public boolean headEndInformation(SiteInfo siteInfo) throws InterruptedException{
		mstatus = true;
		By by=By.xpath("//li[text()='"+siteInfo.headendName+"']");
		try{
			if( WaitandSwitchToFrame(frameMain)){
				 WaitandSwitchToFrame(frameAddress);
				 scrollDown();				 
					do{
						if(waitForElement(ddtxtHeadendName)){							
							scrollDown();
							ddValueSelect(ddtxtHeadendName,by,siteInfo.headendName);
							Thread.sleep(4000);
						}
						}while(ddtxtHeadendCLLI.getAttribute("class").contains("x-form-empty-field"));
			}				 
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean clickCreateButton(){
		mstatus = true;
		try{
			if(waitForElement(btnCreate)){
				iClick(btnCreate, null, "Create Address: Customer Page: CreateButton");
				waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;
	}
	
	public boolean clickAddContact(){
		mstatus= true;
		try{
			scrollDown();
			 WaitandSwitchToFrame(frameMain);
			 WaitandSwitchToFrame(frameAddress);
			 if(waitForElement(BtnAddContact)){
				 log.info("Add Contact Button exist");
			 }
			 BtnAddContact.click();
			 /**
			  * Here Keys.ENTER is not working in IE. So we are using normal click for all the browsers.
			  * Updated by Kesavan on 06th Sep 2016			  
			    iClick(BtnAddContact, null, "Add Contact: ContactPage: AddContactButton");
			 */
			 report.reportDoneEvent("Click on Add Contact", "Add Contact Clicked");
			 waitforPageLoadComplete();
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
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
					iClick(BtnCreateNewAddress, null, "Create New Address: AddressPage: CreateNewAddressButton");					
				}
				 waitforPageLoadComplete();
			}
		}
		catch(Exception ex)
		{
			log.info(ex.getMessage());
			mstatus = false;
		}
		return mstatus;		
	}
	

}


		
 
 
		
		

	
	
	


