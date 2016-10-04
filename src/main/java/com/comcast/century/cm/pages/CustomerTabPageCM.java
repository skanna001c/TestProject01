package com.comcast.century.cm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.comcast.century.data.CustomerInfo;
import com.comcast.century.data.SupplementInfo;
import com.comcast.reporting.SeleniumReport;
import com.comcast.reporting.Status;
import com.comcast.utils.ComcastTestMain.FrameworkContext;
import com.comcast.utils.Page;
import com.comcast.utils.PerfTransaction;
import com.comcast.utils.TestSettings;

public class CustomerTabPageCM extends Page {

	private TestSettings testSettings;

	protected static String HOME_PAGE_TITLE_QA = "Einstein 360°";

	protected static String HOME_PAGE_TITLE_STG = "Comcast Business | My Online Account";

	protected static String HOME_PAGE_TITLE_SCH = "Search";

	public CustomerTabPageCM(WebDriver browser, SeleniumReport report) {
		super(browser, report);
	}
	
	public CustomerTabPageCM(FrameworkContext context){
		super(context);
	}
	
	private String customerName;
	
	@FindBy(css = "#Customer")
	private WebElement tabCustomer;	
	
	@FindBy(xpath=".//*[@class='x-tab-strip-text' and .='Customer']")
	private WebElement subtabCustomer;
	
	@FindBy(id = "mainFrame")
	private WebElement frameMain;
	
	@FindBy(id = "leftFrame")
	private WebElement frameLeft;
	
	@FindBy(xpath = "//*[@class[contains(.,'expand')]]")
	private WebElement btnExpand ;

	@FindBy(id = "CustomerFrame")
	private WebElement frameCustomer;
	
	@FindBy(id = "busiCustBean.businessName")
	private WebElement txtCustomerName;
	
	@FindBy(id = "busiCustBean.busiWorkPhone1")
	private WebElement txtworkPhone;
	
	@FindBy(id = "sfAccountID")
	private WebElement txtSalesForceID;	

	@FindBy(id = "addrBean.addrGDBean.addrsLine1")
	private WebElement txtAddressLine1;
	
	@FindBy(id = "addrBean.addrGDBean.addrsLine2")
	private WebElement txtAddressLine2;

	@FindBy(id = "xt-gen1031")
	private WebElement ddArrwState;
	
	@FindBy(id = "CmbGDstate-inputEl")
	private WebElement ddtxtState;
	
	@FindBy(xpath = "//li[text()='Alaska']")
	private WebElement ddValueState;

	@FindBy(id = "ext-gen1036")
	private WebElement ddArrwCity;
	
	@FindBy(id = "CmbGDcity-inputEl")
	private WebElement ddtxtCity;
	
	@FindBy(xpath = "//li[text()='Akiak']")
	private WebElement ddValueCity;

	@FindBy(id = "ext-gen1041")
	private WebElement ddArrwZipCode;
	
	@FindBy(id = "CmbGDzipcode-inputEl")
    private WebElement ddtxtZipCode;
		
	@FindBy(xpath = "//li[text()='99552']")
	private WebElement ddValueZipCode;
	
	@FindBy(id = "zipcode_imgSearch")
	private WebElement imgZipcodeSearch;
	
	@FindBy(xpath = "//span[text()='City']")
	private WebElement txtCity;
	
	@FindBy(id = "closeLookup")
	private WebElement btnOk;

	@FindBy(id = "CmbGDdivision-inputEl")
    private WebElement ddtxtDivision;
		
	@FindBy(xpath = "//li[text()='West']")
	private WebElement ddValueDivision;
	
	@FindBy(id = "CmbGDmarket-inputEl")
    private WebElement ddtxtMarket;
		
	@FindBy(xpath = "//li[text()='Seattle']")
	private WebElement ddValueMarket;
	
	@FindBy(id = "CmbGDregion-inputEl")
    private WebElement ddtxtRegion;
		
	@FindBy(xpath = "//li[text()='Seattle'][2]")
	private WebElement ddValueRegion;
	
	@FindBy(xpath = "//input[@value='Validate']")
	private WebElement btnValidate;
	
	@FindBy(xpath = "//input[@value='Create']")
	private WebElement btnCreate;
	
	@FindBy(id = "0")
	private WebElement btnSelectValidSite;
	
	@FindBy(xpath = "//input[@value='More']")
	private WebElement btnMore;

	@FindBy(id = "diclaimercheck")
	private WebElement chkDisclaimer;

	@FindBy(id = "codition")
	private WebElement frameCondition;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement btnContinue;
	
	@FindBy(xpath = "//span[.='Order']")
	private WebElement tabOrder;
	
	@FindBy(xpath = "//tr[contains(@title,'In-Progress')]/child::td[@class='standartTreeImage']/child::img[contains(@src,'images/csh_bluebooks/plus')]")
	private WebElement plusButtonInProgress;
	
	@FindBy(xpath = "//tr[contains(@title,'Completed')]/child::td[@class='standartTreeImage']/child::img[contains(@src,'images/csh_bluebooks/plus')]")
	private WebElement plusButtonCompleted;
	
	@FindBy(xpath = "//span[contains(.,'SR ID')]")
	private WebElement linkSRID;
	
	@FindBy(xpath = "//div[text()='loading...']")
	private WebElement elementLoading ;	
	
	
	private boolean mstatus;	
	
	Logger log = Logger.getLogger(CustomerTabPageCM.class);
	
	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_QA)
				|| (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_STG))
				|| (browser.getTitle().trim().equalsIgnoreCase(HOME_PAGE_TITLE_SCH))) {
			return true;
		}
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		
		
	}
	
	  public String customerInformation(CustomerInfo customerInfo){		  
		  waitforPageLoadComplete();
		  do{
			  browser.switchTo().defaultContent();
			  if(waitForElement(tabCustomer,1)){
				  doubleClick(tabCustomer);
				  waitforPageLoadComplete();
			  }
			  WaitandSwitchToFrame(frameMain);
		  }while(!WaitandSwitchToFrame(frameCustomer,1));		  
		  report.reportDoneEvent("Click on Customer Tab", "Customer Tab is present");
		  waitforPageLoadComplete();
		  waitForElement(txtCustomerName);
		 customerName = customerInfo.customerName + getTimestamp();
		 do 
		 {    txtCustomerName.click();
			  txtCustomerName.clear();
			  //updated by harsh to store runtime data into datadump - 8/5/2016			  
			  txtCustomerName.sendKeys(customerName);
		 }
		 while(txtCustomerName.getAttribute("value").length()<1);
		 log.info(" Customer Name is: " + txtCustomerName.getAttribute("value"));		  		  
		  waitforPageLoadComplete();
		  report.reportDoneEvent("Enter Customer Name", "Entered Customer Name as ->" + customerName);
		  if(waitForElement(txtworkPhone)){
			  txtworkPhone.click();
		  }
		  txtworkPhone.sendKeys(customerInfo.workPhone);
		  report.reportDoneEvent("Enter Work Phone", "Work Phone Entered as ->" +customerInfo.workPhone);
		  String salesForceID = customerInfo.salesForceAccId+randomNumber(5);
		  txtSalesForceID.sendKeys(salesForceID);
		  report.reportDoneEvent("Enter Salesforce Account ID", "Salesforce Account ID Entered as ->" + salesForceID);  
		  return customerName;

	  }
	  	  
	  public boolean addressInformationValid(CustomerInfo customerInfo) throws InterruptedException{
		  mstatus = true;
		  String addressLine2;
		  try {
			waitForElement(txtAddressLine1);
			  txtAddressLine1.sendKeys(customerInfo.addressLine1);
			  report.reportDoneEvent("Enter Address Line 1", "Address Line 1 Entered as ->" +customerInfo.addressLine1);
			  if(customerInfo.TestSetName.equalsIgnoreCase("Local Biller")){
				  addressLine2=customerInfo.addressLine2;
				}
				else
				{	
					addressLine2=customerInfo.addressLine2 + " " + getTimestamp();
				}
			 
			  txtAddressLine2.sendKeys(addressLine2);
			  report.reportDoneEvent("Enter Address Line 2", "Address Line 2 Entered as ->" + addressLine2);
			  ddtxtZipCode.sendKeys(customerInfo.zipCode);
			  report.reportDoneEvent("Enter Zipcode", "Zipcode Entered as ->" +customerInfo.zipCode);
			  imgZipcodeSearch.click();
			  waitforPageLoadComplete();
			  while(!WaitandSwitchToFrame(frameCondition)){}			  
			  while(!waitForElement(txtCity)){}
			  while(!iterateThroughtableAndSelectCity(customerInfo.city)){};
			  btnOk.sendKeys(Keys.ENTER);
			  browser.switchTo().defaultContent();
			  if(WaitandSwitchToFrame(frameMain)){
				  WaitandSwitchToFrame(frameCustomer);
				  }
			  waitForElement(btnCreate);
			  iClick(btnCreate,null,"CreateCustomer: Customer Page: CreateButton");//updated by harsh on 9/2/2016
			  
			  if(waitForElement(btnSelectValidSite,3)){
					btnSelectValidSite.click();
					waitForElement(btnContinue);
					iClick(btnContinue,null,"CreateCustomer: Address Verification: Continue_Button");
				}
				else if(waitForElement(btnMore, 2)) {
					iClick(btnMore, null, "Select City: Customer Page: MoreButton");		
					WaitandSwitchToFrame(frameCondition);
					waitForElement(chkDisclaimer);
					chkDisclaimer.click();		
					waitForElement(btnContinue);
					iClick(btnContinue,null,"CreateCustomer: Address Verification: Continue_Button");
				} 		  
			  browser.switchTo().defaultContent();
			  report.updateTestLog("Create Customer", "Customer Created Successfully", Status.SCREENSHOT);
		} catch (Exception e) {
			mstatus = false;
			e.printStackTrace();
		}
		return mstatus;
	  }
	  
	  
	  /**
		 *Creates Customer
		 *
		 **/
	  @PerfTransaction(name="CreateCustomer")
	  public String createCustomer(CustomerInfo customerInfo) throws InterruptedException{
		  String custName = customerInformation(customerInfo);
		  this.addressInformationValid(customerInfo);
		  return custName;
		  
	  }
	  
	public boolean clickSRIDInOrderHierarchy(String custName, SupplementInfo supplementInfo) {
		mstatus = true;
		String xpath = "//tr[@title='" + custName + "']/child::td[@class='standartTreeImage']/child::"
				+ "img[contains(@src,'images/csh_bluebooks/plus')]";
		By by=By.xpath("//*[@class[contains(.,'expand')]]");
					  
		try {
			do {
				waitUntilElementPresent(by, 60);
				if (isElementPresent(btnExpand)) {

					iClick(btnExpand, null, "ExpandSOHierarchyButton: CSO Page: ExpandButton");
				}
			} while (isElementDisplayed(btnExpand, 2));
			waitforPageLoadComplete();
			WaitandSwitchToFrame(frameLeft);
			do {
				if (isElementDisplayed(tabOrder)) {
					iClick(tabOrder, null, "OrderButton: CSO Page: tabOrder");
				}
			} while (!waitUntilElementPresent(By.xpath(xpath), 2));
			waitforPageLoadComplete();
			if (waitUntilElementPresent(By.xpath(xpath), 60)) {
				iClick(browser.findElement(By.xpath(xpath)));
			}
			waitforPageLoadComplete();
			if (supplementInfo.supplementType.matches("Tech Sup|Admin Sup|Cancel Sup")) {
				waitForElement(plusButtonInProgress);
				iClick(plusButtonInProgress, linkSRID, "Click on + button: CSO Page: InProgress + button");
			} else if (supplementInfo.supplementType.matches("Change|Disconnect")) {
				waitForElement(plusButtonCompleted);
				iClick(plusButtonCompleted, linkSRID, "Click on + button: CSO Page: Completed + button");
			}
			waitforPageLoadComplete();
			waitForElement(linkSRID);
			clickndRelease(linkSRID);
			waitforPageLoadComplete();
			browser.switchTo().defaultContent();
		} catch (Exception e) {
			mstatus = false;
			e.printStackTrace();
		}

		return mstatus;
	}
	  
}


	
	

